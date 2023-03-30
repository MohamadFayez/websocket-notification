package com.mdi.websocket.service;

import com.mdi.websocket.payload.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebsocketService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebsocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(final String message) {
        ResponseMessage res = new ResponseMessage(message);
        messagingTemplate.convertAndSend("/receive/message", res);
    }

    public void sendPrivateMessage(final String message, final String receiver) {
        ResponseMessage res = new ResponseMessage(message);
        messagingTemplate.convertAndSend("/receive/private-message/" + receiver, res);
    }

    public void sendNotification(final String message) {
        ResponseMessage res = new ResponseMessage(message);
        messagingTemplate.convertAndSend("/receive/global-notification", res);
    }

    public void sendPrivateNotification(final String message, final String receiver) {
        ResponseMessage res = new ResponseMessage(message);
        messagingTemplate.convertAndSend("/receive/private-notification/" + receiver, res);
    }
}
