package com.github.zhangguoqing.service.simpleBPMN.model;

import com.github.zhangguoqing.service.simpleBPMN.model.element.DefinitionsElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 记录在bpmn流程引擎文件中的元素集合
 */
@Getter
@Setter
public class BpmnParseModel {

    private DefinitionsElement definitionElement;

    private List<ProcessElement> processElementList;
}
