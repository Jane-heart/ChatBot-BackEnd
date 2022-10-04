package online.tuanzi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 16:12
 * @Description: 用户控制层类
 */
@Api("用户控制层类")
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/addFriend")
    @ApiOperation("添加好友")
    public void addFriend(){

    }

    @GetMapping("/listFriend")
    @ApiOperation("展示好友列表")
    public void listFriend(){

    }

    @GetMapping("/showFriendDetail")
    @ApiOperation("查看好友信息")
    public void showFriendDetail(){

    }

    @GetMapping("/showChatDetail")
    @ApiOperation("查看聊天信息")
    public void showChatDetail(){

    }

    @GetMapping("hello")
    @ApiOperation("测试方法")
    public String test(){
        return "hello";
    }
}
