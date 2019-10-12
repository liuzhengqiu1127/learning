package com.lzq.study.lettcode.concurrence;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Foo {
    private CountDownLatch flagOne = new CountDownLatch(1);
    private CountDownLatch flagTwo = new CountDownLatch(1);
    private Semaphore second = new Semaphore(0);
    private Semaphore third = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        second.release();
        flagOne.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        flagOne.await();
        second.acquire();

        printSecond.run();

        third.release();
        flagTwo.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        flagTwo.await();
        third.acquire();
        printThird.run();
    }
}
