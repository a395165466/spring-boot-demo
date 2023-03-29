package com.github.zhangguoqing.service.threadPool;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture是jdk8的新特性
 * 而CompletableFuture是对Future的扩展和增强。CompletableFuture实现了Future接口，并在此基础上进行了丰富的扩展，
 * 完美弥补了Future的局限性，同时CompletableFuture实现了对任务编排的能力。借助这项能力，可以轻松地组织不同任务的运行顺序、规则以及方式。
 * 比如：执行某一阶段，可以向下执行后续阶段
 * 带有sync后缀的意味着都是异步执行，即开启一个新的线程
 */
public class CompletableFutureUtil {
    private static String param = "this is";
    private static String output = " the result";

    public static void test() {

        CompletableFuture<String> completedFuture = CompletableFuture.supplyAsync(() -> {
            return param + output;
        });

        String result = null;
        try {
            result = completedFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(param + output);
        });

        //任务执行后，增加后置动作(无返回值)
        CompletableFuture.supplyAsync(() -> {
            return param + output;
        }).thenAccept(tempValue -> {
            System.out.println("tempValue is :" + tempValue);
        });

        //任务执行后，增加后置动作(无返回值)
        CompletableFuture.supplyAsync(() -> {
            return param + output;
        }).thenApply((tempValue) -> {
            System.out.println("tempValue is :" + tempValue);
            return param + output;
        });

        //whenComplete是当某个任务执行完成后执行的回调方法，会将执行结果或者执行期间抛出的异常传递给回调方法
        CompletableFuture.supplyAsync(() -> {
            return param + output;
        }).whenComplete((tempResult, e) -> {
            System.out.println("上个任务结果：" + tempResult);
            System.out.println("上个任务抛出异常：" + e);
        });

        //thenCombine 两个CompletableFuture组合起来处理，只有两个任务都正常完成时，才进行下阶段任务
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
           return param;
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            return output;
        });

        CompletableFuture<String> cf3 = cf1.thenCombine(cf2, (a, b) -> {
           return a + b;
        });
        try {
            System.out.println(cf3.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    //allOf / anyOf
    private static void test1() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "cf1 result";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            return "cf2 result";
        });

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            return "cf3 result";
        });

        //都正常返回则get获取null，如果没有都执行完则会等待
        CompletableFuture<Void> voidResult = CompletableFuture.allOf(cf1, cf2, cf3);
        try {
            System.out.println(voidResult.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<Object> objectResult = CompletableFuture.anyOf(cf1, cf2, cf3);
        try {
            System.out.println(objectResult.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
////        test();
//        test1();
//    }
}
