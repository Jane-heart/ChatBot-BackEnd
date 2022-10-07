package online.tuanzi.convertor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import online.tuanzi.common.Message;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;

/**
 * @ClassName: ChatDecoder
 * @Author: 团子tz
 * @CreateTime: 2022/10/05 14:55
 * @Description: 聊天实体解码
 */
@Slf4j
public class MessageDecoder implements javax.websocket.Decoder.Text<Message> {
    @Override
    public Message decode(String s) throws DecodeException {
        log.debug("自定义json解析聊天实体类");
        return JSON.parseObject(s,Message.class);
    }

    //该方法优先于decode被调用。这是为了使你有一个跳过解码消息的机会。
    @Override
    public boolean willDecode(String s) {
        log.debug("聊天实体解码支持开启：willDecode");
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
