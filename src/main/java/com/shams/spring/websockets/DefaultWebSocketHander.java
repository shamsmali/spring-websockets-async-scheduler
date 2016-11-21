package com.shams.spring.websockets;

import com.shams.spring.poller.Poller;
import com.shams.spring.poller.listeners.TextWSListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by shams on 11/21/16.
 */
@Component
public class DefaultWebSocketHander extends TextWebSocketHandler {

    @Autowired
    private Poller poller;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session.getId() + " you are connected ");
        TextMessage textMessage =  new TextMessage("Welcome -- " + session.getId());
        session.sendMessage(textMessage);

        poller.addPollerListener(new TextWSListener(session));

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println(session.getId() + " you are disconnected ");
        poller.removePollerListener(session.getId());
    }
}
