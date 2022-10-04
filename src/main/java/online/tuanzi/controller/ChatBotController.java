package online.tuanzi.controller;

import io.swagger.annotations.ApiOperation;
import online.tuanzi.bean.vo.R;
import online.tuanzi.service.ChatBotService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: ChatBotController
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 17:23
 * @Description: 想想此类的作用，写在这里吧。
 */
@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatBotController {
    @Resource
    private ChatBotService chatBotService;

    /**
     * 人工智能聊天：语义分析,情绪识别,大数据处理
     * eg:你好
     */
    @GetMapping("/chat/{keyword}")
    @ApiOperation("人工智能聊天：语义分析,情绪识别,大数据处理")
    public R artificialIntelligenceChat(@PathVariable("keyword") String keyword) {
        Assert.notNull(keyword, "聊天不能为空");
        R r = chatBotService.artificialIntelligenceChat(keyword);
        System.out.println("r:" + r);
        return r;
    }

    /**
     * 歌词：海量歌曲歌词查询
     * eg：歌词后来 / 歌词后来-刘若英
     */
    @GetMapping("/lyric/{keyword}")
    @ApiOperation("歌词：海量歌曲歌词查询")
    public R lyric(@PathVariable("keyword") String keyword) {
        Assert.notNull(keyword, "歌词不能为空");
        R r = chatBotService.lyric(keyword);
        return r;
    }

    /**
     * 天气：获取最新天气情况，气温，风向等，助你轻松出行
     * eg：天气深圳
     */
    @GetMapping("/weather/{keyword}")
    @ApiOperation("天气：获取最新天气情况，气温，风向等，助你轻松出行")
    public R weather(@PathVariable("keyword") String keyword) {
        Assert.notNull(keyword, "天气不能为空");
        R r = chatBotService.weather(keyword);
        return r;
    }

    /**
     * 翻译：中英文互译，清晰识别
     * eg：翻译i love you
     */
    @GetMapping("/translate/{keyword}")
    @ApiOperation("翻译：中英文互译，清晰识别")
    public R translate(@PathVariable("keyword") String keyword) {
        Assert.notNull(keyword, "翻译不能为空");
        R r = chatBotService.translate(keyword);
        return r;
    }

    /**
     * ip查询：根据ip地址，查询ip所属的区域
     * eg：归属127.0.0.1
     */
    @GetMapping("/ipLookup/{keyword}")
    @ApiOperation("ip查询：根据ip地址，查询ip所属的区域")
    public R ipLookup(@PathVariable("keyword") String keyword) {
        Assert.notNull(keyword, "ip查询不能为空");
        R r = chatBotService.ipLookup(keyword);
        return r;
    }

    /**
     * 笑话：不断搜集更新网络上搞笑、幽默、内涵段子
     * eg：笑话
     */
    @GetMapping("/joke")
    @ApiOperation("笑话：不断搜集更新网络上搞笑、幽默、内涵段子")
    public R joke() {
//        Assert.notNull(keyword,"歌词不能为空");
        R r = chatBotService.joke();
        return r;
    }

    /**
     * 计算：科学计算器，包含加减乘除和各种数学运算
     * eg：计算1+1*2/3-4
     */
    @GetMapping("/calculate/{keyword}")
    @ApiOperation("计算：科学计算器，包含加减乘除和各种数学运算")
    public R calculate(@PathVariable("keyword") String keyword) {
        Assert.notNull(keyword, "计算不能为空");
        R r = chatBotService.calculate(keyword);
        return r;
    }

    /**
     * 手机号码归属查询：根据手机号码，查询手机号码归属地信息，如省份 、城市、运营商等
     * eg：归属13430108888
     */
    @GetMapping("/phoneNumberDependencyQuery/{keyword}")
    @ApiOperation("手机号码归属查询：根据手机号码，查询手机号码归属地信息，如省份 、城市、运营商等")
    public R phoneNumberDependencyQuery(@PathVariable("keyword") String keyword) {
        Assert.notNull(keyword, "手机号码归属查询不能为空");
        R r = chatBotService.phoneNumberDependencyQuery(keyword);
        return r;
    }

    /**
     * 成语查询：根据成语查询详细信息，如：详解、出处、读音等信息
     * eg：成语一生一世
     */
    @GetMapping("/idiomsQuery/{keyword}")
    @ApiOperation("成语查询：根据成语查询详细信息，如：详解、出处、读音等信息")
    public R idiomsQuery(@PathVariable("keyword") String keyword) {
        Assert.notNull(keyword, "成语不能为空");
        R r = chatBotService.idiomsQuery(keyword);
        return r;
    }

    /**
     * 五笔/拼音：根据汉字查询拼音，笔画顺序、五笔字根
     * eg：好字的五笔/拼音
     */
    @GetMapping("/wubiOrPinyin/{keyword}")
    @ApiOperation("五笔/拼音：根据汉字查询拼音，笔画顺序、五笔字根")
    public R wubiOrPinyin(@PathVariable("keyword") String keyword) {
        Assert.notNull(keyword, "五笔/拼音不能为空");
        R r = chatBotService.wubiOrPinyin(keyword);
        return r;
    }

}
