package com.lzq.study.geektime.test.sort;

/**
 * 直接插入排序
 */
public class InsertSort {

    public static void sort(int[] array)
    {
        for (int i = 1; i < array.length; ++i)
        {
            int value = array[i];//选取第i个元素
            int j = i -1;
            for (;j>=0;--j){ // 对比 [0,i-1] 与 i 的元素大小
                if (array[j] > value){ // 大于则往后移动1位，因为[0, i-1]已经按照从小到大的顺序，
                    // 所以不会出现 后面的小于 value , 前面大于value的情况。
                    array[j+1] = array[j];
                }else {
                    break;
                }
            }
            array[j+1] = value;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,8,2,5,1,3,8,7,23};
        sort(array);
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i] + ",");
        }
    }
}
