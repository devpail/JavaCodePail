package com.devpail.designmode.proxy;


/**
 * UserDAO 代理和被代理的公共的接口(Subject)
 */
public interface UserDao {
    boolean insert(String name,int age);
}
