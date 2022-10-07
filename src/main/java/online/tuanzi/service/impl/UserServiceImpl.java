package online.tuanzi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import online.tuanzi.convertor.UserConvertor;
import online.tuanzi.exception.UserNoExistException;
import online.tuanzi.mapper.UserMapper;
import online.tuanzi.model.dto.UserFindRequest;
import online.tuanzi.model.entity.User;
import online.tuanzi.model.vo.UserFindVO;
import online.tuanzi.model.vo.UserInfoVO;
import online.tuanzi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-10-04 16:55:25
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public UserInfoVO getUserInfo(Integer userId) {
        log.debug("进入getUserInfo，参数为：{}",userId);

        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
        UserInfoVO userInfoVO = UserConvertor.INSTANCE.toUserInfoVO(user);
//        System.out.println("需要返回的userInfoVO："+userInfoVO);

        log.debug("getUserInfo处理完毕");
        return userInfoVO;
    }

    @Override
    public List<UserFindVO> findUser(UserFindRequest userFindRequest) throws UserNoExistException {
        log.debug("进入findUser，参数为：{}", userFindRequest);

        //判断是否为合法用户
        long isExist = this.count(new LambdaQueryWrapper<User>().eq(User::getId, userFindRequest.getId()));
        if (isExist == 0){
            //用户不存在，抛出异常
            throw new UserNoExistException("当前用户不存在");
        }

        //todo:对用户名做一些合法性判断

        //查找用户名
        List<User> users = this.list(new LambdaQueryWrapper<User>().like(User::getUserName, userFindRequest.getUserName()));

        List<UserFindVO> userFindVO = UserConvertor.INSTANCE.toUserFindVO(users);

        log.debug("findUser处理完毕");
        return userFindVO;
    }
}




