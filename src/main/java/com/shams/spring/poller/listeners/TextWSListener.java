package com.shams.spring.poller.listeners;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * Created by shams on 11/21/16.
 */
public class TextWSListener implements PollerListener {

    private WebSocketSession webSocketSession;
    private String id;

    public TextWSListener(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
        this.id = webSocketSession.getId();
    }

    @Override
    public void listen() {
        if (webSocketSession.isOpen()) {
            TextMessage textMessage = new TextMessage("Hello ---  " + webSocketSession.getId());
            try {
                webSocketSession.sendMessage(textMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String id() {
        return this.webSocketSession.getId();
    }

    @Override
    public void stop() {
        if (webSocketSession.isOpen()) {
            try {
                webSocketSession.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextWSListener that = (TextWSListener) o;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
