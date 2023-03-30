package com.github.zhangguoqing.service.simpleBPMN.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SequenceFlowElement extends FlowItemElement {
    /**
     * 源元素
     */
    private BaseElement sourceRef;

    /**
     * 目标元素
     */
    private BaseElement targetRef;
}
