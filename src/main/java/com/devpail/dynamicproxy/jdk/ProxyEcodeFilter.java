package com.devpail.dynamicproxy.jdk;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 利用动态代理解决中文乱码问题
 * @author: zhangzhb
 * @create: 2019-07-27 23:03
 **/

public class ProxyEcodeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 使用动态代理来增强getParameter方法
        // 获取请求方式
        HttpServletRequest req = (HttpServletRequest) request;
        ClassLoader loader = null;
        try {
            loader = Class.forName("com.filter.ProxyEcodeFilter").getClassLoader();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        // 获取委托者实现的所有接口
        Class<?>[] interfaces = req.getClass().getInterfaces();
        HttpServletRequest proxy = (HttpServletRequest) Proxy.newProxyInstance(loader, interfaces,

                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 判断方法名
                        String methodName = method.getName();
                        if ("getParameter".equals(methodName)) {
                            // 判断请求方式
                            String name = req.getMethod();
                            if (name.equals("POST")) {

                                req.setCharacterEncoding("UT-8");

                                Object object = method.invoke(req, args);
                                return object;
                            } else if (name.equals("GET")) {
                                String value = (String) method.invoke(req, args);
                                byte[] bytes = value.getBytes("ISO-8859-1");
                                value = new String(bytes, "UTF-8");
                                return value;
                            }
                        }
                        // req的其他方法就让他按以前的方式执行
                        return method.invoke(req, args);
                    }
                });


        // 把代理者放行
        chain.doFilter(proxy, response);
    }


    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}