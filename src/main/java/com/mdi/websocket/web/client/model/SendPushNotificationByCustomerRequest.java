/*
 * Copyright (c) 2023.
 */

package com.mdi.websocket.web.client.model;

import com.mdi.websocket.constant.ErrorKeys;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import net.atos.mdi.commons.annotations.CustomNotNull;
import net.atos.mdi.commons.model.NotificationTemplateField;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendPushNotificationByCustomerRequest {

    @Schema(description = "customer Id.")
    @CustomNotNull(error = ErrorKeys.BLANK_CUSTOMER_NUMBER)
    private Long customerId;

    @Schema(description = "Campaign name.")
    private String templateId;

    @Schema(description = "to replace fields with values.")
    private List<SubscriberPushField> templateParametersList;

    @Schema(description = "to receive list of Key-Value to be passed to D-Tech.")
    private List<PushContentDataList> pushContentDataList;

    @Schema(description = "push content name.")
    @Builder.Default
    private String pushContentName = " ";

    @Schema(description = "notification title.")
    private String notificationTitle;

    @Schema(description = "notification body.")
    private String notificationBody;

    @Schema(description = "0: queue , 1: instant")
    @CustomNotNull(error = ErrorKeys.BLANK_INSTANT_DELIVERY)
    private Integer instantDelivery;
}