package online.tuanzi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import online.tuanzi.exception.UserNoExistException;
import online.tuanzi.model.dto.UserFindRequest;
import online.tuanzi.model.entity.User;
import online.tuanzi.model.vo.UserFindVO;
import online.tuanzi.model.vo.UserInfoVO;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-10-04 16:55:25
*/
public interface UserService extends IService<User> {

    UserInfoVO getUserInfo(Integer userId);

    List<UserFindVO> findUser(UserFindRequest userFindRequest) throws UserNoExistException;

}
