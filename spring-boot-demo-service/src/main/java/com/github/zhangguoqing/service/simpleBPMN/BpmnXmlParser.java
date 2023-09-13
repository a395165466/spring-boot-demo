package com.github.zhangguoqing.service.simpleBPMN;

import com.github.zhangguoqing.service.flowEngine.BusInventoryProcessor;
import com.github.zhangguoqing.service.simpleBPMN.converter.*;
import com.github.zhangguoqing.service.simpleBPMN.model.BpmnParseModel;
import com.github.zhangguoqing.service.simpleBPMN.model.element.BpmnModel;
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

    public Map<String, BpmnParseModel> parse(List<String> files, ApplicationContext applicationContext) {
        Map<String, BpmnParseModel> modelMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(files)) {
            return modelMap;
        }
        //获取所有的converter
        addBpmnConverter2MapByReflection(applicationContext);

        //解析xml文件为FlowDefinition列表
        List<BpmnParseModel> bpmnParseModels = Lists.newArrayList();
        for (String file : files) {
            bpmnParseModels.add(parseFileByXml(file));
        }
        for (BpmnParseModel bpmnParseModel : bpmnParseModels) {
            modelMap.put(bpmnParseModel.getId(), bpmnParseModel);
        }
        return modelMap;
    }

    public BpmnParseModel parseFileByXml(String fileName) {
        BpmnParseModel model = new BpmnParseModel();
        ProcessElement process = new ProcessElement();

        File file = new File(fileName);
        XMLInputFactory xmlFactory  = XMLInputFactory.newInstance();
        try {
            InputStream in = new FileInputStream(file);
            XMLStreamReader reader = xmlFactory.createXMLStreamReader(in);
            while(reader.hasNext()){
                if (reader.next() == XMLStreamReader.START_ELEMENT) {
                    String localName = reader.getLocalName();
                    System.out.printf("%s\n", localName);

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

        return model;
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
        converterMap.put(DefinitionsBpmnConverter.ELEMENT_TYPE, new DefinitionsBpmnConverter());
        converterMap.put(EndEventBpmnConverter.ELEMENT_TYPE, new EndEventBpmnConverter());
        converterMap.put(StartEventBpmnConverter.ELEMENT_TYPE, new StartEventBpmnConverter());
        converterMap.put(SequenceBpmnConverter.ELEMENT_TYPE, new SequenceBpmnConverter());
        converterMap.put(ServiceTaskBpmnConverter.ELEMENT_TYPE, new ServiceTaskBpmnConverter());
        converterMap.put(ProcessBpmnConverter.ELEMENT_TYPE, new ProcessBpmnConverter());
        converterMap.put(ExclusiveGateWayConverter.ELEMENT_TYPE, new ExclusiveGateWayConverter());
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
