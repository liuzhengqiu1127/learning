package com.lzq.study.lettcode.concurrence;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;
    AtomicInteger atomicInteger = new AtomicInteger(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        synchronized (this){
            while (atomicInteger.get() <= n){
                while(atomicInteger.get() % 3 != 0 || atomicInteger.get() % 15 == 0){
                    this.wait();
                    if (atomicInteger.get() > n) return;
                }
                printFizz.run();
                atomicInteger.incrementAndGet();
                this.notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        synchronized (this){
            while (atomicInteger.get() <= n){
                while(atomicInteger.get() % 5 != 0 || atomicInteger.get() % 15 == 0){
                    this.wait();
                    if (atomicInteger.get() > n) return;
                }
                printBuzz.run();
                atomicInteger.incrementAndGet();
                this.notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        synchronized (this){
            while (atomicInteger.get() <= n){
                while(atomicInteger.get() % 15 != 0 ){
                    this.wait();
                    if (atomicInteger.get() > n) return;
                }
                printFizzBuzz.run();
                atomicInteger.incrementAndGet();
                this.notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized (this){
            while (atomicInteger.get() <= n){
                while(atomicInteger.get() % 3 == 0 || atomicInteger.get() % 5 == 0){
                    this.wait();
                    if(atomicInteger.get() > n) return;
                }
                printNumber.accept(atomicInteger.get());
                atomicInteger.incrementAndGet();
                this.notifyAll();
            }
        }
    }
}
