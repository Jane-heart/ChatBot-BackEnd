package online.tuanzi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: ChatBotMain
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 16:08
 * @Description: ChatBotMain主启动类
 */
@SpringBootApplication
@MapperScan(basePackages = {"online.tuanzi.**.mapper"})
public class ChatBotMain {
    public static void main(String[] args) {
        SpringApplication.run(ChatBotMain.class,args);
    }
}
