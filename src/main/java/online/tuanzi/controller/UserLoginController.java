package online.tuanzi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import online.tuanzi.common.RequestHolder;
import online.tuanzi.exception.UserRegisterException;
import online.tuanzi.exception.UserUnLoginException;
import online.tuanzi.model.dto.UserLoginRequest;
import online.tuanzi.model.dto.UserRegisterRequest;
import online.tuanzi.model.vo.UserLoginVO;
import online.tuanzi.service.UserLoginService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: LoginController
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 17:13
 * @Description: 用户登录或者注册处理类
 */
@Api("用户登录/注册处理")
@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class UserLoginController {

    @Resource
    private UserLoginService userLoginService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public UserLoginVO login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) throws UserUnLoginException {
        log.info("用户登录信息:"+userLoginRequest);
        //判断用户信息是否为空
        Assert.notNull(userLoginRequest.getPhone(),"手机号不能为空");
        Assert.notNull(userLoginRequest.getPassword(),"密码不能为空");
        Assert.notNull(userLoginRequest.getVerificationCode(),"验证码不能为空");
        //保存请求信息
        RequestHolder.save(request);
        //登录
        UserLoginVO userLoginVO;
        try {
            userLoginVO = userLoginService.login(userLoginRequest);
        } catch (Exception e){
            e.printStackTrace();
            throw new UserUnLoginException("用户登录异常");
        } finally {
            //清除当前线程保存的信息
            RequestHolder.remove();
        }
        return userLoginVO;
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public void register(UserRegisterRequest userRegisterRequest, HttpServletRequest request) throws UserRegisterException {
        //判断注册信息是否为空
        Assert.notNull(userRegisterRequest.getPhone(),"手机号不能为空");
        Assert.notNull(userRegisterRequest.getPassword(),"密码不能为空");
        Assert.notNull(userRegisterRequest.getVerificationCode(),"验证码不能为空");
        //保存请求信息
        RequestHolder.save(request);
        //登录
        try {
            userLoginService.register(userRegisterRequest);
        } catch (Exception e){
            e.printStackTrace();
            throw new UserRegisterException("用户注册失败");
        } finally {
            //清除当前线程保存的信息
            RequestHolder.remove();
        }
    }
}
