package com.github.service.simpleBPMN;

import com.github.service.simpleBPMN.context.FlowContext;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
public class Flow {
    private String id;

    private String name;

    private List<Step> stepList;

    public <T, Q, R> void execute(FlowContext<T, Q, R> flowContext) {
        if (CollectionUtils.isEmpty(stepList)) {
            return;
        }
        for (Step step : stepList) {
            step.execute(flowContext);
        }
    }
}
