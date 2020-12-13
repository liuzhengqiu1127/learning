package com.lzq.study.lettcode.weekly.twoone;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by liuzhengqiu on 2020/12/13.
 */
public class TwoOneNine {

    public int numberOfMatches(int n) {
        int result = 0;
        while (n > 1){
            if (n % 2 == 0){
                result += n/2;
                n = n/2;
            }else{
                result += (n-1)/2;
                n = (n-1)/2 + 1;
            }
        }
        return result;
    }

    public int minPartitions(String n) {
        char[] temp = {'9','8','7','6','5','4','3','2','1','0'};
        for (char ch : temp){
            if (n.indexOf(ch+"")!=-1){
                return Integer.valueOf(ch+"");
            }
        }
        return 0;
    }

    public int stoneGameVII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int bobSum = 0;
        int alisSum = 0;
        int start = 0, end = stones.length - 1;
        int count = 1;
        while (start < end){
            if (start + 2 >= end){
                if (stones[start] > stones[end]){
                    if (count % 2 == 1) alisSum += sum - stones[end];
                    else bobSum += sum - stones[end];
                    sum -= stones[end];
                    end--;
                }else{
                    if (count % 2 == 1) alisSum += sum - stones[start];
                    else bobSum += sum - stones[start];
                    sum -= stones[start];
                    start++;
                }
            }else{
                int startNumber = stones[start];
                int endNumber = stones[end];
                int startNumber1 = stones[start+1];
                int endNumber1 = stones[end-1];
                int chaJu1 = Integer.min(startNumber1,endNumber);
                int chaJu2 = Integer.min(endNumber1,startNumber);
                if (chaJu1 > chaJu2){
                    if (count % 2 == 1) alisSum += sum - startNumber;
                    else bobSum += sum - startNumber;
                    sum -= startNumber;
                    start++;
                }else if (chaJu1 < chaJu2){
                    if (count % 2 == 1) alisSum += sum - endNumber;
                    else bobSum += sum - endNumber;
                    sum -= endNumber;
                    end--;
                }else {
                    if (startNumber > endNumber){
                        if (count % 2 == 1) alisSum += sum - endNumber;
                        else bobSum += sum - endNumber;
                        sum -= endNumber;
                        end--;
                    }else{
                        if (count % 2 == 1) alisSum += sum - startNumber;
                        else bobSum += sum - startNumber;
                        sum -= startNumber;
                        start++;
                    }
                }
            }
            count++;
        }
        return alisSum - bobSum;
    }


    @Test
    public void test(){
//        Assert.assertTrue(numberOfMatches(7)==6);
//        Assert.assertTrue(minPartitions("27346209830709182346")==9);
        System.out.println(stoneGameVII(new int[]{1,1,1,1,1,1,1,7,1,1,1,1,1,1,1}));
    }
}
