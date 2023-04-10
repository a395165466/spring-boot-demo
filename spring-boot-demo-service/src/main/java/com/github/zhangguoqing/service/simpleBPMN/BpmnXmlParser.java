package com.github.zhangguoqing.service.simpleBPMN;

import com.github.zhangguoqing.service.flowEngine.BusInventoryProcessor;
import com.github.zhangguoqing.service.simpleBPMN.converter.*;
import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.ProcessElement;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 纯粹对xml文件的解析
 */
@Component
public class BpmnXmlParser {
    /**
     * converterMap
     */
    private Map<String, BaseBpmnConverter> converterMap = Maps.newHashMap();

    @Autowired
    private DefinitionsBpmnConverter definitionsBpmnConverter;

    @Autowired
    private ProcessBpmnConverter processBpmnConverter;

    public Map<String, Flow> parse(List<String> files, ApplicationContext applicationContext) {
        Map<String, Flow> flowMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(files)) {
            return flowMap;
        }

        //获取所有的converter
        addBpmnConverter2MapByReflection(applicationContext);

        //解析xml文件为FlowDefinition列表
        List<FlowDefinition> flowDefinitions = Lists.newArrayList();
        for (String file : files) {
            flowDefinitions.addAll(parseFileByXml(file));
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

    public List<FlowDefinition> parseFileByXml(String fileName) {
        BpmnParseModel model = new BpmnParseModel();
        ProcessElement process = new ProcessElement();
        List<FlowDefinition> flowDefinitions = Lists.newArrayList();

        File file = new File(fileName);
        XMLInputFactory xmlFactory  = XMLInputFactory.newInstance();
        try {
            InputStream in = new FileInputStream(file);
            XMLStreamReader reader = xmlFactory.createXMLStreamReader(in);
            while(reader.hasNext()){
                if (reader.next() == XMLStreamReader.START_ELEMENT) {
                    String localName = reader.getLocalName();
                    System.out.printf("%s\t\t\t", localName);

                    if ("definitions".equals(localName)) {
                        definitionsBpmnConverter.converter(reader, model, process);
                    } else if ("process".equals(localName)) {
                        processBpmnConverter.converter(reader, model, process);
                    } else if (converterMap.containsKey(localName)) {
                        //Process里面包含的元素
                        BaseBpmnConverter converter = converterMap.get(localName);
                        converter.converter(reader, model, process);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }

        return flowDefinitions;
    }
    public List<FlowDefinition> parseFile(String fileName) {
        return null;
//        List<FlowDefinition> flowDefinitions = Lists.newArrayList();
//        if (StringUtils.isBlank(fileName)) {
//            return flowDefinitions;
//        }
//        SAXParser saxParser = null;
//        try {
//            saxParser = SAXParserFactory.newInstance().newSAXParser();
////            saxParser.parse(null, );
//        } catch (ParserConfigurationException e) {
//            throw new RuntimeException(e);
//        } catch (SAXException e) {
//            throw new RuntimeException(e);
//        }
//        saxParser.
//
//        //解析
//        SAXReader saxReader = new SAXReader();
//        try {
//            Document document = saxReader.read(new File(fileName));
//            Element rootElement = document.getRootElement();
//            String rootName = rootElement.getName();
//            List<Element> elements = rootElement.elements();
//            for (Element element : elements) {
//                String elementName = element.getName();
//                String text = element.getText();
//                String value = element.getStringValue();
//                Attribute tempAttr = element.attribute("name");
//                if (Objects.nonNull(tempAttr)) {
//                    String attrName = tempAttr.getName();
//                    String attrValue = tempAttr.getValue();
//                    System.out.println("1");
//                     List<Element> subElements = element.elements();
//                    for (Element subElement : subElements) {
//                        String subElementName = subElement.getName();
//                        List<Element> gElements = subElement.elements();
//                        for (Element gElement :  gElements) {
//                            String gElementName = gElement.getName();
//                            System.out.println("1");
//                        }
//                        System.out.println("1");
//                    }
//                }
//                if (!"flows".equalsIgnoreCase(elementName) && !"flow".equalsIgnoreCase(elementName) && !"step".equalsIgnoreCase(elementName)) {
//                    continue;
//                }
//
////                String attrName = tempAttr.getName();
//            }
//            System.out.println("解析xml文件" + rootName);
//        } catch (DocumentException e) {
//            // TODO: 2023/3/11 记录错误日志
//            throw new RuntimeException(e);
//        }
//
//        return flowDefinitions;
    }

    /**
     * 将所有的converter保存到内存中
     */
    public void addBpmnConverter2Map() {
        converterMap.put(DefinitionsBpmnConverter.class.getName(), new DefinitionsBpmnConverter());
        converterMap.put(EndEventBpmnConverter.class.getName(), new EndEventBpmnConverter());
        converterMap.put(StartEventBpmnConverter.class.getName(), new StartEventBpmnConverter());
        converterMap.put(SequenceBpmnConverter.class.getName(), new SequenceBpmnConverter());
        converterMap.put(ServiceTaskBpmnConverter.class.getName(), new ServiceTaskBpmnConverter());
        converterMap.put(ProcessBpmnConverter.class.getName(), new ProcessBpmnConverter());
        converterMap.put(ExclusiveGateWayConverter.class.getName(), new ExclusiveGateWayConverter());
    }

    /**
     * 通过applicationContext获取到所有的bean
     * @param applicationContext
     */
    public void addBpmnConverter2MapByReflection(ApplicationContext applicationContext) {
        Map<String, BaseBpmnConverter> map = applicationContext.getBeansOfType(BaseBpmnConverter.class);
        for (Map.Entry<String, BaseBpmnConverter> entry : map.entrySet()) {
            converterMap.put(entry.getValue().getClass().getName(), entry.getValue());
        }
    }
}
