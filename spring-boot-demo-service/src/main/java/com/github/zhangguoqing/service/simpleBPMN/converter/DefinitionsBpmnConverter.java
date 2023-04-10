package com.github.zhangguoqing.service.simpleBPMN.converter;

import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.DefinitionsElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamReader;

@Component
public class DefinitionsBpmnConverter implements BaseBpmnConverter {
    @Override
    public void converter(XMLStreamReader xtr, BpmnParseModel model, ProcessElement processElement) {
        String name = xtr.getLocalName();
        String typeLanguage = xtr.getAttributeValue(null, "typeLanguage");
        String expressionLanguage = xtr.getAttributeValue(null, "expressionLanguage");
        String targetNamespace = xtr.getAttributeValue(null, "targetNamespace");

        DefinitionsElement definitionsElement = new DefinitionsElement();
        definitionsElement.setTypeLanguage(typeLanguage);
        definitionsElement.setTargetNamespace(targetNamespace);
        definitionsElement.setExpressionLanguage(expressionLanguage);

        model.setDefinitionElement(definitionsElement);
    }

    @Override
    public String getElementType() {
        return DefinitionsBpmnConverter.class.getName();
    }
}
