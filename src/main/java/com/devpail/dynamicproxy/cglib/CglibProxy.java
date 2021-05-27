package com.devpail.dynamicproxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: 创建子类的方法与代理的方法
 * @author: zhangzhb
 * @create: 2019-07-28 00:20
 **/

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    //getProxy(SuperClass.class)方法通过入参即父类的字节码，通过扩展父类的class来创建代理对象
    public Object getProxy(Class clazz) {
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    /**
     *      * 实现MethodInterceptor接口方法
     *      *  intercept()方法拦截所有目标类方法的调用
     *      *  obj表示目标类的实例，method为目标类方法的反射对象，
     *      *  为方法的动态入参，proxy为代理类实例。
     *      *  proxy.invokeSuper(obj, arg2)通过代理类调用父类中的方法
     *     
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] arg2,
                            MethodProxy proxy) throws Throwable {
        System.out.println("前置代理");
        //对接入点的放行(Spring aop中接入点)也即实现类方法，要是不掉用此方法，则代理类中的say()方法将不会被执行
        Object result = proxy.invokeSuper(obj, arg2);
        System.out.println("后置代理");
        return null;
    }

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //创建生成子类的方式创建代理类
        SayHello proxyImpl = (SayHello) proxy.getProxy(SayHello.class);
        proxyImpl.say();
    }

}

/**
 * @description: 需要被代理的类，也就是父类
 * @author: zhangzhb
 * @create: 2019-07-28 00:10
 **/

class SayHello {
    public void say() {
        System.out.println("hello everyone");
    }
}

