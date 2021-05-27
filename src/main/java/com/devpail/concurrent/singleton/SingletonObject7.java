package com.devpail.concurrent.singleton;

import java.util.stream.IntStream;

/**
 * @author bingo
 * @version 1.0.0
 * @ClassName SingletonObject7.java
 * @Description TODO
 * @createTime 2019年07月07日 21:01:00
 */
public class SingletonObject7 {
    private SingletonObject7() {

    }

    /**
     * 枚举类型是线程安全的，构造函数只会被构造一次
     */
    private enum Singleton {
        //单例对象
        INSTANCE;

        private final SingletonObject7 instance;

        Singleton() {
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance() {
            return instance;
        }
    }

    public static SingletonObject7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).forEach(i ->
                new Thread(String.valueOf(i)) {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + ":" + SingletonObject7.getInstance());
                    }
                }.start()
        );
        //
        // IntStream.rangeClosed(1,100).forEach(
        //         i-> new Thread(String.valueOf(i)){
        //             @Override
        //             public void run() {
        //                 System.out.println(SingletonObject7.getInstance());
        //             }
        //         }.start()
        // );

    }
}
