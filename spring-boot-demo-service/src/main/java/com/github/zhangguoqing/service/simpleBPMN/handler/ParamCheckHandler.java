package com.github.zhangguoqing.service.simpleBPMN.handler;

import com.github.zhangguoqing.service.simpleBPMN.context.FlowContext;
import com.github.zhangguoqing.service.simpleBPMN.params.CreateOrderRequest;
import com.github.zhangguoqing.service.simpleBPMN.params.CreateOrderResponse;

public class ParamCheckHandler implements Handler<Object, CreateOrderRequest, CreateOrderResponse> {
    @Override
    public boolean support(FlowContext<Object, CreateOrderRequest, CreateOrderResponse> flowContext) {
        CreateOrderRequest request = flowContext.getRequest();
        return true;
    }

    @Override
    public void execute(FlowContext<Object, CreateOrderRequest, CreateOrderResponse> flowContext) {
        flowContext.getRequest();
    }
}
