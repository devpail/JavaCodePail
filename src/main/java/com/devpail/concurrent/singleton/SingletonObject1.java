package com.devpail.concurrent.singleton;

/**
 * @author bingo
 * @version 1.0.0
 * @ClassName SingletonObject1.java
 * @Description 懒汉模式
 * @createTime 2019年07月07日 20:22:00
 */
public class SingletonObject1 {

    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1() {

    }

    public static SingletonObject1 getInstance() {
        return instance;
    }
}
