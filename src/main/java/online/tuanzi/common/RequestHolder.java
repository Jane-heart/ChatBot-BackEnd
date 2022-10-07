package online.tuanzi.common;

import online.tuanzi.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: UserHolder
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 17:42
 * @Description: 保存用于当前线程的请求信息，todo:暂时替代redis
 */
public class RequestHolder {
    private static final ThreadLocal<User> userHolder = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();
    //todo:添加对token的支持

    public static void save(User user){
        userHolder.set(user);
    }

    public static void save(HttpServletRequest request){
        requestHolder.set(request);
    }

    public static User getUser(){
        return userHolder.get();
    }

    public static HttpServletRequest getRequestHolder(){
        return requestHolder.get();
    }


    public static void remove(){
        userHolder.remove();
        requestHolder.remove();
    }
}
