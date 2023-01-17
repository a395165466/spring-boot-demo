package com.github.service.flowEngine;

public interface BusInventoryProcessor {
    /**
     * 获取处理器编号
     * @return
     */
    Integer getSequenceNumber();

    /**
     * 执行处理
     */
    void process();
}
