package com.github.service.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串类型的工具类
 * https://blog.csdn.net/weixin_43272286/article/details/114240541?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-114240541-blog-126677151.pc_relevant_aa2&spm=1001.2101.3001.4242.2&utm_relevant_index=4
 * https://blog.csdn.net/u012060033/article/details/89326485
 */
public class StringTypeUtil {
    /**
     * 大部分都是使用apache的string工具类，该类方法齐全，功能更加强大
     * 同时org.apache.commons.lang3.StringUtils已经逐步替换org.apache.commons.lang.StringUtils类，是对原有类的升级
     */
    public static void apacheUtil() {
        //是否有一个为空字符
        org.apache.commons.lang3.StringUtils.isAllEmpty("", "111", "222");
        //是否有一个为空白字符
        org.apache.commons.lang3.StringUtils.isAnyBlank("", "111", "222");
        org.apache.commons.lang3.StringUtils.trim("  123  ");
        //如果trim之后的字符串为空，则转换为null
        org.apache.commons.lang3.StringUtils.trimToNull("  123  ");
        //如果trim之后的字符串为空，则转换为""
        org.apache.commons.lang3.StringUtils.trimToEmpty("  123  ");
        //字符串截断
        org.apache.commons.lang3.StringUtils.truncate("123", 1);
        //判断字符串是否相等（忽略大小写）
        org.apache.commons.lang3.StringUtils.equalsIgnoreCase("123", "456");
        org.apache.commons.lang3.StringUtils.compare("111", "222");
        //判断一个字符串是否包含另一个字符串
        boolean containsRe = org.apache.commons.lang3.StringUtils.contains("12356786", "3568");
        org.apache.commons.lang3.StringUtils.containsIgnoreCase("12356786", "3568");
        //是否包含空格
        org.apache.commons.lang3.StringUtils.containsWhitespace("123");
        org.apache.commons.lang3.StringUtils.containsAny("123", "1", "2");
        //返回子字符串（该子字符串是新创建的，不是返回原有的）
        org.apache.commons.lang3.StringUtils.substring("123", 1, 2);
        //返回子字符串
        org.apache.commons.lang3.StringUtils.substringBefore("123", ",");
        //separator会拆分为单个字符进行分割
        org.apache.commons.lang3.StringUtils.split("111", ",");
        //把separator按照完整字符串分割
        org.apache.commons.lang3.StringUtils.splitByWholeSeparator("111", "11");
        //保留所有标记，包括由相邻分隔符创建的空标记
        org.apache.commons.lang3.StringUtils.splitPreserveAllTokens("11,,1", ",");

        //用字符进行连接
        String[] arrs = new String[]{"1", "2"};
        String joinArr = org.apache.commons.lang3.StringUtils.join(arrs, "_");
        System.out.println(joinArr);
        //删除空白字符
        org.apache.commons.lang3.StringUtils.deleteWhitespace("111  34322");
        //字符串替换
        org.apache.commons.lang3.StringUtils.replace("123dswhd423", "42", "haha");
        //字符串按照正则匹配替换
        String replaceRe = org.apache.commons.lang3.StringUtils.replacePattern("1234e45", "\\d", "a");
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher("1234e45");
        matcher.replaceAll("a");
        System.out.println(replaceRe);

        org.apache.commons.lang3.StringUtils.length("123456");
        org.apache.commons.lang3.StringUtils.upperCase("1111a");
        org.apache.commons.lang3.StringUtils.lowerCase("1223aasd");
        //是否为数字
        org.apache.commons.lang3.StringUtils.isNumeric("123456");
        org.apache.commons.lang3.StringUtils.defaultString("12345", "123");
        //String是charSequence的实现类
        org.apache.commons.lang3.StringUtils.defaultIfBlank("111", "a");
        org.apache.commons.lang3.StringUtils.rotate("11122345", 3);
        //字符串反转
        org.apache.commons.lang3.StringUtils.reverse("111234231");
        //是否以某字符串开头
        org.apache.commons.lang3.StringUtils.startsWith("11111", "22");
        org.apache.commons.lang3.StringUtils.endsWith("11111", "er");

        Pair<String, String> a = null;
        String b = null;
    }

    /**
     * spring提供的string操作相关的类方法
     */
    public static void springUtil() {
        org.springframework.util.StringUtils.isEmpty("");
        org.springframework.util.StringUtils.startsWithIgnoreCase("123456", "123");
        String quote = org.springframework.util.StringUtils.quote("12344556");
        System.out.println(quote);

        String[] arr = new String[]{"123", "456", "789"};
        String[] newArr = org.springframework.util.StringUtils.addStringToArray(arr, "999");
        System.out.println(newArr[3]);
        org.springframework.util.StringUtils.mergeStringArrays(arr, newArr);
    }

    /**
     * string原生的方法
     */
    public static void originStringUtil() {
        String str = "123456%d89qwer%dt%s";
        byte[] bytes = str.getBytes();
        System.out.println(bytes.toString());
        //将字符串格式化输出
        String format = String.format(str, 123, 456, "hahaha");
        System.out.println(format);
    }

    /**
     * 其他的方法
     */
    public static void otherStringUtil() {
        //StringBuffer是线程安全的
        StringBuffer stringBuffer = new StringBuffer();
        //每次append都是调用System.arrayCopy()方法，即创建新的数组，将原有数据拷贝到新的数组。
        //System.arraycopy()性能要高于Arrays.copyOf()，Arrays.copyOf()最终也是调用System.arraycopy()
        stringBuffer.append("1");

        //StringBuilder不是线程安全的
        //每次append都是调用System.arrayCopy()方法，即创建新的数组，将原有数据拷贝到新的数组。
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1");
    }

    public static void main(String[] args) {
//        apacheUtil();
//        springUtil();
        originStringUtil();
    }
}
