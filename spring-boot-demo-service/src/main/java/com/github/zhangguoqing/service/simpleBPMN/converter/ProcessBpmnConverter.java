package com.github.zhangguoqing.service.simpleBPMN.converter;

import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;

@Component
public class ProcessBpmnConverter implements BaseBpmnConverter {
    @Override
    public void converter(XMLStreamReader xtr, BpmnParseModel model, ProcessElement processElement) {
        String id = xtr.getAttributeValue(null, "id");
        String name = xtr.getAttributeValue(null, "name");

        processElement.setId(id);
        processElement.setName(name);

        model.setId(id);
        model.setName(name);
        model.setProcessElementList(Lists.newArrayList(processElement));
    }

    @Override
    public String getElementType() {
        return ProcessBpmnConverter.class.getName();
    }
}
