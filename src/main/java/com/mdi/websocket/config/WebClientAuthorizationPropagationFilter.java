/*
 * Copyright (c) 2023.
 */

package com.mdi.websocket.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;

import net.atos.mdi.commons.model.AuthorizationDetails;
import reactor.core.publisher.Mono;

public class WebClientAuthorizationPropagationFilter implements ExchangeFilterFunction {

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {

        ClientRequest newRequest = ClientRequest.from(request)
                .header(HttpHeaders.AUTHORIZATION,
                        ((AuthorizationDetails) SecurityContextHolder.getContext()
                                .getAuthentication().getPrincipal()).getToken()).build();
        return next.exchange(newRequest);
    }

}
