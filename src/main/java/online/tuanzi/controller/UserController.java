package online.tuanzi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.tuanzi.exception.UserNoExistException;
import online.tuanzi.model.dto.UserFindRequest;
import online.tuanzi.model.entity.User;
import online.tuanzi.model.vo.UserFindVO;
import online.tuanzi.model.vo.UserInfoVO;
import online.tuanzi.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: UserController
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 16:12
 * @Description: 用户控制层类
 */
@Api("用户控制层类")
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation("列表")
    public List<User> list(){
        return userService.list();
    }

    @GetMapping("/getUserInfo/{userId}")
    @ApiOperation("获取用户基本信息")
    public UserInfoVO getUserInfo(@PathVariable("userId") Integer userId){
        return userService.getUserInfo(userId);
    }

//    @PostMapping("/updateUserInfo")
//    @ApiOperation("更新用户基本信息")
//    public String updateUserInfo(){
//        return "hello";
//    }

    @PostMapping("/findUser")
    @ApiOperation("查找用户，根据用户名搜索")
    public List<UserFindVO> findUser(@RequestBody UserFindRequest userFindRequest) throws UserNoExistException {
        return userService.findUser(userFindRequest);
    }

//    @PostMapping("/findUserById")
//    @ApiOperation("根据用户id查找用户具体信息")
//    public String findUserById(String userId){
//        return "hello";
//    }


//
//    @GetMapping("hello")
//    @ApiOperation("测试方法")
//    public String test(){
//        return "hello";
//    }
}
