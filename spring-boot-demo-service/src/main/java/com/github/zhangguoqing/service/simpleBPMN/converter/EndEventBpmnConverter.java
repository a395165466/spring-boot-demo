package com.github.zhangguoqing.service.simpleBPMN.converter;

import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.EndEventElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamReader;

@Component
public class EndEventBpmnConverter implements BaseBpmnConverter {
    @Override
    public void converter(XMLStreamReader xtr, BpmnParseModel model, ProcessElement processElement) {
        String id = xtr.getAttributeValue(null, "id");
        String name = xtr.getAttributeValue(null, "name");

        EndEventElement endEventElement = new EndEventElement();
        endEventElement.setId(id);
        endEventElement.setName(name);

        processElement.getFlowItemElements().add(endEventElement);
    }
}
