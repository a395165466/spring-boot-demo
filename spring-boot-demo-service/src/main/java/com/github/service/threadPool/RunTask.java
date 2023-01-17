package com.github.service.threadPool;

public class RunTask implements Runnable{
    private String val;

    RunTask(String val) {
        this.val = val;
    }
    @Override
    public void run() {
        Long threadId = Thread.currentThread().getId();
        String threadName= Thread.currentThread().getName();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("call task execute finish!, threadId:")
                .append(threadId).append(",threadName:").append(threadName).append(",val:").append(this.val);
        System.out.println(stringBuffer.toString());
    }
}
