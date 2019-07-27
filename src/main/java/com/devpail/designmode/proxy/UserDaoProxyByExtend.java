package com.devpail.designmode.proxy;

/**
 * @description: 用户持久数据的代理类(继承被代理类方式)
 * @author: zhangzhb
 * @create: 2019-07-27 23:21
 **/

public class UserDaoProxyByExtend extends UserDaoImpl {
    private UserDaoImpl conreteUserDao;

    public UserDaoProxyByExtend() {

    }
    public UserDaoProxyByExtend(UserDaoImpl conreteUserDAO) {
        this.conreteUserDao = conreteUserDAO;
    }
    @Override
    public boolean insert(String name, int age) {
        System.out.println("before insert handle some logic by extend");
        return conreteUserDao.insert(name, age);
    }
}
