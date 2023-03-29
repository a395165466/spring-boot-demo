package com.github.zhangguoqing.service.threadPool;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.*;

public class ThreadPoolDemo {
    //线程池工厂
    private static ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("threadPooldemo-%d").build();
    private static ThreadFactory scheduleFactory =  new ThreadFactoryBuilder().setNameFormat("scheduleThreadPool-%d").build();

    //普通线程池
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60,
            MILLISECONDS, new ArrayBlockingQueue<Runnable>(500), factory, new ThreadPoolExecutor.CallerRunsPolicy());
    //周期性线程池
    public static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5, scheduleFactory);

    public static <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) {
        try {
            List<Future<T>> futures = threadPoolExecutor.invokeAll(tasks);
            return futures;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object delayedInvoke(Callable<? extends Object> task, long delayedTime, TimeUnit timeUnit) {
        ScheduledFuture<? extends Object> future = scheduledThreadPool.schedule(task, delayedTime, timeUnit);
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void scheduledDelayInvoke(Runnable task, long initDelayTime, long delayedTime, TimeUnit timeUnit) {
        ScheduledFuture<?> future = scheduledThreadPool.scheduleWithFixedDelay(task, initDelayTime, delayedTime, timeUnit);
        try {
            future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        //Runnable
////        test1();
//        //Callable
////        test2();
//        //周期性任务
////        test3();
//        //延迟任务
////        test4();
//        //延迟周期任务
////        test5();
//        //Callable批量任务
//        test6();
//    }
    private static void test1() {
        for (int i = 0; i <= 10; i++) {
            RunTask runTask = new RunTask(String.valueOf(i));
            threadPoolExecutor.execute(runTask);
        }
    }
    private static void test2() {
        List<Future<String>> futureTasks = Lists.newArrayList();
        for (int i = 0; i <= 10; i++) {
            CallTask callTask = new CallTask(String.valueOf(i));
            futureTasks.add(threadPoolExecutor.submit(callTask));
        }

        for (int i = 0; i <= 10; i++) {
            try {
                System.out.println(futureTasks.get(i).get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static void test3() {
        // TODO: 2022/12/29 了解其原理
        // 1秒后执行一次性任务:
        scheduledThreadPool.schedule(new CallTask("one-time"), 1, SECONDS);
        // 2秒后开始执行定时任务，每3秒执行:
        scheduledThreadPool.scheduleAtFixedRate(new RunTask("fixed-rate"), 2, 3, SECONDS);
        // 2秒后开始执行定时任务，以3秒为间隔执行:
        scheduledThreadPool.scheduleWithFixedDelay(new RunTask("fixed-delay"), 2, 3, SECONDS);
    }
    private static void test4() {
        CallTask callTask = new CallTask("delayedTask");
        String result = (String)ThreadPoolDemo.delayedInvoke(callTask, 1000, MILLISECONDS);
        System.out.println(result);
    }
    private static void test5() {
        RunTask runTask = new RunTask("scheduledDelayTask");
        scheduledDelayInvoke(runTask, 2, 1, SECONDS);
    }
    private static void test6() {
        List<CallTask> callTasks = Lists.newArrayList();
        for (int i = 0; i <= 10; i++) {
            callTasks.add(new CallTask(String.valueOf(i)));
        }

        List<Future<String>> futures = invokeAll(callTasks);
        for (int i = 0, size = futures.size(); i < size; i++) {
            try {
                String result = futures.get(i).get();
                System.out.println(result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}