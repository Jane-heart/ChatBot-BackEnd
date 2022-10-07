package online.tuanzi.convertor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import online.tuanzi.common.Message;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;

/**
 * @ClassName: ChatEncoder
 * @Author: 团子tz
 * @CreateTime: 2022/10/05 14:56
 * @Description: 聊天实体编码
 */
@Slf4j
public class MessageEncoder implements javax.websocket.Encoder.Text<Message> {
    @Override
    public String encode(Message object) throws EncodeException {
        log.debug("自定义json解析聊天实体类");
        return JSON.toJSONString(object);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
