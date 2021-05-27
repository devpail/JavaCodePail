package com.devpail.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 实现自己的InvocationHandler
 * @author: zhangzhb
 * @create: 2019-07-28 00:32
 **/

public class MyInvocationHandler implements InvocationHandler {
    //目标对象
    private Object target;

    /**
     * 构造方法
     *
     * @param
     */
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * 执行目标对象的方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //在目标对象执行之前简单的标记一下
        System.out.println("=====代理前==========");
        //执行目标对象的方法
        Object result = method.invoke(target, args);
        System.out.println("=====代理后==========");
        return result;
    }

    /**
     * 获取目标对象的代理对象
     *
     * @return 代理对象
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {

        //实例化目标对象
        UserService userService = new UserServiceImpl();
        //实例化InvocationHandler
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
        //根据目标对象生成代理对象
        UserService proxy = (UserService) invocationHandler.getProxy();
        //调用代理对象的方法
        proxy.add();
    }

}
