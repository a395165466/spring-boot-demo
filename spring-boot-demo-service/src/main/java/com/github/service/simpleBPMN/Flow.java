package com.github.service.simpleBPMN;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Flow {
    private String id;

    private String name;

    private List<Step> stepList;

    public void execute() {

    }
}
