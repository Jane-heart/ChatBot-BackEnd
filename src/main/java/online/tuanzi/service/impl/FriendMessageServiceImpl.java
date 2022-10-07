package online.tuanzi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import online.tuanzi.convertor.FriendMessageConvertor;
import online.tuanzi.exception.UserNoExistException;
import online.tuanzi.mapper.FriendMessageMapper;
import online.tuanzi.mapper.SessionListMapper;
import online.tuanzi.model.dto.FriendMessageRequest;
import online.tuanzi.model.entity.FriendMessage;
import online.tuanzi.model.entity.SessionList;
import online.tuanzi.model.entity.User;
import online.tuanzi.model.vo.FriendMessageVO;
import online.tuanzi.service.FriendMessageService;
import online.tuanzi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Tuanzi
* @description 针对表【t_friend_message】的数据库操作Service实现
* @createDate 2022-10-06 12:12:58
*/
@Service
@Slf4j
public class FriendMessageServiceImpl extends ServiceImpl<FriendMessageMapper, FriendMessage>
        implements FriendMessageService {

    @Resource
    private UserService userService;

    @Resource
    private SessionListMapper sessionListMapper;

    @Override
    public void saveFriendMessage(FriendMessageRequest friendMessageRequest) throws UserNoExistException {
        log.debug("进入了saveFriendMessage，参数为：{}", friendMessageRequest);

        //验证两个用户都存在
        long isExist = userService.count(new LambdaQueryWrapper<User>()
                .eq(User::getId,friendMessageRequest.getSenderId())
                .or().eq(User::getId, friendMessageRequest.getReceiverId()));

        if (isExist != 2){
            //其中一个用户不存在，抛出异常
            throw new UserNoExistException("用户不存在");
        }

        //封装数据
        FriendMessage friendMessage = FriendMessageConvertor.INSTANCE.toFriendMessage(friendMessageRequest);
        friendMessage.setIsDelete(0);//消息未撤回
        friendMessage.setIsRead(0);//消息未读
        this.save(friendMessage);

        //封装数据,更新会话列表的状态
        SessionList sessionList = new SessionList();
        sessionList.setUserId(friendMessageRequest.getSenderId());
        sessionList.setTargetId(friendMessageRequest.getReceiverId());
        sessionList.setIsGroup(1);//标志好友消息
        sessionList.setLastUserId(friendMessageRequest.getSenderId());
        sessionList.setContent(friendMessageRequest.getContent());
        sessionList.setLastTime(friendMessageRequest.getCreateTime());
        sessionList.setIsDelete(0);//消息未撤回
        sessionListMapper.update(sessionList,
                new LambdaUpdateWrapper<SessionList>()
                        .eq(SessionList::getUserId, sessionList.getUserId())
                        .eq(SessionList::getTargetId, sessionList.getTargetId())
                        .eq(SessionList::getIsDelete, sessionList.getIsDelete())
                        .eq(SessionList::getIsGroup, sessionList.getIsGroup()));

        log.debug("friendMessageRequest处理完毕");
    }

    @Override
    public List<FriendMessageVO> listUnCheckFriendMessage(FriendMessageRequest friendMessageRequest) throws UserNoExistException {
        log.debug("进入了listUnCheckFriendMessage，参数为：{}", friendMessageRequest);

        //验证两个用户都存在
        long isExist = userService.count(new LambdaQueryWrapper<User>()
                .eq(User::getId,friendMessageRequest.getSenderId())
                .or().eq(User::getId, friendMessageRequest.getReceiverId()));

        if (isExist != 2){
            //其中一个用户不存在，抛出异常
            throw new UserNoExistException("用户不存在");
        }
        //查到发送用户与接收用户之间未读，且未撤回的消息
        List<FriendMessage> friendMessages = this.list(new LambdaQueryWrapper<FriendMessage>()
                .eq(FriendMessage::getSenderId, friendMessageRequest.getSenderId())
                .eq(FriendMessage::getReceiverId, friendMessageRequest.getReceiverId())
                .eq(FriendMessage::getIsRead, 0)//未读
                .eq(FriendMessage::getIsDelete, 0));//未撤回

        List<FriendMessageVO> friendMessageVO = FriendMessageConvertor.INSTANCE.toFriendMessageVO(friendMessages);

        log.debug("listUnCheckFriendMessage处理完毕");
        return friendMessageVO;
    }

    @Override
    public void updateMessageState(Integer id) {
        log.debug("进入了updateMessageState，参数为：{}", id);

        this.update(new LambdaUpdateWrapper<FriendMessage>()
                .eq(FriendMessage::getId,id)//获取对应的id
                .set(FriendMessage::getIsRead, 1));//修改状态为已读

        log.debug("updateMessageState处理完毕");
    }

    @Override
    public List<FriendMessageVO> listFriendMessage(Integer userId, Integer friendId) {
        log.debug("进入了listFriendMessage，参数为：{}-{}", userId, friendId);

        //获取双方的消息记录，并按时间升序排列
        List<FriendMessage> friendMessages = this.list(new LambdaQueryWrapper<FriendMessage>()
                .eq(FriendMessage::getSenderId, userId).eq(FriendMessage::getReceiverId, friendId)
                .or()
                .eq(FriendMessage::getSenderId, friendId).eq(FriendMessage::getReceiverId, userId)
                .orderByAsc(FriendMessage::getCreateTime));


        List<FriendMessageVO> friendMessageVOS = FriendMessageConvertor.INSTANCE.toFriendMessageVO(friendMessages);
        log.debug("listFriendMessage处理完毕");
        return friendMessageVOS;
    }
}
