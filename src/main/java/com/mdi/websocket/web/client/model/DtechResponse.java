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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtechResponse {

    private String status;
    private String description;

}
