package com.devpail.multithreading;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bingo
 * @version 1.0.0
 * @ClassName OddEvenPrinterReentrantLock.java
 * @Description 通过可重入锁ReentrantLock实现
 * @createTime 2019年07月07日 19:30:00
 */
public class OddEvenPrinterReentrantLock {
    /**
     * 可重入锁
     */
    private ReentrantLock lock = new ReentrantLock(false);
    /**
     * 条件？
     */
    private Condition condition = lock.newCondition();
    /**
     * 当前打印数字
     */
    private volatile int count = 0;


    /**
     * Description: 打印线程任务
     * Author: bingo
     * Date: 19-7-7 下午7:58
     * Param: []
     * return: void
     */
    private void printjob() {
        int maxNum = 10;
        lock.lock();
        while (count < maxNum) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + ++count);
                condition.signalAll();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        condition.signalAll();
        lock.unlock();
    }

    public static void main(String[] args) {
        OddEvenPrinterReentrantLock printer = new OddEvenPrinterReentrantLock();

        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("奇数偶数打印-pool-%d").build();
        ExecutorService threadPool = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024), factory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            threadPool.execute(printer::printjob);
        }

        threadPool.shutdown();


        // Thread thread1 = new Thread(printer::printjob);
        // thread1.start();
        //
        // Thread thread2 = new Thread(printer::printjob);
        // thread2.start();
    }


}
