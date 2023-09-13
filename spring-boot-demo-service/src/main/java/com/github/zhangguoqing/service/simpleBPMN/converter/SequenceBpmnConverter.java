package com.github.zhangguoqing.service.simpleBPMN.converter;

import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.BaseElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.FlowItemElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.SequenceFlowElement;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamReader;
import java.util.List;
import java.util.Objects;

@Component
public class SequenceBpmnConverter implements BaseBpmnConverter {
    public static final String ELEMENT_TYPE = "sequenceFlow";

    @Override
    public void converter(XMLStreamReader xtr, BpmnParseModel model, ProcessElement processElement) {
        SequenceFlowElement element = new SequenceFlowElement();

        String id = xtr.getAttributeValue(null, "id");
        String name = xtr.getAttributeValue(null, "name");
        String sourceRef = xtr.getAttributeValue(null, "sourceRef");
        String targetRef = xtr.getAttributeValue(null, "targetRef");

        element.setId(id);
        element.setName(name);

        //sourceRef和targetRef要从processElement取出来
        if (Objects.nonNull(getItemElementByType(processElement.getFlowItemElements(), sourceRef))) {
            element.setSourceRef(getItemElementByType(processElement.getFlowItemElements(), sourceRef));
        }
        if (Objects.nonNull(getItemElementByType(processElement.getFlowItemElements(), targetRef))) {
            element.setTargetRef(getItemElementByType(processElement.getFlowItemElements(), targetRef));
        }
        processElement.getFlowItemElements().add(element);
    }

    private BaseElement getItemElementByType(List<FlowItemElement> elements, String elementType) {
        if (CollectionUtils.isEmpty(elements)) {
            return null;
        }
        for (FlowItemElement itemElement : elements) {
            if (elementType.equals(itemElement.getId())) {
                return itemElement;
            }
        }

        return null;
    }

    @Override
    public String getElementType() {
        return ELEMENT_TYPE;
    }
}
