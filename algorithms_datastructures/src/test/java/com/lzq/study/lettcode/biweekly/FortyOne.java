package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by liuzhengqiu on 2020/12/12.
 */
public class FortyOne {

    public int countConsistentStrings(String allowed, String[] words) {
        int result = 0;
        char[] chars = allowed.toCharArray();
        for (String word : words){
            for (char ch : chars){
                word = word.replaceAll(""+ch,"");
                if (word.isEmpty()){
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 纯粹数学题
     * 2,3,4,5
     * 2 -> (2+3+4+5)-2*4+(2*0-0)*2
     * 3 -> (2+3+4+5)-3*4+(3*1-2)*2
     * 4 -> (2+3+4+5)-4*4+(4*2-(2+3))*2
     * 5 -> (2+3+4+5)-5*4+(5*3-(2+3+4))*2
     * @param nums
     * @return
     */
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int[] result = new int[nums.length];
        int su = 0;
        for (int i = 0; i < nums.length; i++){
            result[i] = sum - nums[i]*nums.length + (nums[i]*i - su)*2;
            su += nums[i];
        }
        return result;
    }

    @Test
    public void test(){
        System.out.println(getSumAbsoluteDifferences(new int[]{4,7,7,7,10}));
    }
}
