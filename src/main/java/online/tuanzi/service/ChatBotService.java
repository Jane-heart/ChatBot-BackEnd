package online.tuanzi.service;

import online.tuanzi.common.R;

/**
 * @ClassName: ChatBotService
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 17:31
 * @Description: 想想此类的作用，写在这里吧。
 */
public interface ChatBotService {
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
