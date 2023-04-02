package com.github.zhangguoqing.service.simpleBPMN.handler;

import com.github.zhangguoqing.service.simpleBPMN.model.element.BaseElement;

public interface ParseHandler<T extends BaseElement> {
    /**
     * 校验是否符合条件
     * @param element
     * @return
     */
    Boolean check(BaseElement element);

    /**
     * 解析element
     * @param element
     */
    void parse(T element);
}
