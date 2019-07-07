package com.devpail.concurrent.singleton;

/**
 * @author bingo
 * @version 1.0.0
 * ClassName SingletonObject2.java
 * Description 饱汉模式
 * createTime 2019年07月07日 20:27:00
 */
public class SingletonObject2 {

    private static SingletonObject2 instance ;

    private SingletonObject2(){}

    public static SingletonObject2 getInstance(){
        if (null == instance) {
            instance = new SingletonObject2();
        }
        return instance;
    }
}
