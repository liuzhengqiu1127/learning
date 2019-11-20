package com.lzq.study.concurrent;

import org.junit.Test;

/**
 * Created by liuzhengqiu on 2019/11/20.
 */
public class ShopTest {

    @Test
    public void findPrices(){
        BestFinder bestFinder = new BestFinder();
        long start = System.currentTimeMillis();
        System.out.println(bestFinder.findPrices("iPhonX"));
        System.out.println("done:"+(System.currentTimeMillis()-start) + "msecs");
    }
    @Test
    public void findPricesParallel(){
        BestFinder bestFinder = new BestFinder();
        long st = System.currentTimeMillis();
        System.out.println(bestFinder.findPricesParallel("iPhonX"));
        System.out.println("done : " + (System.currentTimeMillis() - st) + "msecs");
    }
    @Test
    public void findPricesAsync(){
        BestFinder bestFinder = new BestFinder();
        long st = System.currentTimeMillis();
        System.out.println(bestFinder.findPricesAsync("iPhonX"));
        System.out.println("done : " + (System.currentTimeMillis() - st) + "msecs");
    }
}
