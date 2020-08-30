package com.lzq.study.lettcode.weekly.twozero;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TwoZeroFour {

    /**
     * 1-m一起来比，而不是一个个比
     * @param arr
     * @param m
     * @param k
     * @return
     */
    public boolean containsPattern(int[] arr, int m, int k){
        StringBuilder stringBuilder = new StringBuilder();
        for (int number : arr) stringBuilder.append(number);
        String string = stringBuilder.toString();
        for (int j = 0; j < arr.length - m; j++){
            int count = 1;
            String pre = string.substring(j, j+m);
            for (int i = j+m; i < string.length(); i+=m){
                int next = i + m > string.length() ? string.length() - 1 : i+m;
                String temp = string.substring(i,next);
                if (temp.equals(pre)){
                    if (++count >= k) return true;
                }else {
                    break;
                }
            }
        }
        return false;
    }

    public int getMaxLen(int[] nums){
        int max = 0;
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<nums.length; i++){
            if (nums[i] > 0) list.add(1);
            else if (nums[i] < 0) list.add(-1);
            else {
                max = Math.max(countMinusNumber(list), max);
                list.clear();
            }
        }
        if (list.size() != 0) max = Math.max(countMinusNumber(list), max);
        return max;
    }
    public int countMinusNumber(List<Integer> list){
        int count = 0, max = 0;
        int multiply = 1;
        for (int i = 0; i < list.size(); i++){
            multiply *= list.get(i);
            count++;
            if (multiply > 0){
                max = Math.max(count, max);
            }
        }
        count = 0;
        multiply = 1;
        for (int i=list.size()-1; i>=0; i--){
            multiply *= list.get(i);
            count++;
            if (multiply > 0){
                max = Math.max(count, max);
            }
        }
        return max;
    }

    @Test
    public void test(){
        System.out.println(getMaxLen(new int[]{1,2,3,5,-6,4,0,10}));
    }
}
