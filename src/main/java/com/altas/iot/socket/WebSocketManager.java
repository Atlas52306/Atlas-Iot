package com.altas.iot.socket;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName: WebSocketManager
 * @Description: WebSocket管理模块
 * @Author: LiHanzhang
 * @Date: 2024-06-19 09:38
 * @Email: 9255520@gmail.com
 * @Version: 1.0
 **/
@Slf4j
public class WebSocketManager {

    private final static CopyOnWriteArraySet<WebSocketServer> webSocketServerSet = new CopyOnWriteArraySet<>();

    private final static ConcurrentHashMap<String, WebSocketServer> webSocketServerMap = new ConcurrentHashMap<>();


    /**
     * 模糊查询map集合
     * @param deviceType
     * @return
     */
    public static ConcurrentHashMap<String, WebSocketServer> queryWebSocketServerMap(String deviceType) {
        ConcurrentHashMap<String, WebSocketServer> map = new ConcurrentHashMap<>();
        //遍历集合
        for (String key : webSocketServerMap.keySet()) {
            //根据设备类型模糊查询key
            if (key.contains(deviceType)) {
                System.out.println("获取到key：" + key);
                map.put(key, webSocketServerMap.get(key));
            }
        }
        return map;
    }
    public static void addWebSocketServer(WebSocketServer webSocketServer){
        if (webSocketServer != null){
            webSocketServerSet.add(webSocketServer);
            webSocketServerMap.put(webSocketServer.getSessionId(), webSocketServer);
        }
    }

    public static void removeWebSocketServer(WebSocketServer webSocketServer){
        webSocketServerSet.remove(webSocketServer);
        webSocketServerMap.remove(webSocketServer.getSessionId());
//        webSocketServerMap.remove(webSocketServer.getUserName()+"-"+webSocketServer.getSessionId());
    }

    /**
     * 通过deviceAddressNo发送消息给特定用户
     * @param
     * @param msg
     */
    public static void sentToDeviceAddressNo(String addressNo, String msg){
        for (String key : webSocketServerMap.keySet()) {
            //根据设备类型模糊查询key
            if (key.contains(addressNo)) {
                System.out.println("获取到key：" + key);
                //获取session
                Session session = webSocketServerMap.get(key).getSession();
                //发送
                sentToUser(session, msg);
            }
        }
    }

    /**
     * 通过SessionId发送消息给特定用户
     * @param
     * @param msg
     */
    public static void sentToUser(String sessionId, String msg){
        Session session = webSocketServerMap.get(sessionId).getSession();
        sentToUser(session, msg);
    }

    /**
     * 通过Session发送消息给特定用户
     * @param session
     * @param msg
     */
    public static void sentToUser(Session session, String msg){
        if (session == null){
            log.error("不存在该Session，无法发送消息");
            return;
        }
        session.getAsyncRemote().sendText(msg);
    }

    /**
     * 发送消息给所有用户
     * @param msg
     */
    public static void sentToAllUser(String msg){
        for (WebSocketServer webSocketServer : webSocketServerSet) {
            sentToUser(webSocketServer.getSession(), msg);
        }
        log.info("向所有用户发送WebSocket消息完毕，消息：{}", msg);
    }
}
