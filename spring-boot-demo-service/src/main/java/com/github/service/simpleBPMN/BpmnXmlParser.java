package com.github.service.simpleBPMN;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 纯粹对xml文件的解析
 */
@Component
public class BpmnXmlParser {
    public Map<String, Flow> parse(List<String> files, ApplicationContext applicationContext) {
        Map<String, Flow> flowMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(files)) {
            return flowMap;
        }
        //解析xml文件为FlowDefinition列表
        List<FlowDefinition> flowDefinitions = Lists.newArrayList();
        for (String file : files) {
            flowDefinitions.addAll(parseFile(file));
        }
        for (FlowDefinition flowDefinition : flowDefinitions) {
            Flow flow = (Flow)flowDefinition.getInstance();
            //判断是否有重复的flowId
            if (Objects.nonNull(flowMap.get(flow.getId()))) {
                throw new RuntimeException("duplicate flowId: " + flow.getId());
            }
            flowMap.put(flow.getId(), flow);
        }
        return flowMap;
    }

    public List<FlowDefinition> parseFile(String fileName) {
        List<FlowDefinition> flowDefinitions = Lists.newArrayList();
        if (StringUtils.isBlank(fileName)) {
            return flowDefinitions;
        }
        //解析
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File(fileName));
            Element rootElement = document.getRootElement();
            String rootName = rootElement.getName();

            System.out.println("解析xml文件" + rootName);
        } catch (DocumentException e) {
            // TODO: 2023/3/11 记录错误日志 
            throw new RuntimeException(e);
        }

        return flowDefinitions;
    }
}
