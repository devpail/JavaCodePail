package com.devpail.dynamicproxy.jdk;

/**
 * @description: 目标对象
 * @author: zhangzhb
 * @create: 2019-07-28 00:31
 **/

public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("=======add==========");
    }

}