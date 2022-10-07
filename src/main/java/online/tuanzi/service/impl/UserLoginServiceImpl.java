package online.tuanzi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import online.tuanzi.common.RequestHolder;
import online.tuanzi.common.WebSocketHolder;
import online.tuanzi.convertor.UserLoginConvertor;
import online.tuanzi.model.dto.UserLoginRequest;
import online.tuanzi.model.dto.UserRegisterRequest;
import online.tuanzi.model.entity.User;
import online.tuanzi.model.vo.UserLoginVO;
import online.tuanzi.service.UserLoginService;
import online.tuanzi.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @ClassName: UserLoginServiceImpl
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 18:34
 * @Description: 想想此类的作用，写在这里吧。
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserService userService;

    @Override
    public UserLoginVO login(UserLoginRequest userLoginRequest) {
        //todo:校验验证码
        Assert.isTrue("aaa".equals(userLoginRequest.getVerificationCode()),"验证码不正确");

        //查询用户是否存在
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, userLoginRequest.getPhone())
                .eq(User::getPassword, userLoginRequest.getPassword()));
        //不存在，抛出异常
        Assert.notNull(user,"用户名不存在");
        //存在，将基本信息返回
        //登录成功，将其信息写入
        WebSocketHolder.curUserPool.put(user.getId(),user);
        return UserLoginConvertor.INSTANCE.toUserLoginVO(user);
    }

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        //todo:校验验证码
        Assert.isTrue(userRegisterRequest.getVerificationCode()=="aaa","验证码不正确");

        //查询用户是否存在
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, userRegisterRequest.getPhone()));

        //存在，抛出异常
        Assert.isNull(user,"注册用户已存在");
        //不存在，保存用户信息
        userService.save(UserLoginConvertor.INSTANCE.toUser(userRegisterRequest));
    }
}
