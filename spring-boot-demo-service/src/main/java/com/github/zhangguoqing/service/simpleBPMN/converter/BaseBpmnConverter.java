package com.github.zhangguoqing.service.simpleBPMN.converter;

import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;

import javax.swing.tree.TreeNode;
import javax.xml.stream.XMLStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public interface BaseBpmnConverter {
    void converter(XMLStreamReader xtr, BpmnParseModel model, ProcessElement processElement);

    String getElementType();



}
