package com.devpail.multithreading;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author bingo
 * @version 1.0.0
 * @ClassName OddEvenPrinterMonitor.java
 * @Description 使用对象监视器实现
 * @createTime 2019年07月07日 16:15:00
 */
public class OddEvenPrinterMonitor {
    /**
     * 定义监视器对象
     */
    private final Object monitor = new Object();
    /**
     * 定义打印数量
     */
    private volatile int count = 0;
    /**
     *
     */
    private final int max_num = 10;

    /**
     * @Description: 定义打印方法，同步监视器对象
     * @Author: bingo
     * @Date: 19-7-7 下午6:33
     * @Param: []
     * @return: void
     */
    public void printjob() {
        synchronized (monitor) {
            //循环打印
            while (count < max_num) {
                try {
                    //打印完毕，count++
                    System.out.println(Thread.currentThread().getName() + ":" + ++count);
                    //唤醒其他线程
                    monitor.notifyAll();
                    //当前线程等待资源释放
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            monitor.notifyAll();
        }
    }


    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("奇数偶数打印-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());

        //ThreadPoolExecutor executor = new ThreadPoolExecutor(2,2,5 , TimeUnit.SECONDS,new LinkedBlockingQueue<>());


        //创建打印对象
        OddEvenPrinterMonitor printer = new OddEvenPrinterMonitor();
        //开启第一个线程
        singleThreadPool.execute(printer::printjob);
        //开启第二个线程
        singleThreadPool.execute(printer::printjob);
        singleThreadPool.shutdown();


        //第一个线程启动
        //Thread thread1 = new Thread(printer::printjob,"奇数线程");
        //thread1.start();
        //Thread thread2 = new Thread(printer::printjob,"偶数线程");
        //第二个线程启动
        // thread2.start();

    }

}

