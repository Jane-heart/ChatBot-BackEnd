package online.tuanzi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.tuanzi.model.entity.Message;
import online.tuanzi.service.MessageService;
import online.tuanzi.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【t_message】的数据库操作Service实现
* @createDate 2022-10-04 16:55:19
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

}




