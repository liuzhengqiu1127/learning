package com.lzq.study.concurrent;

public class ThreadTest implements Runnable {
    int b = 100;
    synchronized void m1() throws InterruptedException {
        System.out.println("m1 begin");
        b = 1000;
        Thread.sleep(500);
        System.out.println("m1 b="+b);
    }
    synchronized void m2() throws InterruptedException {
        System.out.println("m2 begin");
        Thread.sleep(250);
        b = 2000;
        System.out.println("m2 b="+b);
    }

    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.start();
        threadTest.m2();
        System.out.println("main thread b="+threadTest.b);
    }
}
