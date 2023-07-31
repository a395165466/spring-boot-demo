package com.github.zhangguoqing.service.simpleBPMN.converter;

import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.EndEventElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import org.springframework.stereotype.Component;

import javax.swing.tree.TreeNode;
import javax.xml.stream.XMLStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

@Component
public class EndEventBpmnConverter implements BaseBpmnConverter {
    @Override
    public void converter(XMLStreamReader xtr, BpmnParseModel model, ProcessElement processElement) {
        String id = xtr.getAttributeValue(null, "id");
        String name = xtr.getAttributeValue(null, "name");

        EndEventElement endEventElement = new EndEventElement();
        endEventElement.setId(id);
        endEventElement.setName(name);

        processElement.getFlowItemElements().add(endEventElement);
//
//        int[] nums = new int[]{1,2,3,-4,-2};
//        Arrays.stream(nums).boxed().sorted((o1, o2) -> Math.abs(o1) - Math.abs(o2)).mapToInt(Integer::intValue).toArray();
//
//        Deque<Character> stack = new LinkedList<>();
//        stack.push();//插入队头
//        stack.pop();//删除队头
//
//        stack.add();//添加队尾
//        stack.addFirst();//添加队头
//        stack.addLast();//添加队尾
//
//        stack.removeFirst();//删除队头
//        stack.removeLast();//删除队尾
//
//        stack.peekFirst();//获取但不删除队头
//        stack.peekLast();//获取但不删除队尾
    }

    @Override
    public String getElementType() {
        return EndEventBpmnConverter.class.getName();
    }
}
