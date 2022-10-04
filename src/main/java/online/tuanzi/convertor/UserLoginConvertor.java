package online.tuanzi.convertor;

import online.tuanzi.model.dto.UserRegisterRequest;
import online.tuanzi.model.entity.User;
import online.tuanzi.model.vo.UserLoginVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName: UserLoginConvertor
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 19:05
 * @Description: 用户登录信息转换器
 */
@Mapper
public interface UserLoginConvertor {
    UserLoginConvertor INSTANCE = Mappers.getMapper(UserLoginConvertor.class);

    UserLoginVO toUserLoginVO(User user);

    User toUser(UserRegisterRequest userRegisterRequest);
}
