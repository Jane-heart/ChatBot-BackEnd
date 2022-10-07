package online.tuanzi.service;

import online.tuanzi.model.dto.UserLoginRequest;
import online.tuanzi.model.dto.UserRegisterRequest;
import online.tuanzi.model.vo.UserLoginVO;

/**
 * @ClassName: UserLoginService
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 18:34
 * @Description: 想想此类的作用，写在这里吧。
 */
public interface UserLoginService {
    //登录逻辑
    UserLoginVO login(UserLoginRequest userLoginRequest);

    void register(UserRegisterRequest userRegisterRequest);
}
