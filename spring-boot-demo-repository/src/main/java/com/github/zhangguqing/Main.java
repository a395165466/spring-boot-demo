package com.github.zhangguqing;

public class Main {
    public static void main(String[] args) {
        String s = "0123456";
        System.out.println(s.substring(0,1));

        System.out.println("Hello world!");
    }

    //检查网络状态
    //client.app.coc.10086.cn作为主域名，会申请停机可访问
    //resource.app.coc.10086.cn 是普通域名
    //因为申请停机可访问需要一定的时间，因此期望客户端只做一次改造，在申请成功之前和之后都能判断网络状态
    private int checkNetStatus() {
        return 0;
    }
}