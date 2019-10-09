package com.lzq.study.geektime.algorithms.recursion;

import java.util.HashMap;
import java.util.Map;

public class RecursionCal {
    private static final int MAX_LOOP = 1894;
    private int loop = 0;
    private Map<Integer,Integer> hashMap = new HashMap<Integer, Integer>();

    /**
     *  你可以一次走1步，或者走2步，求解有多少种走法
     * @param n
     * @return
     */
    public int getFx(int n) throws Exception {
        if (loop > MAX_LOOP) throw new Exception("exceed max");
        if (n == 1) return 1;
        if (n == 2) return 2;
        loop++;
        if (hashMap.containsKey(n)){
            return hashMap.get(n);
        }
        int result = getFx(n-1) + getFx(n-2);
        hashMap.put(n,result);
        return result;
    }
}
