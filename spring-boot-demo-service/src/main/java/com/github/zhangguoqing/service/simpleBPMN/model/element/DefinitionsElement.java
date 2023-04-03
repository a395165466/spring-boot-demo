package com.github.zhangguoqing.service.simpleBPMN.model.element;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefinitionsElement extends BaseElement {

    private String typeLanguage;

    private String expressionLanguage;

    private String targetNamespace;
}
