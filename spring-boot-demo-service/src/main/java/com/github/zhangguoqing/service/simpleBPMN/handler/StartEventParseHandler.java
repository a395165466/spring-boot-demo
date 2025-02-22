package com.github.zhangguoqing.service.simpleBPMN.handler;

import com.github.zhangguoqing.service.simpleBPMN.model.element.BaseElement;
import com.github.zhangguoqing.service.simpleBPMN.model.element.StartEventElement;

public class StartEventParseHandler extends AbstractParseHandler<StartEventElement> {

    @Override
    void execute(StartEventElement element) {

    }

    @Override
    public Boolean check(BaseElement element) {
        if (element instanceof StartEventElement) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
