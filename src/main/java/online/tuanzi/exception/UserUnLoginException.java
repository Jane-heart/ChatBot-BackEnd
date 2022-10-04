package online.tuanzi.exception;

/**
 * @ClassName: UserUnLoginException
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 17:27
 * @Description: 用户未登录异常
 */
public class UserUnLoginException extends Exception {
    //异常信息
    private String message;

    //构造函数
    public UserUnLoginException(String message){
        super(message);
        this.message = message;
    }
}
