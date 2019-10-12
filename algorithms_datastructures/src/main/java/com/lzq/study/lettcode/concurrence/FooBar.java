package com.lzq.study.lettcode.concurrence;


import java.util.concurrent.atomic.AtomicInteger;

public class FooBar {
    private int n;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < n; i++) {
                while (atomicInteger.get() == 1) {
                    this.wait();
                }
                printFoo.run();
                atomicInteger.incrementAndGet();
                this.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < n; i++) {
                while (atomicInteger.get() == 0){
                    this.wait();
                }
                printBar.run();
                atomicInteger.decrementAndGet();
                this.notifyAll();
            }
        }
    }
}
