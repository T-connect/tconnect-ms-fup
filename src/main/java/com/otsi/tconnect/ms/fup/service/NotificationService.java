package com.otsi.tconnect.ms.fup.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.otsi.tconnect.ms.fup.config.CustomWebSocketHandler;

@Service
public class NotificationService {

    private final CustomWebSocketHandler webSocketHandler;

    public NotificationService(CustomWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    public void notifyUser(String userId, String message) {
        try {
            webSocketHandler.sendMessageToUser(userId, new TextMessage(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
