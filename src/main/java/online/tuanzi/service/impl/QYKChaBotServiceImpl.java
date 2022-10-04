package online.tuanzi.service.impl;

import online.tuanzi.bean.vo.QYKResult;
import online.tuanzi.bean.vo.R;
import online.tuanzi.constant.QYKConstant;
import online.tuanzi.service.QYKChaBotService;
import online.tuanzi.utils.HttpClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: QYKChaBotServiceImpl
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 17:49
 * @Description: 想想此类的作用，写在这里吧。
 */
@Service
public class QYKChaBotServiceImpl implements QYKChaBotService {

    private HttpClient httpClient;

    public QYKChaBotServiceImpl(RestTemplate restTemplate){
        httpClient = new HttpClient(restTemplate);
    }

    @Override
    public R artificialIntelligenceChat(String keyword) {
        QYKResult result = httpClient.getBody(QYKConstant.ARTIFICIAL_INTELLIGENCE_CHAT.getUrl(), QYKResult.class, keyword);
        return R.ok().data(result.getContent());
    }

    @Override
    public R lyric(String keyword) {
        QYKResult result = httpClient.getBody(QYKConstant.LYRIC.getUrl(), QYKResult.class, keyword);
        return R.ok().data(result);
    }

    @Override
    public R weather(String keyword) {
        QYKResult result = httpClient.getBody(QYKConstant.WEATHER.getUrl(), QYKResult.class, keyword);
        return R.ok().data(result);
    }

    @Override
    public R translate(String keyword) {
        QYKResult result = httpClient.getBody(QYKConstant.TRANSLATE.getUrl(), QYKResult.class, keyword);
        return R.ok().data(result);
    }

    @Override
    public R ipLookup(String keyword) {
        QYKResult result = httpClient.getBody(QYKConstant.IP_LOOKUP.getUrl(), QYKResult.class, keyword);
        return R.ok().data(result);
    }

    @Override
    public R joke() {
        QYKResult result = httpClient.getBody(QYKConstant.JOKE.getUrl(), QYKResult.class, null);
        return R.ok().data(result);
    }

    @Override
    public R calculate(String keyword) {
        QYKResult result = httpClient.getBody(QYKConstant.CALCULATE.getUrl(), QYKResult.class, keyword);
        return R.ok().data(result);
    }

    @Override
    public R phoneNumberDependencyQuery(String keyword) {
        QYKResult result = httpClient.getBody(QYKConstant.PHONE_NUMBER_DEPENDENCY_QUERY.getUrl(), QYKResult.class, keyword);
        return R.ok().data(result);
    }

    @Override
    public R idiomsQuery(String keyword) {
        QYKResult result = httpClient.getBody(QYKConstant.IDIOMS_QUERY.getUrl(), QYKResult.class, keyword);
        return R.ok().data(result);
    }

    @Override
    public R wubiOrPinyin(String keyword) {
        QYKResult result = httpClient.getBody(QYKConstant.WUBI_OR_PINYIN.getUrl(), QYKResult.class, keyword);
        return R.ok().data(result);
    }
}
