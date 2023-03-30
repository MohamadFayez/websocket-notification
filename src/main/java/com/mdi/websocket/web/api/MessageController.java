/*
 * Copyright (c) 2023.
 */

package com.mdi.websocket.web.api;

import com.mdi.websocket.payload.Message;
import com.mdi.websocket.payload.ResponseMessage;
import com.mdi.websocket.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {
    @Autowired
    WebsocketService wsService;

    @MessageMapping("/message")
    public ResponseMessage getMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);
        wsService.sendMessage(message.getMessageContent());
        System.out.println("received message");
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-message")
    public ResponseMessage getPrivateMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);
        wsService.sendPrivateMessage(message.getMessageContent(), message.getReceiver());
        System.out.println("received message private from: " +  message.getReceiver());
        return new ResponseMessage(HtmlUtils.htmlEscape("Sending personal message to user" +  message.getReceiver() + ": " + message.getMessageContent()));
    }

}
