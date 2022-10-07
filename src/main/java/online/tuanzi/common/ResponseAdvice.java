package online.tuanzi.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName: ResponseAdvice
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 20:41
 * @Description: 正常结果全局统一封装返回
 */
//仅拦截controller的，防止swagger的结果被拦截
@RestControllerAdvice(basePackages = {"online.tuanzi.controller"})
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 是否开启功能 true：是
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 处理返回结果
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //处理字符串类型数据，如果Controller返回String的话，SpringBoot是直接返回.
        if (body instanceof String) {
            try {
                return objectMapper.writeValueAsString(R.ok().data(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        //返回类型是否已经封装
        if (body instanceof R) {
            return body;
        }

        //这里处理成功结果，异常结果有全局异常机制处理
        return R.ok().data(body);
    }
}
