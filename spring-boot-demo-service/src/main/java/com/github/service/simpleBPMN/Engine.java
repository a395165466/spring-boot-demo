package com.github.service.simpleBPMN;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Engine {
//    @Autowired
//    private BpmnXmlFileLoad bpmnXmlFileLoad;

    public void start(String bpmnFlow) {
        if (StringUtils.isBlank(bpmnFlow)) {
            throw new RuntimeException("bpmn flow is empty");
        }

//        List<Flow> flowList = bpmnXmlFileLoad.getFlowListByFlowName("");
//        for (Flow flow : flowList) {
//            flow.execute();
//        }
    }
}
