package com.lzq.study.geektime.algorithms.sort;

public class Heap {

    public static void buildHeap(int[] a,int n){
        for (int i = n/2; i >= 1; --i){
            heapify(a,n,i);
        }
    }
    public static void heapify(int[] a, int n, int i){
        while (true){
            int maxPos = i;
            if ( 2 * i <= n && a[2 * i] > a[i] ) maxPos = 2 * i;
            if ( 2 * i + 1 <= n && a[2 * i + 1] > a[maxPos]) maxPos = 2 * i + 1;
            if (maxPos == i) break;
            int tmp = a[i];
            a[i] = a[maxPos];
            a[maxPos] = tmp;
            i = maxPos;
        }
    }
    public static void sort(int[] a, int n) {
        buildHeap(a,n);
        int k = n;
        while (k > 1){
            int tmp = a[k];
            a[k] = a[1];
            a[1] = tmp;
            --k;
            heapify(a,k,1);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-2,3,2,9,5,8,6,7,1};
        sort(arr,arr.length-1);
        System.out.println();
    }
}
