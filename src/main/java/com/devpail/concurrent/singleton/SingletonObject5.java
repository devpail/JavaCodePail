package com.devpail.concurrent.singleton;

/**
 * @author bingo
 * @version 1.0.0
 * @ClassName SingletonObject3.java
 * @Description TODO
 * @createTime 2019年07月07日 20:36:00
 */
public class SingletonObject5 {


    /**
     * volatile 禁止指令重排序的优化， 保证可见性，原子性，如果不加  对象可能不会被创建完整就返回
     */
    private static volatile SingletonObject5 instance;

    private SingletonObject5() {
    }

    /**
     * 双重校验锁   但是可能会引起空指针异常
     */
    public static SingletonObject5 getInstance() {
        if (instance == null) {
            synchronized (SingletonObject5.class) {
                if (instance == null) {
                    instance = new SingletonObject5();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
    }

}
