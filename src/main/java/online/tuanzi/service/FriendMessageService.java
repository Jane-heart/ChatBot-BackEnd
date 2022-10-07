package online.tuanzi.service;

import online.tuanzi.exception.UserNoExistException;
import online.tuanzi.model.dto.FriendMessageRequest;
import online.tuanzi.model.entity.FriendMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import online.tuanzi.model.vo.FriendMessageVO;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【t_friend_message】的数据库操作Service
* @createDate 2022-10-06 12:12:58
*/
public interface FriendMessageService extends IService<FriendMessage> {

    //将消息保存为未读状态,并更新会话列表的状态
    void saveFriendMessage(FriendMessageRequest friendMessageRequest) throws UserNoExistException;

    //获取所有未读消息
    List<FriendMessageVO> listUnCheckFriendMessage(FriendMessageRequest friendMessageRequest) throws UserNoExistException;

    //将指定消息id对应的消息修改为已读
    void updateMessageState(Integer id);

    //获取所有的双方消息记录
    List<FriendMessageVO> listFriendMessage(Integer userId, Integer friendId);
}
