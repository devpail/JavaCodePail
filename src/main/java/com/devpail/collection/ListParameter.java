package com.devpail.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: list传参
 * @author: zhangzb10
 * @create: 2021-05-27 12:17
 **/

public class ListParameter {

    public static void updateList(List<String> list) {
        list = new ArrayList<>();
        list.add("bbb");
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        updateList(list);
        System.out.println(list);
    }
}
