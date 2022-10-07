package online.tuanzi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import online.tuanzi.convertor.FriendConvertor;
import online.tuanzi.exception.UserNoExistException;
import online.tuanzi.mapper.FriendMapper;
import online.tuanzi.model.dto.FriendAddRequest;
import online.tuanzi.model.dto.FriendApplyRequest;
import online.tuanzi.model.dto.FriendDeleteRequest;
import online.tuanzi.model.entity.Friend;
import online.tuanzi.model.entity.User;
import online.tuanzi.model.vo.FriendApplyVO;
import online.tuanzi.model.vo.FriendBasicVO;
import online.tuanzi.service.FriendService;
import online.tuanzi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author Tuanzi
* @description 针对表【t_friend】的数据库操作Service实现
* @createDate 2022-10-04 16:54:24
*/
@Service
@Slf4j
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
    implements FriendService{
    @Resource
    private UserService userService;

    @Override
    public List<FriendBasicVO> getFriendList(Integer userId) {
        log.debug("进入了getFriendList，参数为：{}", userId);

        List<Friend> friendList = this.list(new LambdaQueryWrapper<Friend>().eq(Friend::getUserId, userId));

        List<FriendBasicVO> friendBasics = new ArrayList<>(friendList.size());

        friendList.forEach(friend -> {
            FriendBasicVO friendBasicVO = new FriendBasicVO();

            User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, friend.getFriendId()));

            friendBasicVO.setFriendId(friend.getFriendId());
//            friendBasicVO.setAvatar(user.getAvatar);
            friendBasicVO.setGender(user.getGender());
            friendBasicVO.setUserName(user.getUserName());

            friendBasics.add(friendBasicVO);
        });

        log.debug("getFriendList处理完毕");
        return friendBasics;
    }

    @Override
    public void addFriend(FriendAddRequest friendAddRequest) throws UserNoExistException {
        log.debug("进入了addFriend，参数为：{}", friendAddRequest);

        //验证两个用户都存在
        long isExist = userService.count(new LambdaQueryWrapper<User>()
                .eq(User::getId,friendAddRequest.getUserId())
                .or().eq(User::getId, friendAddRequest.getFriendId()));

        if (isExist != 2){
            //其中一个用户不存在，抛出异常
            throw new UserNoExistException("用户或申请添加的好友不存在");
        }

        //判断是否存在重复的添加
        long isDul = this.count(new LambdaQueryWrapper<Friend>()
                .eq(Friend::getUserId, friendAddRequest.getUserId())
                .or().eq(Friend::getFriendId, friendAddRequest.getFriendId()));
        if (isDul != 0){
            //重复添加，不做处理
            return;
        }

        //封装添加好友的消息
        Friend friend = FriendConvertor.INSTANCE.toFriend(friendAddRequest);
        //添加待通过状态
        friend.setIsDelete(0);
        //保存添加好友的消息
        this.save(friend);
        log.debug("addFriend处理完毕");
    }

    @Override
    public List<FriendApplyVO> getFriendApply(Integer userId) throws UserNoExistException {
        log.debug("进入getFriendApply，参数为：{}", userId);
        //判断是否为合法用户
        long isExist = userService.count(new LambdaQueryWrapper<User>()
                .eq(User::getId, userId));
        if (isExist == 0){
            //用户不存在，抛出异常
            throw new UserNoExistException("当前用户不存在");
        }

        //查找所有跟当前用户有关的未通过的好友申请
        List<Friend> list = this.list(new LambdaQueryWrapper<Friend>()
                .eq(Friend::getUserId, userId).eq(Friend::getIsDelete, 0));

        List<FriendApplyVO> friendApplyVO = FriendConvertor.INSTANCE.toFriendApplyVO(list);

        log.debug("getFriendApply处理完毕");
        return friendApplyVO;
    }

    @Override
    public void saveUnPassFriendState(FriendApplyRequest friendApplyRequest) throws UserNoExistException {
        log.debug("进入saveUnPassFriendState，参数为：{}", friendApplyRequest);
        //验证两个用户都存在
        long isExist = userService.count(new LambdaQueryWrapper<User>()
                .eq(User::getId,friendApplyRequest.getUserId())
                .or().eq(User::getId, friendApplyRequest.getFriendId()));

        if (isExist != 2){
            //其中一个用户不存在，抛出异常
            throw new UserNoExistException("用户或申请添加的好友不存在");
        }

        //封装好友信息
        Friend friend = FriendConvertor.INSTANCE.toFriend(friendApplyRequest);
        //设置好友添加状态为未通过
        friend.setIsDelete(0);
        this.save(friend);

        log.debug("saveUnPassFriendState处理完毕");
    }

    @Override
    public void savePassFriendState(Integer id) {
        this.update(new LambdaUpdateWrapper<Friend>()
                .eq(Friend::getId, id)
                .set(Friend::getIsDelete, 1));//更新状态为未删除/已添加

        //查询对应的好友信息
        Friend friend = this.getById(id);
        Integer friendId = friend.getFriendId();
        friend.setId(null);
        friend.setFriendId(friend.getUserId());
        friend.setUserId(friendId);

        //将好友关系变成双向
        this.save(friend);
    }

    @Override
    public void deleteFriendState(Integer id) {
        this.removeById(id);
    }

    @Override
    public void deleteFriend(FriendDeleteRequest friendDeleteRequest) {
        this.update(new LambdaUpdateWrapper<Friend>()
                .eq(Friend::getUserId, friendDeleteRequest.getUserId())
                .eq(Friend::getFriendId, friendDeleteRequest.getFriendId())
                .eq(Friend::getIsDelete, 2));//更改状态为删除
    }
}




