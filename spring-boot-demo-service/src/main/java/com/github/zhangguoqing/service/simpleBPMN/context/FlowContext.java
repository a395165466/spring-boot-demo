package com.github.zhangguoqing.service.simpleBPMN.context;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowContext<T, Q, R> extends Context<Q, R> {
    private T param;
}
