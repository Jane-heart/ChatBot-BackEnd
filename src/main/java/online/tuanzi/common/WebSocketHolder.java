package online.tuanzi.common;

import online.tuanzi.controller.WebSocketServer;
import online.tuanzi.model.entity.User;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName: WebSocketHolder
 * @Author: 团子tz
 * @CreateTime: 2022/10/05 13:16
 * @Description: 保存Websocket相关信息，todo:暂时替代redis
 */
public class WebSocketHolder {
    //    public static CopyOnWriteArraySet<WebSocket> webSockets =new CopyOnWriteArraySet<>();
    public static final ConcurrentMap<Integer, WebSocketServer> webSockets = new ConcurrentHashMap<>();
    // 第一个存userId，第二个存session集合【意味着一个用户可以建立多个连接】，由于需要覆盖操作，所以自己写一个不覆盖的操作
    public static Map<Integer, List<Session>> sessionPool = new ConcurrentHashMap<>();
    // 当前登录用户，第一个存userId，第二个存User
    public static Map<Integer, User> curUserPool = new ConcurrentHashMap<>();

    public static void save(Integer userId, List<Session> session){
        /**
         * merge() 适用于两种情况。
         * 如果给定的key不存在，它就变成了put(key, value)。
         * 但是，如果key已经存在一些值，remappingFunction 可以选择合并的方式。
         */
        WebSocketHolder.sessionPool.merge(userId, session, (oldList, newList)->{
            List<Session> objects = new ArrayList<>();
            oldList.forEach((value)->{
                objects.add(value);
            });
            newList.forEach((value)->{
                if (!oldList.contains(value)){
                    objects.add(value);
                }
            });
            return objects;
        });
    }
}
