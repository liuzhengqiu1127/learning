package com.lzq.study.geektime.algorithms.sort;

/**
 * 计数排序
 */
public class Counting {

    public static void sort(int[] arr, int n){
        if (n <= 1) return;
        int max = arr[0];//找到最大值
        for (int i = 1; i < n;i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        int carr[] = new int[max + 1];//基于值建立index=[0,max]的数组
        for (int i=0; i <= max;i++) carr[i] = 0;
        for (int i=0;i<n;i++){
            carr[arr[i]]++;//统计每个value的频率
        }
        for (int i=1;i<=max;i++){
            carr[i] = carr[i] + carr[i-1];//从[0,max]统计相加，表示排序后的下标
        }
        //关键实现部分
        int[] rArr = new int[n];
        for (int i=n-1;i>=0;i--){
            int index = carr[arr[i]] - 1;//获取下标
            rArr[index] = arr[i];//下标对应的值
            carr[arr[i]]--;//相同值放入同一个数组需要--
        }
        for (int i=0;i<n;i++){
            arr[i] = rArr[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,5,3,0,4,3,2,1,1,2,3,4,0,2,1};
        sort(arr,arr.length);
        System.out.println("");
    }

}
