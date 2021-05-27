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
    private static Object mointer = new Object();
    private static int i = 0;

    static class Printer {
        public void run() {
            synchronized (mointer) {
                try {
                    while (i < 10) {
                        System.out.println(Thread.currentThread().getName() + ":" + ++i);
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

        Printer printer = new Printer();
        Thread oddThread = new Thread(printer::run, "oddPrinter---");
        Thread evenThread = new Thread(printer::run, "evenPrinter--");
        oddThread.start();
        evenThread.start();
    }
}
