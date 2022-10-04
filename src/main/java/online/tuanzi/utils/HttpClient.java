package online.tuanzi.utils;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: HttpClient
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 19:27
 * @Description: 想想此类的作用，写在这里吧。
 */
@Logger
public class HttpClient {

    private RestTemplate restTemplate;

    public HttpClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public <T> T getBody(String url, Class<T> clazz, String keyword){

        if (StringUtils.hasText(keyword)){
            url = String.format(url, keyword);
        }
        System.out.println("url:"+url);
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz);
        T result = responseEntity.getBody();
        System.out.println("result:"+result);
        return result;
    }
}
