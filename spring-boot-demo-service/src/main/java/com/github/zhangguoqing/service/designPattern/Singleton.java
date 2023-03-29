package com.github.zhangguoqing.service.designPattern;

public class Singleton {
//    private static Singleton instance = new Singleton();

    private static Singleton instance = null;

    private Singleton() {}

//    public static Singleton getInstance() {
//        return instance;
//    }

    /**
     * 双重校验锁1
     * @return
     */
    public static synchronized Singleton getInstance() {
        if (null == instance) {
            synchronized (Singleton.class) {
                if (null == instance) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
