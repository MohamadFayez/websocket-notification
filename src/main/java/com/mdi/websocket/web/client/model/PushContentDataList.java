/*
 * Copyright (c) 2023.
 */

package com.mdi.websocket.web.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushContentDataList {
    private String id;
    private String value;
}
