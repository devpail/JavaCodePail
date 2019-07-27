package com.devpail.dynamicproxy.jdk;

/**
 * @description: 目标对象实现的接口，用jdk来生成代理对象一定要实现一个接口
 * @author: zhangzhb
 * @create: 2019-07-28 00:30
 **/

public interface UserService {
    /**
     * 目标方法
     */
    public void add();
}