package online.tuanzi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.tuanzi.model.entity.User;
import online.tuanzi.service.UserService;
import online.tuanzi.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Tuanzi
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-10-04 16:55:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




