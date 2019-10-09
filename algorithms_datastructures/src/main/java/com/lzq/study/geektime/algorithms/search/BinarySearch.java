package com.lzq.study.geektime.algorithms.search;

/**
 * 二分查找
 */
public class BinarySearch {

    /**
     * 从数组中查找第一个等于value的元素
     * @param array
     * @param value
     * @return
     */
    public static int findFirst(int[] array,int value){
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] > value) {
                high = mid - 1;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || array[mid - 1] != value) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    public static int findFirst2(int[] array,int value){
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] >= value) {
                if (mid == 0 || array[mid - 1] < value) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 从数组中查找最后一个等于value的元素
     * @param array
     * @param value
     * @return
     */
    public static int findLast(int[] array,int value){
        int low = 0;
        int high = array.length - 1;
        while (low <= high){
            int mid = low + ((high-low)>>1);
            if (array[mid] > value) {
                high = mid - 1;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == array.length - 1 || array[mid + 1] != value) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 数组从小到大排列，并且不存在重复元素
     * @param array
     * @param value
     * @return
     */
    public static int bSearch(int[] array,int value)
    {
        int low = 0;
        int high = array.length - 1;
        while (low <= high){
            int mid = low + ((high-low)>>1);
            if (array[mid] == value) return mid;
            else if (array[mid] < value){
                low = mid + 1;
            }else {
                high = mid -1;
            }
        }
        return -1;
    }

    public static int bSearch(int[] arr, int low, int high, int value){
        if (low > high) return -1;
        int mid = low + ((high-low)>>1);
        if (arr[mid] == value) return mid;
        else if (arr[mid] > value) return bSearch(arr,low,mid-1,value);
        else return bSearch(arr,mid+1,high,value);
    }

    public static double getSqrt(double value){
        if (value == 1) return 1;
        double min = value;
        double max = 1;
        if (value > 1){
            min = 1;
            max = value;
        }
        while (min <= max){
            double mid = (max + min)/2;
            if ((mid+0.000001)*(mid+0.000001) > value && (mid-0.000001)*(mid-0.000001) < value) return mid;
            if (mid * mid > value) max = mid-0.0000001;
            if (mid * mid < value) min = mid + 0.0000001;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findFirst2(new int[]{1,2,3,4,4,8,9},2));
    }
}
