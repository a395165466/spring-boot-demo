package com.github.zhangguoqing.service.simpleBPMN.handler;

import com.github.zhangguoqing.service.simpleBPMN.model.BaseElement;

public abstract class AbstractParseHandler<T extends BaseElement> implements ParseHandler<T> {
    @Override
    public void parse(T element) {
        //使用模版方法
        //1.前置处理
        //2.执行解析
        execute(element);
        //3.后置处理
    }

    abstract void execute(T element);
}
