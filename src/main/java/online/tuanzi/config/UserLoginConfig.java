package online.tuanzi.config;

import online.tuanzi.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @ClassName: UserLoginConfig
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 17:37
 * @Description: 用户登录配置类
 */
@Configuration
public class UserLoginConfig implements WebMvcConfigurer{
    @Resource
    private UserLoginInterceptor userLoginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册UserLoginInterceptor拦截器
        InterceptorRegistration registration = registry
                .addInterceptor(userLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(               //添加不拦截路径
                        "/login",       //登录路径
                        "/register",    //注册路径
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/api-docs",
                        "/chat/**",     //聊天机器人路径
                        "/**/*.html",   //html静态资源
                        "/**/*.js",     //js静态资源
                        "/**/*.css"     //css静态资源
                );
    }
}
