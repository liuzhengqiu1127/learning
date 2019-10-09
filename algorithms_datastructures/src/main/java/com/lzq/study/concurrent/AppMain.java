package com.lzq.study.concurrent;

import java.util.concurrent.CountDownLatch;

public class AppMain {
    private static int i = 0;
    static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            for (;;){
                if (countDownLatch.getCount()==1){
                    System.out.println("thread1:"+i);
                    i++;
                    countDownLatch.countDown();
                }
            }
        });
        Thread thread2 = new Thread(()->{
            for (;;){
                if (countDownLatch.getCount()==0) {
                    System.out.println("thread2:" + i);
                    i++;
                    countDownLatch = new CountDownLatch(1);
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
