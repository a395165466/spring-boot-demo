package com.github.zhangguoqing.service.simpleBPMN.converter;

import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.FlowItemElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamReader;
import java.util.Collection;
import java.util.List;

@Component
public class StartEventBpmnConverter implements BaseBpmnConverter {
    public static final String ELEMENT_TYPE = "startEvent";

    @Override
    public void converter(XMLStreamReader xtr, BpmnParseModel model, ProcessElement processElement) {
        String id = xtr.getAttributeValue(null, "id");
        String name = xtr.getAttributeValue(null, "name");

        FlowItemElement startEventElement = new FlowItemElement();
        startEventElement.setId(id);
        startEventElement.setName(name);

        processElement.getFlowItemElements().add(startEventElement);
    }

    @Override
    public String getElementType() {
        return ELEMENT_TYPE;
    }
}
