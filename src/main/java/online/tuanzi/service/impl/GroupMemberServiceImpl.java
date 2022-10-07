package online.tuanzi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.tuanzi.mapper.GroupMemberMapper;
import online.tuanzi.model.entity.GroupMember;
import online.tuanzi.service.GroupMemberService;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【t_group_member】的数据库操作Service实现
* @createDate 2022-10-06 12:12:58
*/
@Service
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberMapper, GroupMember>
        implements GroupMemberService {

}
