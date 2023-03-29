package com.github.zhangguoqing.service.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1265102638843296
 * 集合相关的工具类
 */
public class CollectionTypeUtil {
    /**
     * java.util.Collections工具类
     */
    public static void execute() {
        List<String> list = Lists.newArrayList("123", "345", "567");
        //list排序
        Collections.sort(list);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        List<String> emptyList = Collections.EMPTY_LIST;
        Map<String, String> emptyMap = Collections.EMPTY_MAP;
        Collections.emptyList();
        Collections.fill(emptyList, "123");
        //反转list
        Collections.reverse(list);
        //打乱list顺序
        Collections.shuffle(list);
        //基于原有的集合创建一个具备同步功能的集合
        Collections.synchronizedList(list);
        Collections.synchronizedMap(emptyMap);

        //基于原有的集合创建一个不可更改的集合
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        unmodifiableList.add("12345");
    }

    /**
     * apache.commons.collections4包里的集合工具类
     */
    public static void execute1() {
        List<String> stringList = Lists.newArrayList();
        stringList.add("123");
        stringList.add("345");
        CollectionUtils.isEmpty(stringList);
        //将remove集合内的数据从collection中移除
        CollectionUtils.removeAll(stringList, Lists.newArrayList("123"));
        //向collection中添加元素
        CollectionUtils.addIgnoreNull(stringList, "123");
        //计算匹配的数量
        int count = CollectionUtils.countMatches(stringList, new Predicate<String>() {
            @Override
            public boolean evaluate(String s) {
                return s.equals("345");
            }
        });
        System.out.println(count);
        //返回符合条件的集合
        Collection<String> newList = CollectionUtils.select(stringList, new Predicate<String>() {
            @Override
            public boolean evaluate(String s) {
                return false;
            }
        });
        Map<String, String> map = Maps.newHashMap();
        map.put("a", "true");
        MapUtils.getBoolean(map, "a");

        //数据对，类似于map
        Pair<String, String> pair = Pair.of("a", "b");
        pair.getKey(); pair.getValue();
        //
    }
//    public static void main(String[] args) {
////        execute();
//        execute1();
//    }
}
