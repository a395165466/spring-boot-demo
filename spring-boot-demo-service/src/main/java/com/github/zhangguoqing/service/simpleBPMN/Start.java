package com.github.zhangguoqing.service.simpleBPMN;

import com.github.zhangguoqing.service.simpleBPMN.context.FlowContext;
import com.github.zhangguoqing.service.simpleBPMN.handler.ParamCheckHandler;
import com.github.zhangguoqing.service.simpleBPMN.params.CreateOrderRequest;
import com.github.zhangguoqing.service.simpleBPMN.params.CreateOrderResponse;

public class Start {
    public static void main(String[] args) {
        FlowContext<Object, CreateOrderRequest, CreateOrderResponse> context = new FlowContext<>();
        ParamCheckHandler activity = new ParamCheckHandler();
        activity.support(context);
        activity.execute(context);
    }
}
