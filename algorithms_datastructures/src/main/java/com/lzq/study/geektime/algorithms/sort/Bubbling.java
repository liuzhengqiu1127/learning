package com.lzq.study.geektime.algorithms.sort;

public class Bubbling {

    /**
     * 冒泡排序,从大到小排列
     * @param arr
     * @param n
     * @return
     */
    public void sort(int[] arr, int n)
    {
        if (n <= 1) return ;
        for (int i = 0; i < n; i++)
        {
            boolean flag = false;
            for (int j = 0; j < n-i-1; j++)
            {
                if (arr[j] < arr[j + 1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }
}
