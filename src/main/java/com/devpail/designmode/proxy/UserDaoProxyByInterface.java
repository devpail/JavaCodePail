package com.devpail.designmode.proxy;

/**
 * @description: 用户持久数据的代理类(和被代理类实现同个接口方式)
 * @author: zhangzhb
 * @create: 2019-07-27 23:19
 **/

public class UserDaoProxyByInterface implements UserDao {

    private UserDaoImpl conreteUserDao;

    public UserDaoProxyByInterface() {

    }

    public UserDaoProxyByInterface(UserDaoImpl conreteUserDAO) {
        this.conreteUserDao = conreteUserDAO;
    }

    @Override
    public boolean insert(String name, int age) {
        System.out.println("before insert handle some logic by interface");
        return conreteUserDao.insert(name, age);
    }
}
