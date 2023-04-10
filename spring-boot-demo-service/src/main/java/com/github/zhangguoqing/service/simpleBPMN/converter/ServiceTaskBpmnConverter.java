package com.github.zhangguoqing.service.simpleBPMN.converter;

import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ServiceTaskElement;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamReader;

@Component
public class ServiceTaskBpmnConverter implements BaseBpmnConverter{
    @Override
    public void converter(XMLStreamReader xtr, BpmnParseModel model, ProcessElement processElement) {
        ServiceTaskElement element = new ServiceTaskElement();

        String id = xtr.getAttributeValue(null, "id");
        String name = xtr.getAttributeValue(null, "name");

        element.setId(id);
        element.setName(name);

        processElement.getFlowItemElements().add(element);
    }

    @Override
    public String getElementType() {
        return ServiceTaskBpmnConverter.class.getName();
    }
}
