package online.tuanzi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 16:12
 * @Description: 想想此类的作用，写在这里吧。
 */
@Api("用户控制层类")
@RestController
public class UserController {

    @GetMapping("hello")
    @ApiOperation("测试方法")
    public String test(){
        return "hello";
    }
}
