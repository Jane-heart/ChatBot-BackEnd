package online.tuanzi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.tuanzi.mapper.GroupMapper;
import online.tuanzi.model.entity.Group;
import online.tuanzi.service.GroupService;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【t_group】的数据库操作Service实现
* @createDate 2022-10-06 12:12:58
*/
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group>
        implements GroupService {

}
