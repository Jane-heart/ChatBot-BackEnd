package online.tuanzi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.tuanzi.model.vo.SessionListVO;
import online.tuanzi.service.FriendMessageService;
import online.tuanzi.service.SessionListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: SessionListController
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 22:43
 * @Description: 想想此类的作用，写在这里吧。
 */
@Api("会话列表")
@RestController
@RequestMapping("/sessions")
@CrossOrigin(origins = "*")
public class SessionListController {
    @Resource
    private SessionListService sessionListService;

    @Resource
    private FriendMessageService friendMessageService;

    @GetMapping("/list/{userId}")
    @ApiOperation("列表")
    public List<SessionListVO> getSessionList(@PathVariable("userId")Integer userId){
        return sessionListService.listSessionList(userId);
    }
}
