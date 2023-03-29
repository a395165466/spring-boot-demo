package com.github.zhangguoqing.service.impl;

import com.github.zhangguoqing.api.common.CommonService;
import com.github.zhangguoqing.service.simpleBPMN.BpmnXmlFileLoad;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    RuntimeService runtimeService;

    @Resource
    RepositoryService repositoryService;

    private String START_MODULE = "spring-boot-demo-starter";

    private String SERVICE_MODULE = "spring-boot-demo-service";

    @Override
    public String query(String param) {
        System.out.println("this is service");
        return null;
    }

    @Override
    public void startFlow() {
        Map<String, Object> variables = new HashMap<>();
        // 请求的资源ID
        variables.put("resourceId", "resourceId");
        // 请求发起用户
        variables.put("requestUser", "resourceId");
        // 初级审批用户
        variables.put("juniorAdmin", "juniorAdmin");
        // 高级审批用户
        variables.put("seniorAdmin", "seniorAdmin");
        ProcessInstance processInstance = runtimeService.
                startProcessInstanceById("audit", variables);
//        ProcessInstanceEntity entity=new ProcessInstanceEntity();
//        entity.setProcessDeploymentId(processInstance.getDeploymentId());
//        entity.setProcessInstanceId(processInstance.getProcessInstanceId());
//        entity.setActivityId(processInstance.getActivityId());
//        return entity;
    }

    @Override
    public void deploy() {
        String classPath = BpmnXmlFileLoad.class.getResource("/").getPath();
        String fileLocation = classPath.replace(START_MODULE, SERVICE_MODULE) + "simple.bpmn20.xml";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(fileLocation));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addInputStream("simple.bpmn20.xml", inputStream);
        deploymentBuilder.deploy();
    }
}
