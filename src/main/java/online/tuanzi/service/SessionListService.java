package online.tuanzi.service;

import online.tuanzi.model.entity.SessionList;
import com.baomidou.mybatisplus.extension.service.IService;
import online.tuanzi.model.vo.SessionListVO;

import java.util.List;

/**
* @author Tuanzi
* @description 针对表【t_session_list】的数据库操作Service
* @createDate 2022-10-06 12:12:58
*/
public interface SessionListService extends IService<SessionList> {

    //更新或添加进好友会话
    void saveOrUpdateSessionList(Integer id);

    //获取对方新的会话列表并返回
    List<SessionListVO> listSessionList(Integer userId);
}
