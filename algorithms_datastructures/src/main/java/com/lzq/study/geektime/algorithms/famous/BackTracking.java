package com.lzq.study.geektime.algorithms.famous;

/**
 * 回溯算法
 */
public class BackTracking {

    /**
     * 求数组的最大有序长度
     * @param arrays
     * @return
     */
    public int longestIncreaseSubArrayDP(int[] arrays,int index){
        if (index == 0) {
            return 1;
        }
        int max = 0;
        //此问题的解，递归的核心就是在之前的序列中找到最大递增子序列加1
        //所以需要遍历此此之前的全部数据项
        for (int i = 0; i < index; i++) {
            //递归求解每项的最递增序列
            int value = longestIncreaseSubArrayDP(arrays, i);
            if (arrays[i] < arrays[index]) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max + 1;
    }
}
