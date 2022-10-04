package online.tuanzi.exception;

/**
 * @ClassName: UserRegisterException
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 19:20
 * @Description: 用户注册异常
 */
public class UserRegisterException extends Exception {
    //异常信息
    private String message;

    //构造函数
    public UserRegisterException(String message){
        super(message);
        this.message = message;
    }
}
