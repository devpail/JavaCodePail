package com.devpail.designmode.proxy;

/**
 * @description:
 * @author: zhangzhb
 * @create: 2019-07-27 23:23
 **/

public class Client {
    public static void main(String[] args) {

        UserDaoImpl conreteUserDao = new UserDaoImpl();
        UserDaoProxyByInterface userDAOProxyByInterface = new UserDaoProxyByInterface(conreteUserDao);
        //和被代理类实现同个接口方式进行代理
        userDAOProxyByInterface.insert("dynamo", 18);

        //通过继承被代理类方式进行代理
        UserDaoProxyByExtend userDAOProxyByExtend = new UserDaoProxyByExtend(conreteUserDao);
        userDAOProxyByExtend.insert("dynamo", 18);

    }
}
