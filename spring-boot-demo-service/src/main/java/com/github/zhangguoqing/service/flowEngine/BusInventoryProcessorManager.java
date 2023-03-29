package com.github.zhangguoqing.service.flowEngine;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BusInventoryProcessorManager implements InitializingBean {
    private Map<String, BusInventoryProcessor> processorMap = Maps.newHashMap();
    @Override
    public void afterPropertiesSet() throws Exception {
//        ApplicationContextUti
    }
}
