package com.mdi.websocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdi.websocket.constant.Constants;
import com.mdi.websocket.web.client.ApigeeAuthenticationClient;
import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

@Service
public class WebsocketChannelInterceptor implements ChannelInterceptor {

    private final Logger log = LoggerFactory.getLogger(WebsocketChannelInterceptor.class);
    @Autowired
    private ApigeeAuthenticationClient apigeeAuthClient;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {

            String authorization = accessor.getFirstNativeHeader(Constants.Authorization);
            String pKey = accessor.getFirstNativeHeader(Constants.PKey);
            String ts = accessor.getFirstNativeHeader(Constants.TS);
            String deviceId = accessor.getFirstNativeHeader(Constants.DeviceId);

            try {
                apigeeAuthClient.authUsingApigee(authorization, deviceId, pKey, ts);
                accessor.setUser(new UserPrincipal(deviceId));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        if (StompCommand.SEND.equals(accessor.getCommand())) {

        }
        return message;
    }


    @Override
    public void postSend(Message<?> msg, MessageChannel mc, boolean bln) {
        log.info("In postSend");
    }

    @Override
    public void afterSendCompletion(Message<?> msg, MessageChannel mc, boolean bln, Exception excptn) {
        log.info("In afterSendCompletion");
    }

    @Override
    public boolean preReceive(MessageChannel mc) {
        log.info("In preReceive");
        return true;
    }
}