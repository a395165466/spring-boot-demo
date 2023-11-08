package com.github.zhangguoqing.service.oauth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String url = "https://touch.10086.cn/discount/551_551.html";
        String regex = ".discount\\/[0-9_]+";
        Pattern pattern2 = Pattern.compile(regex);
        Matcher matcher2 = pattern2.matcher(url);
        if (matcher2.find()) {
            System.out.println(url);
        }
//        if (Pattern.matches(".qwhdmark\\/[0-9]+",url1)) {
//            System.out.println(url);
//        }
    }
}
