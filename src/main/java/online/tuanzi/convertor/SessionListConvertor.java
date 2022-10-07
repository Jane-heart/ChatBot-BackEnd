package online.tuanzi.convertor;

import online.tuanzi.model.entity.SessionList;
import online.tuanzi.model.vo.SessionListVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @ClassName: SessionListConvertor
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 21:41
 * @Description: 想想此类的作用，写在这里吧。
 */
@Mapper
public interface SessionListConvertor {
    SessionListConvertor INSTANCE = Mappers.getMapper(SessionListConvertor.class);

    List<SessionListVO> toSessionListVO(List<SessionList> sessionLists);
}
