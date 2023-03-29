package com.github.zhangguoqing.service.simpleBPMN;

import com.github.zhangguoqing.service.simpleBPMN.context.FlowContext;
import com.github.zhangguoqing.service.simpleBPMN.handler.Handler;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class Step {
    /**
     * id
     */
    private String id;

    /**
     * name
     */
    private String name;

    /**
     * handler
     */
    private Handler stepHandler;

    public <T, Q, R> void execute(FlowContext<T, Q, R> flowContext) {
        if (!Objects.isNull(stepHandler)) {
            return;
        }
        stepHandler.execute(flowContext);
    }
}
