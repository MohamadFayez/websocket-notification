package com.mdi.websocket.payload;

import lombok.Data;

@Data
public class Message {
    private String messageContent;
    private String receiver;
}

