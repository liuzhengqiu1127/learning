package com.lzq.study.geektime.test.search;

/**
 * 二分查找
 */
public class BinarySearch {

    /**
     * 大于等于 data的第一个值
     * @param arr
     * @param data
     * @return
     */
    public static int findFirst(int[] arr, int data)
    {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high){
            int mid = low + ((high-low)>>1);
            if (arr[mid] < data){
                low = mid + 1;
            }else {
                if (mid==0||arr[mid-1]<data) return mid;
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int find(int[] arr,int data)
    {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high){
//            int mid = (low + high)/2;
            int mid = low + ((high-low)>>1);
            if (arr[mid] < data){
                low = mid + 1;
            }
            else if (arr[mid] > data){
                high = mid - 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,45,56,78,89};
        System.out.println(find(arr,45));
        System.out.println(find(arr,100));
        System.out.println(findFirst(arr,50));
     }
}
