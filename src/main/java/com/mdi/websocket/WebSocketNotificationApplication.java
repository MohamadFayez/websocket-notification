package com.mdi.websocket;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import net.atos.mdi.commons.config.BasicAuthSecretsConfigurations;
import net.atos.mdi.commons.config.ErrorCodesLoader;
import net.atos.mdi.commons.config.OpenAPIConfig;
import net.atos.mdi.commons.config.ServletConfig;
import net.atos.mdi.commons.config.WebSecurityConfig;
import net.atos.mdi.commons.web.exception.RestErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import({RestErrorHandler.class,
        OpenAPIConfig.class,
        ServletConfig.class,
        ErrorCodesLoader.class,
        WebSecurityConfig.class,
        BasicAuthSecretsConfigurations.class})

@OpenAPIDefinition(info = @Info(title = "Websocket Notification System APIs", version = "1.0.0", description = "API Document describes how to integrate with The Socket Management System' APIs to send Single/Bulk Notifications (SMSs, Emails, and Mobile Push Notifications)."))
@ComponentScan(basePackages = {"net.atos.mdi.commons", "net.atos.mdi.commons.connectors.util", "com.mdi.websocket"})

public class WebSocketNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketNotificationApplication.class, args);
    }

}
