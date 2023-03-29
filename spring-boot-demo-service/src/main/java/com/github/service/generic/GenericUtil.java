package com.github.service.generic;

import com.github.service.Person;
import com.github.service.Student;
import com.github.service.Worker;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class GenericUtil {
    public static void test(ArrayList<String> list) {
        //Class和Class<?>的区别
        try {
            //Class
            Class clazz = Class.forName("com.github.service.Person");
            Person person = (Person)clazz.newInstance();
            System.out.println(person);

            //Class<?>
            Class<?> clazz1 = Class.forName("com.github.service.Person");
            Person person1 = (Person)clazz.newInstance();

            person = Person.class.newInstance();
            System.out.println(person1);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Person> void test1(List<T> list) {
    }

    public static void test2(List<? extends Person> list) {
    }

    public static void tes3(List<?> list) {

    }
//    public static void main(String[] args) {
//        test(Lists.newArrayList());
//
//        List<Student> students = new ArrayList<>();
//        List<Worker> workers = new ArrayList<>();
//
//        test1(students);
//        test1(workers);
//
//        test2(students);
//        test2(workers);
//    }
}
