package com.github.zhangguoqing.service.simpleBPMN;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BpmnXmlFileLoad implements ApplicationContextAware {
    @Resource
    private BpmnXmlParser bpmnXmlParser;

    private ApplicationContext applicationContext;

    private String defaultFileLocation = "";

    private String START_MODULE = "spring-boot-demo-starter";

    private String SERVICE_MODULE = "spring-boot-demo-service";

    private static String SUFFIX = "xml";

    private static Map<String, List<Flow>> flowMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        // TODO: 2023/3/11 1.考虑要能解析所有module里的问题 2.考虑要解析bpmn文件，而不是xml文件，不然会会有很多
        String classPath = BpmnXmlFileLoad.class.getResource("/").getPath();
        defaultFileLocation = classPath.replace(START_MODULE, SERVICE_MODULE);
        //获取到文件列表
        List<File> fileList = Lists.newArrayList();
        getAllXmlFile(defaultFileLocation, fileList);
        if (CollectionUtils.isEmpty(fileList)) {
            return;
        }
        List<String> fileNameList = fileList.stream().map(File::getAbsolutePath).collect(Collectors.toList());
        for (String s : fileNameList) {
            System.out.println(s);
        }
        bpmnXmlParser.parse(fileNameList, applicationContext);
    }

    public List<Flow> getFlowListByFlowName(String flowName) {
        if (StringUtils.isBlank(flowName)) {
            return Lists.newArrayList();
        }

        return flowMap.get(flowName);
    }

    /**
     * 递归获取所有的文件
     * @param directory
     * @return
     */
    private void getAllXmlFile(String directory, List<File> fileList) {
        if (StringUtils.isBlank(directory)) {
            return;
        }

        File file = new File(directory);
        if (file.isDirectory()) {
            for (File tempFile : file.listFiles()) {
                if (tempFile.isDirectory()) {
                    getAllXmlFile(tempFile.getAbsolutePath(), fileList);
                } else if (isXmlFile(tempFile)){
                    fileList.add(tempFile);
                }
            }
        } else if (isXmlFile(file)) {
            fileList.add(file);
        }
    }

    private boolean isXmlFile(File file) {
        if (file == null) {
            return false;
        }

        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return false;
        }
        return SUFFIX.equals(fileName.substring(index + 1));
    }

//    public static void main(String[] args) {
//
////        System.out.println(ClassLoader.getSystemResource(""));
////        System.out.println(this.getClass().getResource());
//
//        String fileName = "step.java";
//        int index = fileName.lastIndexOf(".");
////        if (index == -1) {
////            return false;
////        }
//        System.out.println(fileName.substring(index + 1));
//    }
}
