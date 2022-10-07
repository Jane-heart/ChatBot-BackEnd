package online.tuanzi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.tuanzi.exception.UserNoExistException;
import online.tuanzi.model.dto.FriendAddRequest;
import online.tuanzi.model.dto.FriendMessageRequest;
import online.tuanzi.model.entity.Friend;
import online.tuanzi.model.vo.FriendBasicVO;
import online.tuanzi.model.vo.FriendMessageVO;
import online.tuanzi.service.FriendMessageService;
import online.tuanzi.service.FriendService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: FriendController
 * @Author: 团子tz
 * @CreateTime: 2022/10/05 19:35
 * @Description: 想想此类的作用，写在这里吧。
 */
@Api("好友类")
@RestController
@RequestMapping("/friends")
@CrossOrigin(origins = "*")
public class FriendController {

    @Resource
    private FriendService friendService;

    @Resource
    private FriendMessageService friendMessageService;

    @GetMapping("/list")
    @ApiOperation("列表")
    public List<Friend> list(){
        return friendService.list();
    }

    @PostMapping("/addFriend")
    @ApiOperation("申请添加好友")
    public void addFriend(@RequestBody FriendAddRequest friendAddRequest) throws UserNoExistException {
        friendService.addFriend(friendAddRequest);
    }
//
//    @GetMapping("/handlerFriend")
//    @ApiOperation("处理好友添加请求")
//    public FriendHandlerVO handlerFriend(FriendHandlerRequest friendHandlerRequest){
//        return friendService.handlerFriend(friendHandlerRequest);
//    }
//
    @PostMapping("/getFriendList")
    @ApiOperation("获取好友列表")
    public List<FriendBasicVO> getFriendList(Integer userId){
        return friendService.getFriendList(userId);
    }

//    @GetMapping("/showFriendDetail")
//    @ApiOperation("查看好友详细信息")
//    public FriendDetailVO showFriendDetail(FriendDetailRequest friendDetailRequest){
//        return friendService.showFriendDetail(friendDetailRequest);
//    }

    @PostMapping("/listFriendMessage")
    @ApiOperation("获取所有聊天记录")
    public List<FriendMessageVO> listFriendMessage(@RequestBody FriendMessageRequest friendMessageRequest) throws UserNoExistException {
        return friendMessageService.listFriendMessage(friendMessageRequest.getSenderId(),friendMessageRequest.getReceiverId());
    }

}
