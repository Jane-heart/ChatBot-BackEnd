package online.tuanzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: ChatBotMain
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 16:08
 * @Description: ChatBotMain主启动类
 */
@SpringBootApplication
public class ChatBotMain {
    public static void main(String[] args) {
        SpringApplication.run(ChatBotMain.class,args);
    }
}
