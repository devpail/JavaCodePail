package com.devpail.concurrent.singleton;

import java.util.stream.IntStream;

/**
 * @author bingo
 * @version 1.0.0
 * @ClassName SingletonObject3.java
 * @Description TODO
 * @createTime 2019年07月07日 20:36:00
 */
public class SingletonObject6 {

    private SingletonObject6() {
    }

    private static class InstanceHolder {
        /**
         * static 只会初始化一次，调用的时候再加载
         */
        private final static SingletonObject6 instance = new SingletonObject6();
    }

    /**
     * 保证懒加载，线程安全，效率高
     */
    public static SingletonObject6 getInstance() {
        return InstanceHolder.instance;
    }

    public static void main(String[] args) {

        IntStream.rangeClosed(1, 1000).forEach(
                i -> new Thread(String.valueOf(i)) {
                    @Override
                    public void run() {
                        System.out.println(SingletonObject6.getInstance());
                    }
                }.start()
        );
        //SingletonObject6.getInstance();
    }

}
