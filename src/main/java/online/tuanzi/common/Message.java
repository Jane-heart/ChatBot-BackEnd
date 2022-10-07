package online.tuanzi.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONUtil;

/**
 * @ClassName: Message
 * @Author: 团子tz
 * @CreateTime: 2022/10/05 19:20
 * @Description: 封装websocket消息体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message<T> {
    private Integer code;
    private String type;
    private T content;
}
