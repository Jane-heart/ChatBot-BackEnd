package online.tuanzi.interceptor;

import lombok.extern.slf4j.Slf4j;
import online.tuanzi.model.entity.User;
import online.tuanzi.common.RequestHolder;
import online.tuanzi.exception.UserUnLoginException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: UserLoginInterceptor
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 17:16
 * @Description: 用户登录拦截器
 */
@Slf4j
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * 请求处理之前（Controller方法调用之前）
     * 如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
     * 如果设置为true时，请求将会继续执行后面的操作
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("执行了拦截器的preHandle方法");
//        System.out.println("执行了拦截器的preHandle方法");
//        System.out.println(request.getRequestURL());
        HttpSession session = request.getSession();
        //统一拦截（查询当前session是否存在user）(这里user会在每次登录成功后，写入session)
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //找不到用户登录信息
            throw new UserUnLoginException("当前用户尚未登录");
        }

        //存在用户信息，存储，放行
        RequestHolder.save(user);
        return true;
    }

    /**
     * 请求处理之后，视图层被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("执行了拦截器的postHandle方法");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 请求结束，视图层被渲染之后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("执行了拦截器的afterCompletion方法");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
