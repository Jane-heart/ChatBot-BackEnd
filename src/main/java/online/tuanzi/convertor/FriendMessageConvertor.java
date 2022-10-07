package online.tuanzi.convertor;

import online.tuanzi.model.dto.FriendMessageRequest;
import online.tuanzi.model.entity.FriendMessage;
import online.tuanzi.model.vo.FriendMessageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @ClassName: FriendMessageConvertor
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 17:36
 * @Description: 想想此类的作用，写在这里吧。
 */
@Mapper
public interface FriendMessageConvertor {
    FriendMessageConvertor INSTANCE = Mappers.getMapper(FriendMessageConvertor.class);

    FriendMessage toFriendMessage(FriendMessageRequest friendMessageRequest);

    List<FriendMessageVO> toFriendMessageVO(List<FriendMessage> friendMessages);
}
