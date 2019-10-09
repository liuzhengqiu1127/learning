package com.lzq.study.geektime.test.sort;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 获取数据第K大元素，复杂度为O(n)
     * @param arr
     * @param p
     * @param r
     * @return
     */
    public static int findK(int[] arr,int p, int r,int k){
        if (k-1 > r || k-1 < p) return -1;
        int point = separate2(arr,p,r);
        if (k -1 == point) return arr[point];
        else if (k - 1 > point) return findK(arr,point+1,r,k);
        else return findK(arr, 0,point-1,k);
    }

    public static int separate2(int[] arr, int p, int r)
    {
        int value = arr[r];
        int k = p;
        for (int i = p; i <r; i++){
            if (arr[i] > value){
                int tmp = arr[k];
                arr[k] = arr[i];
                arr[i] = tmp;
                k++;
            }
        }
        int tmp = arr[k];
        arr[k] = arr[r];
        arr[r] = tmp;
        return k;
    }

    public static void sort(int[] arr,int p,int r)
    {
        if (p >= r) return;
        //分割点
        int k = separate(arr,p,r);
        sort(arr,p,k-1); // 分割点左边都小于，右边的都大于，所以不需要对分割点再排序
        sort(arr,k+1,r);
    }

    private static int separate(int[] arr, int p,int r)
    {
        int i = p, k = p;
        while (i < r){ // 不需要使用分割点
            if (arr[i] < arr[r]){
                int tmp = arr[i];
                arr[i] = arr[k];
                arr[k] = tmp;
                k++;
            }
            i++;
        }
        int tmp = arr[r];
        arr[r] = arr[k];
        arr[k] = tmp;
        return k;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8,3,11,4,7,2,123,43,27,13,20,3,2};
        sort(arr,0,arr.length-1);
        for (int i=0;i<arr.length;i++) System.out.print(arr[i] + ",");
        System.out.println("===========");
        System.out.println(findK(arr,0,arr.length-1,10));
    }
}
