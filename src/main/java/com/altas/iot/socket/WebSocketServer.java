package com.altas.iot.socket;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: WebSocketServer
 * @Description: WebSocket公共类
 * @Author: LiHanzhang
 * @Date: 2024-06-18 10:32
 * @Email: 9255520@gmail.com
 * @Version: 1.0
 **/
@Slf4j
@Component
@ServerEndpoint("/iot/websocket/{addressNo}") //WebSocket客户端建立连接的地址
public class WebSocketServer {

    private Session session;

    private String addressNo;

    /**
     * 在线人数
     */
    public static int onlineNumber = 0;

    @OnOpen
    public void onOpen(@PathParam("addressNo") String addressNo, Session session) throws SQLException {
        this.session = session;
        this.addressNo = addressNo;
        WebSocketManager.sentToUser(session, "WebSocket is connected!");
        WebSocketManager.addWebSocketServer(this);

        log.info("与SessionId：{}建立连接", session.getId() + "，楼层id：" + addressNo);

    }

    //发送数据
//        WebSocketManager.sentToDevice("1sss", "deviceType");
    @OnClose
    public void onClose() {
        WebSocketManager.removeWebSocketServer(this);
        log.info("SessionId:{},WebSocket连接关闭", this.getSession().getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自SessionId:{}的消息:{}", session.getId(), message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Session:{}的WebSocket发生错误", session.getId(), error);
    }

    public Session getSession() {
        return session;
    }

    public String getSessionId() {
        return addressNo + "-" + session.getId();
    }

    public String getUserName() {
        return addressNo;
    }
}
