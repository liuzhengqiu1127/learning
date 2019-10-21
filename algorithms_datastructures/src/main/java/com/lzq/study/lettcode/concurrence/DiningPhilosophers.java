package com.lzq.study.lettcode.concurrence;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    ReentrantLock[] reentrantLocks = {
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()
    };
    Semaphore semaphore = new Semaphore(4);

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int leftFork = (philosopher + 1)%5;
        int rightFork = philosopher;
        semaphore.acquire();
        reentrantLocks[leftFork].lock();
        reentrantLocks[rightFork].lock();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        reentrantLocks[leftFork].unlock();
        reentrantLocks[rightFork].unlock();
        semaphore.release();
    }
}
