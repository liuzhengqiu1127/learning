package com.lzq.study.algorithms.first;

import java.util.Random;

public class One {

    public static void main(String[] args) {
        One one = new One();
        int[] array = one.initArray(10000);
        long start1 = System.currentTimeMillis();
        int k = one.chooseMax(5000,array);
        System.out.println("N=10000,spend time(ms):"+(System.currentTimeMillis()-start1)+",k="+k);

        array = one.initArray(100000);
        long start2 = System.currentTimeMillis();
        k = one.chooseMax(50000,array);
        System.out.println("N=100000,spend time(ms):"+(System.currentTimeMillis()-start2)+",k="+k);

        array = one.initArray(200000);
        long start3 = System.currentTimeMillis();
        k = one.chooseMax(100000,array);
        System.out.println("N=1000000,spend time(ms):"+(System.currentTimeMillis()-start3)+",k="+k);
    }

    private int[] initArray(int n)
    {
        Random random = new Random();
        int[] result = new int[n];
        for (int i=0;i<n;i++){
            result[i] = Math.abs(random.nextInt() % 1000000);
        }
        return result;
    }

    private int chooseMax(int k, int[] result)
    {
        int size = result.length;
        int[] result2 = new int[k];

        for (int i=0;i<k;i++){
            result2[i] = result[i];
        }

        for (int i=0;i<k-1;i++){
            int max = i;
            for (int j=i+1;j<k;j++){
                if (result2[max] < result2[j]){
                    max = j;
                }
            }
            int temp = result2[i];
            result2[i] = result2[max];
            result2[max] = temp;
        }

        for (int i=k;i<size;i++){
            if (result[i] <= result2[k-1]){
                continue;
            }
            if (result[i] >= result2[0]){
                for (int j=k-1;j>0;j--){
                    result2[j] = result2[j-1];
                }
                result2[0] = result[i];
                continue;
            }
            for (int j=k-1;j>=0;j--){
                if (result[i] < result2[j]){
                    for (int s=k-1;s>j+1;s--){
                        result2[s] = result2[s-1];
                    }
                    result2[j+1] = result[i];
                    break;
                }
            }
        }

        return result2[k-1];

    }
}
