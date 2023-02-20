package com.github.service.threadPool;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUtil {
    //共享队列
    private static int fullSize = 10;
    private static PriorityQueue<Integer> queue = new PriorityQueue<>(fullSize);

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition producerCondition = lock.newCondition();
    private static Condition consumerCondition = lock.newCondition();

    private static Boolean process = true;

    public static class ProduceTask implements Runnable {
        @Override
        public void run() {
            while (process) {
                try {
                    lock.lock();
                    while (queue.size() == fullSize) {
                        try {
                            System.out.println("produce task await");
                            producerCondition.await();//await操作会释放锁，同时将当前线程阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    System.out.println("add 1 to queue, size is :" + queue.size());
                    consumerCondition.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static class ConsumeTask implements Runnable {
        @Override
        public void run() {
            while (process) {
                lock.lock();
                try {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("consumer task await");
                            consumerCondition.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    queue.poll();
                    System.out.println("get 1 from queue, size is :" + queue.size());
                    producerCondition.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread producer = new Thread(new ProduceTask());
        Thread consumer = new Thread(new ConsumeTask());

        producer.start();
        consumer.start();
    }
}
