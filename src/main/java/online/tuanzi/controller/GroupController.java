package online.tuanzi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.tuanzi.model.entity.Friend;
import online.tuanzi.model.entity.Group;
import online.tuanzi.service.GroupService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: GroupController
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 13:19
 * @Description: 想想此类的作用，写在这里吧。
 */
@Api("群聊")
@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "*")
public class GroupController {

    @Resource
    private GroupService groupService;

    @GetMapping("/list")
    @ApiOperation("列表")
    public List<Group> list(){
        return groupService.list();
    }
}
