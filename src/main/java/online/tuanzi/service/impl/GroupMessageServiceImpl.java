package online.tuanzi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.tuanzi.mapper.GroupMessageMapper;
import online.tuanzi.model.entity.GroupMessage;
import online.tuanzi.service.GroupMessageService;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【t_group_message】的数据库操作Service实现
* @createDate 2022-10-06 12:12:58
*/
@Service
public class GroupMessageServiceImpl extends ServiceImpl<GroupMessageMapper, GroupMessage>
        implements GroupMessageService {

}
