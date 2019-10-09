package com.lzq.study.concurrent.cache;

public class CacheTest {
    private long count = 0;
    private void add10K() {
        int idx = 0;
        while(idx++ < 10000) {
            count += 1;
        }
        System.out.println(count);
    }
    public long calc() throws InterruptedException {
        final CacheTest test = new CacheTest();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(()->{
            test.add10K();
        });
        Thread th2 = new Thread(()->{
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        CacheTest cacheTest = new CacheTest();
        System.out.println(cacheTest.calc());
    }
}

