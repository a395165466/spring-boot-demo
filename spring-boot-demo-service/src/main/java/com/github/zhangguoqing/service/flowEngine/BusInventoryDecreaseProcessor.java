package com.github.zhangguoqing.service.flowEngine;

import org.springframework.stereotype.Component;

@Component
public class BusInventoryDecreaseProcessor implements BusInventoryProcessor{
    @Override
    public Integer getSequenceNumber() {
        return 2;
    }

    @Override
    public void process() {

    }
}
