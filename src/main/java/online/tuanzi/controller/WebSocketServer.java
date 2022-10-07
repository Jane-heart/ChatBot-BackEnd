package online.tuanzi.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import online.tuanzi.common.Message;
import online.tuanzi.common.WebSocketHolder;
import online.tuanzi.convertor.MessageDecoder;
import online.tuanzi.convertor.MessageEncoder;
import online.tuanzi.exception.UserNoExistException;
import online.tuanzi.model.dto.*;
import online.tuanzi.model.vo.FriendApplyVO;
import online.tuanzi.model.vo.FriendMessageVO;
import online.tuanzi.model.vo.SessionListVO;
import online.tuanzi.service.FriendMessageService;
import online.tuanzi.service.FriendService;
import online.tuanzi.service.SessionListService;
import online.tuanzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: WebSocketServer
 * @Author: 团子tz
 * @CreateTime: 2022/10/05 12:34
 * @Description: WebSocket服务端
 */
@Component
@Slf4j
//@ServerEndpoint 将当前类定义成websocket服务器端,value将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
//指定解码器
//@ServerEndpoint("/ws/{userId}")
//访问地址：ws://localhost:5000/websocket/1
@ServerEndpoint(value = "/websocket/{userId}",  decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
public class WebSocketServer {

    //用来保存自己的Session信息
    private Session session;

    private static UserService userService;

    //todo:SpringBoot无法注入非静态依赖【暂不清楚原因】
    private static FriendService friendService;

    private static FriendMessageService friendMessageService;

    private static SessionListService sessionListService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }

    @Autowired
    public void setFriendMessageService(FriendMessageService friendMessageService) {
        this.friendMessageService = friendMessageService;
    }

    @Autowired
    public void setSessionListService(SessionListService sessionListService) {
        this.sessionListService = sessionListService;
    }

    /**
     * 连接建立成功调用的方法
     * @param session 连接成功获取到的session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value="userId")Integer userId) throws UserNoExistException {
        log.info("websocket连接onOpen:{},{}",this.toString(), session.getId());
        WebSocketHolder.webSockets.put(userId, this);
        WebSocketHolder.save(userId, Arrays.asList(session));
        this.session = session;
        //todo:连接上之后查询数据库，将未读的消息发送给指定用户
        //根据用户id获取尚未通过的好友添加申请
        this.getFriendApply(userId);

        //todo:发送所有未读消息

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 聊天消息实体
     * @param session session
     */
    @OnMessage
    public void onMessage(Message message, Session session) throws UserNoExistException {
        log.info("websocket连接onMessage:{},{},{}", message, this.toString(), session.getId());

        //先序列化为JSON串
        String content = JSON.toJSONString(message.getContent());
        //再序列化为对应的对象
        //处理不同类型的数据
        switch (message.getType()){
            case "friend-message"://好友消息
                handlerFriendMessage(JSON.parseObject(content, FriendMessageRequest.class));
                break;
            case "friend-message-success"://成功接收好友消息
                handlerFriendMessageSuccess(JSON.parseObject(content, FriendMessageSuccessRequest.class));
                break;
            case "apply-friend"://申请好友
                handlerApplyFriend(JSON.parseObject(content, FriendApplyRequest.class));
                break;
            case "apply-friend-agree"://同意好友申请
                handlerApplyFriendAgree(JSON.parseObject(content, FriendApplyAgreeRequest.class));
                break;
            case "apply-friend-fail"://拒绝好友申请
                handlerApplyFriendFail(JSON.parseObject(content, FriendApplyFailRequest.class));
                break;
            case "delete-friend"://删除好友
                deleteFriend(JSON.parseObject(content, FriendDeleteRequest.class));
                break;
                //todo:群相关的暂时不做处理
            case "group-message"://群消息
                groupMessage(message.getContent());
                break;
            case "invite-people"://邀请进群
                invitePeople(message.getContent());
                break;
            case "invite-people-agree"://同意邀请进群
                invitePeopleAgree(message.getContent());
                break;
            case "invite-people-fail"://拒绝邀请进群
                invitePeopleFail(message.getContent());
                break;
            case "apply-group"://申请进群
                applyGroup(message.getContent());
                break;
            case "apply-group-agree"://同意申请进群消息
                applyGroupAgree(message.getContent());
                break;
            case "apply-group-fail"://拒绝申请进群消息
                applyGroupFail(message.getContent());
                break;
            case "quit-group"://退群
                quitGroup(message.getContent());
                break;
            case "kicked"://账户已别处登录
                kicked(message.getContent());
                break;
            case "ping"://保持心跳
                ping();
                break;
            default:
                break;
        }







//        String key = this.session.getId();
//        if (!webSockets.containsKey(key)) {
//            this.close();
//            return;
//        }
        //判断是发群消息还是单发消息
//        if (StringUtils.isEmpty(chatRequest.getGroupId())){
//            //单发
////            this.sendMessage(chatRequest);
//        }else {
//            //群发
//            this.sendAllMessage(chatRequest);
//        }
        log.info("websocket连接onMessage，处理完成");
    }

    /**
     * 发生异常时的处理
     *
     * @param session   客户端session
     * @param throwable session
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        if (this.session != null && this.session.isOpen()) {
            log.error("websocket连接onError。inputSession：{}-localSession：{}", session.getId(), this.toString(), throwable);
            this.close();
        } else {
            log.debug("已经关闭的websocket连接发生异常！inputSession：{}-localSession：{}", session.getId(), this.toString(), throwable);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.debug("websocket连接onClose。{}", this.toString());
        //然后关闭
        this.close();
    }

    /**
     * 推送消息给群内客户端
     * @param message 聊天消息实体
     * @param sessions 发送消息的目标
     */
    private void sendMessage(Message message,List<Session> sessions) {
        log.info("发送消息：{}-{}",sessions,message);
        //todo：群发消息
        try {
            sessions.forEach(session -> {
                try {
                    session.getBasicRemote().sendObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            log.error("websocket连接发送客户端发送消息时异常！{}-{}", message, this.toString(), e);
        }
        log.info("发送消息完成");
    }
    /**
     * 推送消息给指定客户端
     * @param message 聊天消息实体
     * @param session 发送消息的目标
     */
    private void sendMessage(Message message,Session session) {
        log.info("发送消息：{}-{}",session,message);
        //判断需要发送的用户是否处于登录状态
//        boolean isLogin = WebSocketHolder.curUserPool.containsKey(chatRequest.getId());

        //发送给指定客户端
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("websocket连接发送客户端发送消息时异常！{}-{}", message, this.toString(), e);
        } catch (EncodeException e) {
            e.printStackTrace();
            log.error("websocket连接发送客户端发送消息时异常！{}-{}", message, this.toString(), e);
        }
        log.info("发送消息完成");
    }

    /**
     * 关闭session连接
     */
    private void close() {
        // 断开连接删除用户删除session
        Integer userId = Integer.parseInt(this.session.getRequestParameterMap().get("userId").get(0));
        WebSocketHolder.sessionPool.remove(userId);//此处做全部移除，后期 todo:做单个移除
        WebSocketHolder.webSockets.remove(userId);

        WebSocketHolder.curUserPool.remove(userId);
        log.info("websocket连接关闭完成。{}", this.toString());
    }


    //根据用户id获取尚未通过的好友添加申请
    private void getFriendApply(Integer userId) throws UserNoExistException{
        List<FriendApplyVO> friendApplyVO = friendService.getFriendApply(userId);

        //封装Message
        Message<List<FriendApplyVO>> message = new Message<>();
        message.setCode(200);
        message.setType("apply-friend");
        message.setContent(friendApplyVO);

        this.sendMessage(message, this.session);
    }

    //处理好友消息
    private void handlerFriendMessage(FriendMessageRequest friendMessageRequest) throws UserNoExistException {
        Integer userId = friendMessageRequest.getReceiverId();
        //将消息保存为未读状态,并更新会话列表的状态
        friendMessageService.saveFriendMessage(friendMessageRequest);

        //获取所有消息消息
        List<FriendMessageVO> friendMessageVO = friendMessageService.listFriendMessage(friendMessageRequest.getSenderId(),friendMessageRequest.getReceiverId());
        //判断需要发送的用户是否在线
        boolean isLogin = WebSocketHolder.curUserPool.containsKey(userId);
        List<Session> sessions = WebSocketHolder.sessionPool.get(userId);
        //在线，封装实体，发送
        if (isLogin && sessions != null && sessions.size()!=0){
            //封装Message
            Message<List<FriendMessageVO>> message = new Message<>();
            message.setCode(200);
            message.setType("friend-message");
            message.setContent(friendMessageVO);

            this.sendMessage(message, sessions);
        }
        //不在线，不处理
    }

    //成功接收好友消息
    private void handlerFriendMessageSuccess(FriendMessageSuccessRequest friendMessageSuccessRequest){
        //将指定消息id对应的消息修改为已读
        friendMessageService.updateMessageState(friendMessageSuccessRequest.getId());
    }

    //处理申请好友
    private void handlerApplyFriend(FriendApplyRequest friendApplyRequest) throws UserNoExistException {
        Integer userId = friendApplyRequest.getFriendId();
        //将申请好友信息保存为未通过状态，并返回对应的实体
        friendService.saveUnPassFriendState(friendApplyRequest);

        //判断需要发送的用户是否在线
        boolean isLogin = WebSocketHolder.curUserPool.containsKey(userId);
        List<Session> sessions = WebSocketHolder.sessionPool.get(userId);
        //在线，根据用户id获取尚未通过的好友添加申请
        if (isLogin && sessions != null && sessions.size()!=0){
            this.getFriendApply(userId);
        }
        //不在线，不处理
    }

    //处理同意好友申请
    private void handlerApplyFriendAgree(FriendApplyAgreeRequest friendApplyAgreeRequest){
        Integer userId = friendApplyAgreeRequest.getFriendId();
        //更改好友添加成功状态
        friendService.savePassFriendState(friendApplyAgreeRequest.getId());
        //更新或添加进好友会话
        sessionListService.saveOrUpdateSessionList(friendApplyAgreeRequest.getId());

        //向申请者发送添加成功信息
        //判断需要发送的用户是否在线
        boolean isLogin = WebSocketHolder.curUserPool.containsKey(userId);
        List<Session> sessions = WebSocketHolder.sessionPool.get(userId);
        //在线，封装实体，发送
        if (isLogin && sessions != null && sessions.size()!=0){
            //获取对方新的会话列表并返回
            List<SessionListVO> sessionListVO = sessionListService.listSessionList(userId);
            //封装Message
            Message<List<SessionListVO>> message = new Message<>();
            message.setCode(200);
            message.setType("apply-friend-agree");
            message.setContent(sessionListVO);

            this.sendMessage(message, sessions);
        }
        //不在线，不处理
    }

    //处理拒绝好友申请
    private void handlerApplyFriendFail(FriendApplyFailRequest friendApplyFailRequest){
        //更改好友添加失败状态
        friendService.deleteFriendState(friendApplyFailRequest.getId());
        //不做处理
    }

    //处理删除好友
    private void deleteFriend(FriendDeleteRequest friendDeleteRequest){
        //删除好友
        friendService.deleteFriend(friendDeleteRequest);
        //不做处理
    }

    private void groupMessage(Object content){

    }

    private void invitePeople(Object content){

    }

    private void invitePeopleAgree(Object content){

    }

    private void invitePeopleFail(Object content){

    }

    private void applyGroup(Object content){

    }

    private void applyGroupAgree(Object content){

    }

    private void applyGroupFail(Object content){

    }

    private void quitGroup(Object content){

    }

    private void kicked(Object content){

    }
    private void ping(){
        log.info("当前用户{}-{}心跳保持成功",session,session.getId());
    }
}
