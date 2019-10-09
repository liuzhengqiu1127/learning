package com.lzq.study.geektime.test.sort;

/**
 * 冒泡排序
 */
public class BubblingSort {

    public static void sort(int[] array){

        for (int i = 0; i < array.length; ++i)
        {
            boolean flag = false;
            for (int j = 0; j < array.length - 1 - i; ++j) // 从小到大排序，每次把最大的元素放到最后面了，就可以少比较一个
            {
                if (array[j] > array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }
}
