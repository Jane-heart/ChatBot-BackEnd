package online.tuanzi.constant;

import lombok.Data;
import lombok.Getter;

/**
 * @ClassName: QYKConstant
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 16:54
 * @Description: 青云客API
 */
@Getter
public enum QYKConstant {
    /**
     * 人工智能聊天：语义分析,情绪识别,大数据处理
     * eg:你好
     */
    ARTIFICIAL_INTELLIGENCE_CHAT("http://api.qingyunke.com/api.php?key=free&appid=0&msg=%s"),
    /**
     * 歌词：海量歌曲歌词查询
     * eg：歌词后来 / 歌词后来-刘若英
     */
    LYRIC("http://api.qingyunke.com/api.php?key=free&appid=0&msg=歌词%s"),
    /**
     * 天气：获取最新天气情况，气温，风向等，助你轻松出行
     * eg：天气深圳
     */
    WEATHER("http://api.qingyunke.com/api.php?key=free&appid=0&msg=天气%s"),
    /**
     * 翻译：中英文互译，清晰识别
     * eg：翻译i love you
     */
    TRANSLATE("http://api.qingyunke.com/api.php?key=free&appid=0&msg=翻译%s"),
    /**
     * IP查询：根据IP地址，查询IP所属的区域
     * eg：归属127.0.0.1
     */
    IP_LOOKUP("http://api.qingyunke.com/api.php?key=free&appid=0&msg=归属%s"),
    /**
     * 笑话：不断搜集更新网络上搞笑、幽默、内涵段子
     * eg：笑话
     */
    JOKE("http://api.qingyunke.com/api.php?key=free&appid=0&msg=笑话"),
    /**
     * 计算：科学计算器，包含加减乘除和各种数学运算
     * eg：计算1+1*2/3-4
     */
    CALCULATE("http://api.qingyunke.com/api.php?key=free&appid=0&msg=计算%s"),
    /**
     * 手机号码归属查询：根据手机号码，查询手机号码归属地信息，如省份 、城市、运营商等
     * eg：归属13430108888
     */
    PHONE_NUMBER_DEPENDENCY_QUERY("http://api.qingyunke.com/api.php?key=free&appid=0&msg=归属%s"),
    /**
     * 成语查询：根据成语查询详细信息，如：详解、出处、读音等信息
     * eg：成语一生一世
     */
    IDIOMS_QUERY("http://api.qingyunke.com/api.php?key=free&appid=0&msg=成语%s"),
    /**
     * 五笔/拼音：根据汉字查询拼音，笔画顺序、五笔字根
     * eg：好字的五笔/拼音
     */
    WUBI_OR_PINYIN("http://api.qingyunke.com/api.php?key=free&appid=0&msg=%s字的五笔/拼音");

    private String url;
//    private String baseUrl = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";

    QYKConstant(String url) {
        this.url = url;
    }


}
