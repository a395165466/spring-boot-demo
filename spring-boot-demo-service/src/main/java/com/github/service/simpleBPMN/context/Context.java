package com.github.service.simpleBPMN.context;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Context<Q,R> {
    /**
     * 请求入参
     */
    private Q request;

    /**
     * 响应
     */
    private R response;
}
