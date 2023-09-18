package com.github.zhangguoqing.service.oauth;

import java.io.*;

public class LogCollect {
    private static String dirPath = "/Users/zhangguoqing/work/H5调用日志/"; // 根据实际情况修改文件路径
    private static String logFile = "H5_COLLECT_LOG.log";
    private static Long totalCount = 0L;

    public static void main(String[] args) {
        File writeLog = new File(dirPath + logFile);

        File dir = new File(dirPath);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                continue;
            }
            collectLog(file, writeLog);
        }
        System.out.println(totalCount);
    }

    private static void collectLog(File file, File writeLog) {
        //1.从file里读取文件内容
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(file.getPath()));
            writer = new BufferedWriter(new FileWriter(dirPath + logFile, true));
            String line;
            while ((line = reader.readLine()) != null) { // 按行读取文件直到文件末尾
                System.out.println(line);
                totalCount++;
                //2.将读取的文件内容写入writeLog中
                writer.write(line);
                writer.newLine();
            }
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
