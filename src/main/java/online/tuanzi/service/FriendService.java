package online.tuanzi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import online.tuanzi.exception.UserNoExistException;
import online.tuanzi.model.dto.FriendAddRequest;
import online.tuanzi.model.dto.FriendApplyRequest;
import online.tuanzi.model.dto.FriendDeleteRequest;
import online.tuanzi.model.entity.Friend;
import online.tuanzi.model.vo.FriendApplyVO;
import online.tuanzi.model.vo.FriendBasicVO;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【t_friend】的数据库操作Service
* @createDate 2022-10-04 16:54:24
*/
public interface FriendService extends IService<Friend> {

    List<FriendBasicVO> getFriendList(Integer userId);

    void addFriend(FriendAddRequest friendAddRequest) throws UserNoExistException;

    List<FriendApplyVO> getFriendApply(Integer userId) throws UserNoExistException;

    //将申请好友信息保存为未通过状态
    void saveUnPassFriendState(FriendApplyRequest friendApplyRequest) throws UserNoExistException;

    //更改好友添加成功状态
    void savePassFriendState(Integer id);

    void deleteFriendState(Integer id);

    void deleteFriend(FriendDeleteRequest friendDeleteRequest);
}
