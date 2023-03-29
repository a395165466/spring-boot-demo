package com.github.zhangguoqing.service.threadPool;

import java.util.concurrent.Callable;

public class CallTask implements Callable<String> {
    private String val;

    CallTask(String val) {
        this.val = val;
    }

    @Override
    public String call() throws Exception {
        Long threadId = Thread.currentThread().getId();
        String threadName= Thread.currentThread().getName();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("call task execute finish!, threadId:")
                        .append(threadId).append(",threadName:").append(threadName).append(",val:").append(this.val);
        return stringBuffer.toString();
    }
}
