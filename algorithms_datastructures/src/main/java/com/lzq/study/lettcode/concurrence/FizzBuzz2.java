package com.lzq.study.lettcode.concurrence;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Created by liuzhengqiu on 2019/10/14.
 */
public class FizzBuzz2 {
    private int n;
    private volatile int cur = 1;
    Semaphore sa = new Semaphore(0);
    Semaphore sb = new Semaphore(0);
    Semaphore sc = new Semaphore(0);
    Semaphore sd = new Semaphore(0);

    public FizzBuzz2(int n){
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true){
            sa.acquire();
            if (cur > n) break;
            printFizz.run();
            sd.release();
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true){
            sb.acquire();
            if (cur > n) break;
            printBuzz.run();
            sd.release();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true){
            sc.acquire();
            if (cur > n) break;
            printFizzBuzz.run();
            sd.release();
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true){
            if (cur % 3 != 0 && cur % 5 != 0){
                printNumber.accept(cur);
            }
            else if (cur % 3 == 0 && cur % 5 != 0){
                sa.release();
                sd.acquire();
            }
            else if (cur % 5 == 0 && cur % 3 != 0){
                sb.release();
                sd.acquire();
            }else {
                sc.release();
                sd.acquire();
            }
            cur++;
            if (cur > n){
                sa.release();
                sb.release();
                sc.release();
                break;
            }
        }
    }

}
