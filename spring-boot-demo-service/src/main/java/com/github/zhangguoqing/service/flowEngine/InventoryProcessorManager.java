package com.github.zhangguoqing.service.flowEngine;

import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
public class InventoryProcessorManager implements ApplicationContextAware {
    /**
     * context
     */
    private ApplicationContext context;

    private List<BusInventoryProcessor> processorList = Lists.newArrayList();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;

        Map<String, BusInventoryProcessor> map = applicationContext.getBeansOfType(BusInventoryProcessor.class);
        for (Map.Entry<String, BusInventoryProcessor> entry : map.entrySet()) {
            processorList.add(entry.getValue());
        }
        Collections.sort(processorList, new Comparator<BusInventoryProcessor>() {
            @Override
            public int compare(BusInventoryProcessor o1, BusInventoryProcessor o2) {
                return o1.getSequenceNumber() - o2.getSequenceNumber();
            }
        });

        System.out.println(processorList.toString());
    }

    public BusInventoryProcessor getInventoryProcessor(Integer sequenceNum) {
        if (Objects.isNull(sequenceNum) || CollectionUtils.isEmpty(processorList)) {
            return null;
        }

        for (BusInventoryProcessor processor : processorList) {
            if (sequenceNum.equals(processor.getSequenceNumber())) {
                return processor;
            }
        }
        return null;
    }
}
