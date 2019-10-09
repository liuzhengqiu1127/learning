package com.lzq.study.geektime.test.sort;

/**
 * 选择排序 唯一不属于稳定排序
 */
public class ChooseSort {

    public static void sort(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++)
            {
                if (min > array[j]){
                    min = array[j];
                    minIndex = j;
                }
            }
            if (i != minIndex){
                int tmp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{56,23,12,44,34,134,24,37,78,12,8,0,45};
        sort(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ",");
        }
    }

}
