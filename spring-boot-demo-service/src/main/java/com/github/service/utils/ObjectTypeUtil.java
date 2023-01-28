package com.github.service.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.javassist.bytecode.ByteArray;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.*;
import java.util.Comparator;
import java.util.Objects;

public class ObjectTypeUtil {
    public static void apacheObjectUtil() {
        String a = "123";
        String b = "234";
        //要求object对象要实现Comparable接口
        org.apache.commons.lang3.ObjectUtils.compare(a, b);
        //默认值
        org.apache.commons.lang3.ObjectUtils.defaultIfNull(a, b);
    }

    public static void springObjectUtil() {
        String[] arr = new String[]{"123", "456", "3321"};
        org.springframework.util.ObjectUtils.addObjectToArray(arr, "456");
        String displayString = org.springframework.util.ObjectUtils.getDisplayString(arr);
        System.out.println(displayString);
    }

    public static void originObjectUtil() {
        Objects.nonNull(null);
        Objects.toString(null, null);
        String a = "123"; String b = "234";
        int compareRe = Objects.compare(a, b, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(compareRe);
    }

    /**
     * 深拷贝
     * @param src
     * @return
     * @param <T>
     */
    public static <T extends Serializable> T deepClone(T src) {
        if (src == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objOut = null;

        ByteArrayInputStream in = null;
        ObjectInputStream objIn = null;
        try {
            //输出流
            objOut = new ObjectOutputStream(out);
            objOut.writeObject(src);

            //输入流
            in = new ByteArrayInputStream(out.toByteArray());
            objIn = new ObjectInputStream(in);
            return (T)objIn.readObject();
        } catch (Exception e) {
            //
        } finally {
            if (objOut != null) {
                try {
                    objOut.close();
                } catch (IOException ex) {
                }
            }
            if (objIn != null) {
                try {
                    objIn.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        springObjectUtil();
//        originObjectUtil();

        String a = "123456";
        String b = deepClone(a);
        System.out.println(b);
    }
}
