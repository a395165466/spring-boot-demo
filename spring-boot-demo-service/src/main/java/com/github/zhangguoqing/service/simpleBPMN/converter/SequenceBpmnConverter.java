package com.github.zhangguoqing.service.simpleBPMN.converter;

import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.SequenceFlowElement;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamReader;

@Component
public class SequenceBpmnConverter implements BaseBpmnConverter {
    @Override
    public void converter(XMLStreamReader xtr, BpmnParseModel model, ProcessElement processElement) {
        SequenceFlowElement element = new SequenceFlowElement();

        String id = xtr.getAttributeValue(null, "id");
        String name = xtr.getAttributeValue(null, "name");

        element.setId(id);
        element.setName(name);

        processElement.getFlowItemElements().add(element);
    }
}
