package com.lzq.study.concurrent;

public class SafePointTest {
    static double sum = 0;
    public static void foo(){
        for (int i = 0; i < 0x77777777; i++){
            sum  += Math.sqrt(i);
        }
    }
    public static void bar(){
        for (int i = 0; i < 50000000; i++){
            new Object().hashCode();
        }
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        foo();
        bar();
        double spendTime = (System.currentTimeMillis() - startTime)/1000;
        System.out.println("foo + bar spend time (s):" + spendTime);
    }
}
