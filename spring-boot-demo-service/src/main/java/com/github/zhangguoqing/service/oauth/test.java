package com.github.zhangguoqing.service.oauth;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class test {

    private static Map<String, Set<String>> urlMap = new HashMap<>();
    /**
     * 输出的日志文件
     */
    private static String printLog;
    private static int totalCount;
    private static int failCount;



    public static void main(String[] args) {
        String filePath = "/Users/zhangguoqing/Desktop/NATIVE_INTERFACE_DTL.2023-08-04-01.ningbo02.196_112_116_51.log"; // 根据实际情况修改文件路径
        BufferedReader reader = null;
        try {
             reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) { // 按行读取文件直到文件末尾
                totalCount++;
                String[] splitArr = line.split("\\|");
                try {
                    if (splitArr != null && splitArr.length > 0) {
                        String time = splitArr[0];
                        String provinceCode = splitArr[1].substring(2);
                        String cityCode = splitArr[2].substring(2);
                        String version = splitArr[4].substring(2);
                        String[] urlArr = splitArr[7].substring(2).split("\\?");
                        String url = urlArr[0];
                        //过滤掉灰度环境域名
                        if (url.contains("dev")) {
                            continue;
                        }
                        //过滤掉商品分享的域名
                        if (url.contains("goods")) {
                            continue;
                        }
                        String method;
                        if (!StringUtils.isBlank(splitArr[21])) {
                            method = splitArr[21].substring(2);
                        } else if (!StringUtils.isBlank(splitArr[23])){
                            method = splitArr[23].substring(2);
                        } else {
                            method = splitArr[27].substring(2);
                        }
                        Set<String> methods = urlMap.get(url);
                        if (CollectionUtils.isEmpty(methods)) {
                            Set<String> tempMethods = new HashSet<>();
                            tempMethods.add(method);
                            urlMap.put(url, tempMethods);
                        } else {
                            methods.add(method);
                        }
//                        System.out.println(
//                                "Time:" + time +
//                                        ",provinceCode:" + provinceCode +
//                                        ",cityCode:" + cityCode +
//                                        ",version:" + version +
//                                        ",url:" + url +
//                                        ",method:" + method);
                    }
                } catch(Throwable throwable){
                    failCount++;
                    System.out.println(throwable.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("urlCount:" + urlMap.size() + ", totalCount:" + totalCount + ",failCount:" + failCount);
        for (Map.Entry<String, Set<String>> entry : urlMap.entrySet()) {
            String url = entry.getKey();
            Set<String> methods = entry.getValue();
            String methodStr = methods.stream().collect(Collectors.joining(","));
            System.out.println("url:" + url + ",method:" + methodStr);
        }
    }
}
