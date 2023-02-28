package com.github.service.simpleBPMN;

public interface Definition {
    /**
     * 校验是否有效
     * @return
     */
    public boolean valid();

    /**
     * 获取实例
     * @return
     */
    public Object getInstance();
}
