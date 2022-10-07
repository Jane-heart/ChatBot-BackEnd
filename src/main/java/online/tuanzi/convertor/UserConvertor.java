package online.tuanzi.convertor;

import online.tuanzi.model.entity.User;
import online.tuanzi.model.vo.UserFindVO;
import online.tuanzi.model.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @ClassName: UserConvertor
 * @Author: 团子tz
 * @CreateTime: 2022/10/05 21:11
 * @Description: 想想此类的作用，写在这里吧。
 */
@Mapper
public interface UserConvertor {
    UserConvertor INSTANCE = Mappers.getMapper(UserConvertor.class);

    UserInfoVO toUserInfoVO(User user);

    UserFindVO toUserFindVO(User user);

    List<UserFindVO> toUserFindVO(List<User> user);
}
