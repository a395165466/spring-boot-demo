package com.github.zhangguoqing.service.simpleBPMN.converter;

import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ExclusiveGatewayElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamReader;

@Component
public class ExclusiveGateWayConverter implements BaseBpmnConverter {
    @Override
    public void converter(XMLStreamReader xtr, BpmnParseModel model, ProcessElement processElement) {
        ExclusiveGatewayElement element = new ExclusiveGatewayElement();

        String id = xtr.getAttributeValue(null, "id");
        String name = xtr.getAttributeValue(null, "name");

        element.setId(id);
        element.setName(name);

        processElement.getFlowItemElements().add(element);
    }
}
