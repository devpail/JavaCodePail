package com.devpail.designmode.proxy;

/**
 * @description: 用户数据持久具体实现
 * @author: zhangzhb
 * @create: 2019-07-27 23:18
 **/

public class UserDaoImpl implements UserDao {

    @Override
    public boolean insert(String name, int age) {
        System.out.println("insert to database name="+name +" age="+age);
        return true;
    }
}
