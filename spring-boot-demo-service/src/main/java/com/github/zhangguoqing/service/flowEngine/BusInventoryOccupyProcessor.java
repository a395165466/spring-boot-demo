package com.github.zhangguoqing.service.flowEngine;

import org.springframework.stereotype.Component;

@Component
public class BusInventoryOccupyProcessor implements BusInventoryProcessor{
    @Override
    public Integer getSequenceNumber() {
        return 3;
    }

    @Override
    public void process() {

    }
}
