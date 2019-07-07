package com.devpail.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @description: 打印类信息
 * @author: zhangzhb
 * @create: 2019-06-24 20:27
 **/

public class ClassUtil {
    public static void printClassMessage(Object obj){
        //获取类的类类型
        //传递的是哪个子类的对象，c就是该子类的类类型
        Class c = obj.getClass();
        //获取类的名称
        System.out.println("类的名称是："+c.getName());
        /*
        * Method类，方法对象
        * 一个成员方法就是一个Method对象
        * getMethods()方法获取的是所有的public的函数，包括父类继承而来的
        * getDeclaredMethods获取的是所有该类的自己生命的方法，所有访问权限的
        * */
        Method[] ms = c.getMethods();
        for (int i = 0; i <ms.length; i++) {
            //返回值的类类型
            Class returnType = ms[i].getReturnType();
            System.out.println(returnType.getName());
            //方法名称
            System.out.print(ms[i].getName()+"(");
            //参数列表的类型的类类型
            Class[] paramTypes = ms[i].getParameterTypes();
            for (Class class1 :
                    paramTypes) {
                System.out.print(class1.getName()+",");
            }
            System.out.println(")");
        }
        /*
        * getFields()获取所有public 的成员变量信息
        * getDeclaredFields获取的是该类自己声明的成员变量的信息
        * */

        Field[] fs = c.getDeclaredFields();
        for (Field field :
                fs) {
            Class fieldType = field.getType();
            //成员变量的类类型
            String typeName = fieldType.getName();
            //成员变量名称
            String fieldName = field.getName();
        }

    }
}
