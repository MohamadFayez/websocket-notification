/*
 * Copyright (c) 2023.
 */

package com.mdi.websocket.web.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClientTrustedDeviceResponse {
	private String pushId;

	private String customerId;

	private String clientId;

	private String deviceId;

	private String clientPublicKey;

	private String apigeePublicKey;

	private String secretKey;
}
