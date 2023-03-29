package com.github.zhangguoqing.service.simpleBPMN.handler;

import com.github.zhangguoqing.service.simpleBPMN.context.FlowContext;

public interface Handler<T,Q,R> {
    /**
     * 是否执行该handler
     * @param flowContext
     * @return
     */
    public boolean support(FlowContext<T,Q,R> flowContext);

    /**
     * 执行该handler
     * @param flowContext
     */
    public void execute(FlowContext<T,Q,R> flowContext);
}
