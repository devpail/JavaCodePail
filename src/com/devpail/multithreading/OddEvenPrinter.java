package com.devpail.multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 两个线程交替打印奇数偶数
 * @author: zhangzhb
 * @create: 2019-07-04 00:06
 **/

public class OddEvenPrinter {
    private static  Object mointer = new Object();
    private static  int i=0;
    static class Printer implements Runnable{
        @Override
        public void run() {
            synchronized (mointer){

                try {
                    while(i<10){
                        System.out.println(Thread.currentThread().getName()+":"+ ++i);
                        mointer.notifyAll();
                        mointer.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        //ExecutorService threadPool = Executors.newFixedThreadPool(2);
        //ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        //核心线程数6
        //线程池线程数3
        //队列任务数0
        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
        Printer oddPrinter = new Printer();
        Thread oddThread = new Thread(oddPrinter,"oddPrinter---");
        Printer evenPrinter = new Printer();
        Thread evenThread = new Thread(evenPrinter,"evenPrinter--");
        oddThread.start();
        evenThread.start();
    }
}
