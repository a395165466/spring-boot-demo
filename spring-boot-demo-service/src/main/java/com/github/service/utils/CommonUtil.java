package com.github.service.utils;

import com.github.pagehelper.cache.GuavaCache;
import com.github.service.Person;
import com.github.service.Student;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
//import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class CommonUtil {
    public static void commonUtil() {
        //DecimalFormat对小数进行格式化
        BigDecimal number = new BigDecimal("12.45605");
        DecimalFormat format = new DecimalFormat("00.##");
        String formatStr = format.format(number);
        System.out.println(formatStr);

        //SimpleDateFormat线程不安全的，频繁的创建和销毁对象
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateFormat.format(new Date());
        System.out.println(dateStr);

        //apache.common.lang3包中的日期/时间格式化工具类
        //线程安全的，且不需要频繁创建/销毁对象，对象是放在缓存中的
        DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            Date date = DateUtils.parseDate("2023-01-29 10:00:00", "yyyy-MM-dd HH:mm:ss");
            System.out.println(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static void execute() {
//        PropertyUtils
        Person xiaoming = new Person();
        xiaoming.setName("xiaoming");
        xiaoming.setAge(18);
        xiaoming.setCardNumber("370883198912083035");

        Person xiaozhang = new Person();

        //属性赋值
        BeanUtils.copyProperties(xiaoming, xiaozhang, "age", "name");
        //查询bean的方法
        Method method = BeanUtils.findMethod(Person.class, "execute", String.class);
        System.out.println(method.getName());
        System.out.println(method.getDeclaredAnnotations().length);
        System.out.println(method.getReturnType().getName());
        System.out.println(method.getParameters().length);
        PropertyDescriptor descriptor = BeanUtils.findPropertyForMethod(method);
        System.out.println(descriptor);

        //
        Student zhang = new Student();
        //A是否是B的相同类或者子类
        boolean isAssignable = ClassUtils.isAssignable(Student.class, Person.class);

        //A是否是B的相同类或者超类
        boolean isAssignableFrom = Person.class.isAssignableFrom(Student.class);

        System.out.println(isAssignable);
        System.out.println(isAssignableFrom);
        //获取类的接口和父类信息
        List<Class<?>> classList = ClassUtils.getAllInterfaces(Student.class);
        List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(Student.class);
        System.out.println(classList);

        Field[] fields = FieldUtils.getAllFields(Student.class);
    }

    public static void main(String[] args) {
//        commonUtil();
        execute();
    }
}
