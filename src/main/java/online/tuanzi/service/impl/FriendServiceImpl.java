package online.tuanzi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.tuanzi.model.entity.Friend;
import online.tuanzi.service.FriendService;
import online.tuanzi.mapper.FriendMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【t_friend】的数据库操作Service实现
* @createDate 2022-10-04 16:54:24
*/
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
    implements FriendService{

}




