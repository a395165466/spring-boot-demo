package com.github.service.flowEngine;

import org.springframework.stereotype.Component;

@Component
public class BusInventoryIncreaseProcessor implements BusInventoryProcessor{
    @Override
    public Integer getSequenceNumber() {
        return 1;
    }

    @Override
    public void process() {

    }
}
