package com.lzq.study.lettcode.concurrence;

public class H2O {
    int lock = 2;
    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        synchronized (this){
            while (lock == 0){
                this.wait();
            }
            releaseHydrogen.run();
            lock--;
            this.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        synchronized (this){
            while (lock != 0){
                this.wait();
            }
            releaseOxygen.run();
            lock = 2;
            this.notifyAll();
        }
    }
}
