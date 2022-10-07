package online.tuanzi.convertor;

import online.tuanzi.model.dto.FriendAddRequest;
import online.tuanzi.model.dto.FriendApplyRequest;
import online.tuanzi.model.entity.Friend;
import online.tuanzi.model.vo.FriendApplyVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @ClassName: FriendConvertor
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 14:27
 * @Description: 想想此类的作用，写在这里吧。
 */
@Mapper
public interface FriendConvertor {
    FriendConvertor INSTANCE = Mappers.getMapper(FriendConvertor.class);

    Friend toFriend(FriendAddRequest friendAddRequest);

    FriendApplyVO toFriendApplyVO(Friend friend);

    List<FriendApplyVO> toFriendApplyVO(List<Friend> friends);

    Friend toFriend(FriendApplyRequest friendApplyRequest);
}
