package online.tuanzi.common;

import online.tuanzi.exception.UserRegisterException;
import online.tuanzi.exception.UserUnLoginException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: GlobalExceptionHandler
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 20:45
 * @Description: 全局统一异常封装
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //用户未注册/未登录异常
    @ExceptionHandler({UserRegisterException.class, UserUnLoginException.class})
    public R bizExceptionHandler(HttpServletRequest req, Exception e) {
        return R.error().message(e.getMessage());
    }

    //所有异常
    @ExceptionHandler(Exception.class)
    public R globalExceptionHandler(HttpServletRequest req, Exception e) {
        return R.error().message(e.getMessage());
    }
}
