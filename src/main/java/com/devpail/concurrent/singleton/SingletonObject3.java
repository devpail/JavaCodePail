package com.devpail.concurrent.singleton;

/**
 * @author bingo
 * @version 1.0.0
 * @ClassName SingletonObject3.java
 * @Description TODO
 * @createTime 2019年07月07日 20:36:00
 */
public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3(){}

    /**
     * synchronized每次加锁影响性能
     * @return
     */
    public synchronized  static SingletonObject3 getInstance(){
        if(instance == null){
            instance = new SingletonObject3();
        }
        return instance;
    }

}
