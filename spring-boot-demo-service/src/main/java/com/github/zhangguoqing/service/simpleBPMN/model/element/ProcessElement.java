package com.github.zhangguoqing.service.simpleBPMN.model.element;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProcessElement extends BaseElement {
    /**
     * 在Process中包含的流程节点集合
     */
    List<FlowItemElement> flowItemElements;
}
