package online.tuanzi.service.impl;

import online.tuanzi.common.R;
import online.tuanzi.service.ChatBotService;
import online.tuanzi.service.QYKChaBotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: ChatBotServiceImpl
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 17:31
 * @Description: 想想此类的作用，写在这里吧。
 */
@Service
public class ChatBotServiceImpl implements ChatBotService {
    //todo:后期改成动态的，即不只是青云客的接口
    @Resource
    private QYKChaBotService qykChaBotService;

    @Override
    public R artificialIntelligenceChat(String keyword) {
        R r = qykChaBotService.artificialIntelligenceChat(keyword);
        return r;
    }

    @Override
    public R lyric(String keyword) {
        R r = qykChaBotService.lyric(keyword);
        return null;
    }

    @Override
    public R weather(String keyword) {
        R r = qykChaBotService.weather(keyword);
        return null;
    }

    @Override
    public R translate(String keyword) {
        R r = qykChaBotService.translate(keyword);
        return null;
    }

    @Override
    public R ipLookup(String keyword) {
        R r = qykChaBotService.ipLookup(keyword);
        return null;
    }

    @Override
    public R joke() {
        R r = qykChaBotService.joke();
        return null;
    }

    @Override
    public R calculate(String keyword) {
        R r = qykChaBotService.calculate(keyword);
        return null;
    }

    @Override
    public R phoneNumberDependencyQuery(String keyword) {
        R r = qykChaBotService.phoneNumberDependencyQuery(keyword);
        return null;
    }

    @Override
    public R idiomsQuery(String keyword) {
        R r = qykChaBotService.idiomsQuery(keyword);
        return null;
    }

    @Override
    public R wubiOrPinyin(String keyword) {
        R r = qykChaBotService.wubiOrPinyin(keyword);
        return null;
    }
}
