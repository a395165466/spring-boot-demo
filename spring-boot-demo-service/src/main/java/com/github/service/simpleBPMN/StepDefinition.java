package com.github.service.simpleBPMN;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class StepDefinition implements Definition {
    /**
     * id
     */
    private String id;

    /**
     * name
     */
    private String name;

    /**
     * 实例
     */
    private Step instance;

    @Override
    public boolean valid() {
       if (StringUtils.isBlank(id)) {
           return false;
       }
       if (StringUtils.isBlank(name)) {
           return false;
       }
        return true;
    }

    @Override
    public Object getInstance() {
        if (!valid()) {
            throw new RuntimeException("StepDefinition is not valid!");
        }

        if (null != instance) {
            return instance;
        }

        Step step = Step.builder()
                .id(this.id)
                .name(this.name)
                .build();
        return step;
    }
}
