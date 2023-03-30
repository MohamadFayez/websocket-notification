package com.mdi.websocket.payload;

import lombok.Data;

@Data
public class ResponseMessage {
    private String content;

    public ResponseMessage() {
    }

    public ResponseMessage(String content) {
        this.content = content;
    }
}
