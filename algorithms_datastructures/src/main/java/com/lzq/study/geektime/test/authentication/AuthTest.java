package com.lzq.study.geektime.test.authentication;

public class AuthTest {

    public static void main(String[] args) {
        AuthFilter authFilter = new AuthFilter();
        authFilter.init();//初始化数据
        System.out.println("精确匹配测试");
        System.out.println(authFilter.verifyAuth("app_id_b","/user/login"));
        System.out.println(authFilter.verifyAuth("app_id_c","/user/take4"));
        authFilter.sortUrl();//排序
        System.out.println(authFilter.verifyAuth2("app_id_c","/user/take2"));
        System.out.println(authFilter.verifyAuth2("app_id_a","/user/login/detail"));
        System.out.println("前缀匹配测试");
        authFilter.initTrie();
        System.out.println(authFilter.match("app_id_b","/user/login/detail"));
        System.out.println(authFilter.match("app_id_a","/user/login/detail"));
    }
}
