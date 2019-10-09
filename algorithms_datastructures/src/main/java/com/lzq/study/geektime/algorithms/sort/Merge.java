package com.lzq.study.geektime.algorithms.sort;

/**
 * 合并排序
 */
public class Merge {

    public static void sort(int arr[], int n){
        sort(arr,0,n-1);
    }

    private static void sort(int arr[], int p, int r){
        if (p >= r) return;
        int q = (p + r)/2;
        sort(arr,0,q);
        sort(arr,q+1,r);
        merge(arr,p,r,q);
    }
    private static void merge(int[] arr,int p,int r,int q) {
        int i=p,j=q+1,k=0;
        int[] tmp = new int[r-p+1];
        while (i<=q && j<=r){
            if (arr[i] <= arr[j]){
                tmp[k++] = arr[i++];
            }else {
                tmp[k++] = arr[j++];
            }
        }
        int start = i,end = q;
        if (j<=r){
            start = j;
            end = r;
        }
        while(start<=end){
            tmp[k++] = arr[start++];
        }
        for (int f=0;f<tmp.length;f++){
            arr[p+f] = tmp[f];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,0,4,1,5,6,8};
        sort(arr,arr.length);
        System.out.println("");
    }

}
