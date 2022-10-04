package online.tuanzi.service;

import online.tuanzi.bean.vo.R;

/**
 * @ClassName: QYKChaBotService
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 17:48
 * @Description: 青云客接口
 */
public interface QYKChaBotService {
    R artificialIntelligenceChat(String keyword);

    R lyric(String keyword);

    R weather(String keyword);

    R translate(String keyword);

    R ipLookup(String keyword);

    R joke();

    R calculate(String keyword);

    R phoneNumberDependencyQuery(String keyword);

    R idiomsQuery(String keyword);

    R wubiOrPinyin(String keyword);
}
