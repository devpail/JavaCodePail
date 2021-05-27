package com.devpail.dynamicproxy.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: 代理使用测试
 * @author: zhangzhb
 * @create: 2019-07-27 16:50
 **/

public class CglibDemo implements MethodInterceptor {

    private Object target;

    public Object newInstance(Object source) {
        target = source;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method!!!");
        Object value = methodProxy.invokeSuper(o, objects);
        //Object value = methodProxy.invoke(o, objects);
        return value;
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\\\classes");
        InfoDemo instance = (InfoDemo) new CglibDemo().newInstance(new InfoDemo());
        instance.welcome("zhangsan");
    }
}

class InfoDemo {
    public void welcome(String person) {
        System.out.println("welcome :" + person);
    }
}
