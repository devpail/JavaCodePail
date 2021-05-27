package com.devpail.concurrent.singleton;

/**
 * @author bingo
 * @version 1.0.0
 * @ClassName SingletonObject3.java
 * @Description TODO
 * @createTime 2019年07月07日 20:36:00
 */
public class SingletonObject4 {
    private static SingletonObject4 instance;

    private SingletonObject4() {
    }

    /**
     * 双重校验锁   但是可能会引起空指针异常
     */
    public static SingletonObject4 getInstance() {
        if (instance == null) {
            synchronized (SingletonObject4.class) {
                if (instance == null) {
                    instance = new SingletonObject4();
                }
            }
        }
        return instance;
    }

}
