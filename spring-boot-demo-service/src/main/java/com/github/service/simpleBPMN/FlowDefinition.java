package com.github.service.simpleBPMN;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class FlowDefinition implements Definition {
    /**
     * FlowId
     */
    private String id;

    /**
     * FlowName
     */
    private String name;

    /**
     * 实例
     */
    private Flow instance;

    /**
     * stepList
     */
    private List<StepDefinition> stepDefinitionList;

    @Override
    public boolean valid() {
        if (StringUtils.isBlank(id)) {
            return false;
        }
        if (StringUtils.isBlank(name)) {
            return false;
        }
        if (CollectionUtils.isEmpty(stepDefinitionList)) {
            return false;
        }
        return true;
    }

    @Override
    public Object getInstance() {
        if (!valid()) {
            throw new RuntimeException("FlowDefinition is not valid!");
        }

        if (null != instance) {
            return instance;
        }

        List<Step> stepList = stepDefinitionList.stream().map(stepDefinition -> {
            return (Step)stepDefinition.getInstance();
        }).collect(Collectors.toList());

        Flow flow = Flow.builder()
                        .id(this.id)
                        .name(this.name)
                        .stepList(stepList)
                        .build();
        return flow;
    }
}
