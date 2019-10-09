package com.lzq.study.geektime.algorithms.sort;

/**
 * 插入排序
 */
public class Inserting {

    public static void sort(int[] arr){
        if (arr.length <= 1) return;
        int n = arr.length;
        for (int i = 1; i < n ; i++){
            int value = arr[i];
            int j = i-1;
            for (; j >= 0; j--){
                if (arr[j] < value){
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }
            arr[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,8,5,6,7};
        sort(arr);
        for (int i =0;i<arr.length;i++) System.out.println(arr[i]);
    }

}
