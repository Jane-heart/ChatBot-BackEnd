package online.tuanzi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.tuanzi.convertor.SessionListConvertor;
import online.tuanzi.mapper.SessionListMapper;
import online.tuanzi.model.entity.*;
import online.tuanzi.model.vo.FriendMessageVO;
import online.tuanzi.model.vo.SessionListVO;
import online.tuanzi.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author Tuanzi
* @description 针对表【t_session_list】的数据库操作Service实现
* @createDate 2022-10-06 12:12:58
*/
@Service
public class SessionListServiceImpl extends ServiceImpl<SessionListMapper, SessionList>
        implements SessionListService {
    @Resource
    private FriendService friendService;

    @Resource
    private UserService userService;

    @Resource
    private GroupService groupService;

    @Resource
    private FriendMessageService friendMessageService;

    @Resource
    private GroupMessageService groupMessageService;

    @Override
    public void saveOrUpdateSessionList(Integer id) {
        //获取到好友关系信息
        Friend friend = friendService.getOne(new LambdaQueryWrapper<Friend>().eq(Friend::getId, id));

        Integer userId = friend.getUserId();
        Integer friendId = friend.getFriendId();

        //查看之前有没有会话列表
        SessionList session = this.getOne(new LambdaQueryWrapper<SessionList>()
                .eq(SessionList::getIsGroup, 1)//查询好友会话信息
                .eq(SessionList::getUserId, userId)
                .eq(SessionList::getTargetId, friendId));

        //如果之前有开启了会话，不做处理
        if (session!=null) {
            return;
        }
        //如果是新会话
        //封装会话列表信息
        SessionList sessionList = new SessionList();
        sessionList.setContent("我们已经是好友了，一起聊天吧");
        sessionList.setLastTime(new Date());
        sessionList.setIsDelete(0);//会话未删除状态
        sessionList.setUserId(userId);//用户方会话
        sessionList.setLastUserId(friendId);//最后一次发言是好友
        sessionList.setTargetId(friendId);//好友id
        sessionList.setIsGroup(1);//不是群id

        //更新会话状态
        this.save(sessionList);

        //对方的也一样
        sessionList.setUserId(friendId);
        sessionList.setLastUserId(userId);
        sessionList.setTargetId(userId);

        //保存会话状态
        this.save(sessionList);
    }

    @Override
    public List<SessionListVO> listSessionList(Integer userId) {
        List<SessionList> sessionLists = this.list(new LambdaQueryWrapper<SessionList>()
                .eq(SessionList::getUserId, userId)
                .eq(SessionList::getIsDelete, 0));
        List<SessionListVO> sessionListVO = SessionListConvertor.INSTANCE.toSessionListVO(sessionLists);

        for (int i = 0; i < sessionLists.size(); i++) {
            String targetName;
            String targetUrl;
            SessionListVO session = sessionListVO.get(i);
            if (sessionLists.get(i).getIsGroup()==1){
                User user = userService.getOne(new LambdaQueryWrapper<User>()
                        .eq(User::getId, sessionLists.get(i).getTargetId()));//如果会话目标是用户
                targetName = user.getUserName();//获取用户名
                targetUrl = user.getAvatar();//获取头像url

                //获取所有的双方消息记录
                List<FriendMessageVO> friendMessages = friendMessageService.listFriendMessage(sessionLists.get(i).getUserId(),sessionLists.get(i).getTargetId());
                //设置消息记录
                session.setMessage(friendMessages);
            }else {
                Group group = groupService.getOne(new LambdaQueryWrapper<Group>()
                        .eq(Group::getId, sessionLists.get(i).getTargetId()));//如果会话目标是群聊
                targetName = group.getName();//获取群名
                targetUrl = group.getAvatar();//获取头像url
            }
            //设置用户名和头像
            session.setTargetName(targetName);
            session.setTargetUrl(targetUrl);

            //todo:获取所有的群消息的消息记录
            sessionListVO.set(i, session);
        }

        return sessionListVO;
    }
}
