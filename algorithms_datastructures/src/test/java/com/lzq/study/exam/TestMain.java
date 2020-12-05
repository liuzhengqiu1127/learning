package com.lzq.study.exam;

import org.junit.Assert;
import org.junit.Test;

public class TestMain {

    @Test
    public void test01(){
        Assert.assertTrue(longestOnes(new int[]{0,0,0,0,0,0},3) == getMaxOneCount(new int[]{0,0,0,0,0,0},3));
    }

    /**
     *
     * @param arr
     * @param count
     * @return
     */
    public int getMaxOneCount(int[] arr, int count){
        int result = 0;
        for (int i = 0; i < arr.length; i++){
            int[] temp = new int[arr.length];
            System.arraycopy(arr,0,temp,0,arr.length);
            int curCount = 0;
            for (int j = i; j < temp.length; j++){
                if (temp[j] == 0){
                    temp[j] = 1;
                    curCount++;
                }
                if (curCount >= count) break;
            }

            int everyCount = 0;
            for (int j = 0; j < temp.length; j++){
                if (temp[j] == 1){
                    everyCount++;
                }else{
                    result = Integer.max(everyCount,result);
                    everyCount = 0;
                }
            }
            result = Integer.max(everyCount,result);

        }
        return result;
    }

    public int longestOnes(int[] A, int K){
        int max = 0;
        for (int left=0, right=0; right < A.length; right++){
            if (A[right] == 0){
                if (K == 0){
                    while (A[left] == 1) left++;
                    left++;
                }else {
                    K--;
                }
            }
            max = Integer.max(right - left + 1,max);
        }
        return max;
    }
}
