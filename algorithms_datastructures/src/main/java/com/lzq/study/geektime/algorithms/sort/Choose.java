package com.lzq.study.geektime.algorithms.sort;

/**
 * 选择排序
 */
public class Choose {

    public static void sort(int[] arr){
        if (arr.length <= 1) return;
        int n = arr.length;
        for (int i = 0; i < n ; i++){
            int max = i;
            for (int j = i + 1; j < n; j++){
                if (arr[j] > arr[max]){
                    max = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
        }
    }
}
