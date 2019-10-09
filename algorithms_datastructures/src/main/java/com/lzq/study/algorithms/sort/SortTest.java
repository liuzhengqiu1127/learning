package com.lzq.study.algorithms.sort;

import java.util.Random;

public class SortTest {

    /**
     * 冒泡排序
     * N个元素排序，需要比较n（n-1）/2次；
     * 冒泡排序的算法复杂度较高，为O（n*n）
     *
     * @param array
     */
    public void bobSort(int[] array)
    {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<array.length-1;i++)//排序轮数
        {
            for (int j=0; j<array.length-i-1;j++){
                if (array[j]>array[j+1]){
                    int temp = array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
        }
        System.out.println("冒泡排序耗时(ms):"+(System.currentTimeMillis() - startTime));
    }

    /**
     * 选择排序
     * N个元素排序，需要比较n（n-1）/2次；
     * 选择排序的算法复杂度仍为O（n*n）；
     * 相比于冒泡排序，选择排序的交换次数大大减少，因此速度要快于冒泡排序
     */
    public void chooseSort(int[] array){
        long startTime = System.currentTimeMillis();
        for(int i=0; i<array.length-1; i++){
            int minIndex = i;
            for(int j=minIndex+1;j<array.length;j++){
                if(array[j]<array[minIndex]){
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        System.out.println("选择排序耗时(ms):"+(System.currentTimeMillis() - startTime));
    }

    /**
     * 插入排序方法
     * 插入排序的时间复杂度仍然为O(n*n)
     * 插入排序的最多比较次数为N*(N-1)/2
     * 插入排序平均比较次数为N*(N-1)/4
     * 插入排序的速度约比冒泡排序快一倍
     * 比选择排序还要快一些，对于基本有序的数据，插入排序的速度会很快，是简单排序中效率最高的排序算法
     */
    public void doInsertSort(int[] array){
        long startTime = System.currentTimeMillis();
        for(int index = 1; index<array.length; index++){//外层向右的index，即作为比较对象的数据的index
            int temp = array[index];//用作比较的数据
            int leftindex = index-1;
            while(leftindex>=0 && array[leftindex]>temp){//当比到最左边或者遇到比temp小的数据时，结束循环
                array[leftindex+1] = array[leftindex];
                leftindex--;
            }
            array[leftindex+1] = temp;//把temp放到空位上
        }
        System.out.println("插入排序耗时(ms):"+(System.currentTimeMillis() - startTime));
    }

    /**
     * 快速排序
     * @param array
     * @param low
     * @param high
     */
    public void quickSort(int[] array, int low, int high)
    {
        if (low >= high) return;
        int index = partition(array,low,high);
        quickSort(array,low,index-1);
        quickSort(array,index+1,high);
    }

    public int partition(int []array,int lo,int hi){
        //固定的切分方式
        int key=array[lo];
        while(lo<hi){
            while(array[hi]>=key&&hi>lo){//从后半部分向前扫描
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){//从前半部分向后扫描
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }


    public static void main(String[] args) {
        simpleSort();
        complexSort();
    }

    private static void complexSort()
    {
        int[] array = new int[100000];
        Random r = new Random();
        SortTest sortTest = new SortTest();
        for (int i=0;i<array.length;i++){
            array[i] = r.nextInt(99999)+1;
        }
        long startTime = System.currentTimeMillis();
        sortTest.quickSort(array,0,array.length-1);
        System.out.println("快速排序耗时(ms):"+(System.currentTimeMillis() - startTime));
    }

    private static void simpleSort()
    {
        int[] array = new int[10000];
        Random r = new Random();
        SortTest sortTest = new SortTest();

        for (int i=0;i<array.length;i++){
            array[i] = r.nextInt(99999)+1;
        }
        sortTest.bobSort(array);

        for (int i=0;i<array.length;i++){
            array[i] = r.nextInt(99999)+1;
        }
        sortTest.chooseSort(array);

        for (int i=0;i<array.length;i++){
            array[i] = r.nextInt(99999)+1;
        }
        sortTest.doInsertSort(array);
    }

}
