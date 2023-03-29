package com.github.service.threadPool;

import java.util.PriorityQueue;

public class ObjectTreadUtil {
    private static int queueSize = 5;
    private static PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    public static class ProduceTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    try {
                        System.out.println("produce get lock");
                        Thread.sleep(1000);
                        //队列已满
                        while (queue.size() == queueSize) {
                            queue.wait();
                        }
                        queue.offer(1);
                        queue.notifyAll();
                        System.out.println("put 1 to queue, size is :" + queue.size());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static class ConsumeTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    System.out.println("consume get lock");
                    try {
                        Thread.sleep(1000);
                        //队列已空
                        while (queue.isEmpty()) {
                            queue.wait();
                        }
                        queue.poll();
                        queue.notifyAll();
                        System.out.println("get 1 from queue, size is :" + queue.size());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

//    public static void main(String[] args) {
//        Thread produceTask = new Thread(new ProduceTask());
//        Thread consumeTask = new Thread(new ConsumeTask());
//
//        produceTask.start();
//        consumeTask.start();
//    }
}
