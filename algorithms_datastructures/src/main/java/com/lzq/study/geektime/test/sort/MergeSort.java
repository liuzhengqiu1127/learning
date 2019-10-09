package com.lzq.study.geektime.test.sort;

/**
 * 归并排序
 */
public class MergeSort {

    public static void sort(int[] array){
        sort(array,0,array.length-1);
    }

    private static void sort(int arr[], int p, int r){
        if (p >= r) return;
        int q = (p + r)/2; // 分隔点在中间
        sort(arr,0,q); // 不停分
        sort(arr,q+1,r);
        merge(arr,p,q,r); // 分完再进行合并
    }

    private static void merge(int arr[], int p, int q, int r)
    {
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r-p+1];//定义长度
        while (i <= q && j <= r){ // 把两个分区[p q],[q+1,r]合并
            if (arr[i] < arr[j]){
                tmp[k++] = arr[i++];
            }else {
                tmp[k++] = arr[j++];
            }
        }
        int start = i,end = q;
        if (j <= r){
            start = j;
            end = r;
        }
        while (start <= end){
            tmp[k++] = arr[start++];
        }
        for (int t=0;t<tmp.length;t++){ // tmp [0, r-p+1]  ->  arr [p, r]
            arr[p+t] = tmp[t];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8,3,11,4,7,2,123,43,27,13,20,3,2};
        sort(arr);
        for (int i=0;i<arr.length;i++) System.out.print(arr[i] + ",");
    }



}
