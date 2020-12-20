package com.lzq.study.lettcode.weekly.twotwo;

import org.junit.Test;

/**
 * Created by liuzhengqiu on 2020/12/20.
 */
public class TwoTwoZero {

    public String reformatNumber(String number) {
        number = number.replaceAll(" ","");
        number = number.replaceAll("-","");
        int length = number.length();
        StringBuilder result = new StringBuilder();
        int index = 0;
        while (length > 0){
            if (length == 2 || length == 3){
                result.append(number.substring(index));
                break;
            }
            if(length == 4){
                result.append(number.substring(index,index+2));
                result.append("-");
                result.append(number.substring(index+2,index+4));
                break;
            }
            result.append(number.substring(index,index+3));
            result.append("-");
            index += 3;
            length -= 3;
        }
        return result.toString();
    }

    public int maximumUniqueSubarray(int[] nums){
        int[] array = new int[nums.length + 1];
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++){
            array[i+1] = array[i] + nums[i];
            maxLength = Integer.max(maxLength, nums[i]);
        }
        int max = 0;
        boolean[] sign = new boolean[maxLength + 1];
        int start = 0;
        int end = 0;
        while (end < nums.length){
            for (; end < nums.length; end++){
                if (sign[nums[end]]) break;
                sign[nums[end]] = true;
            }
            int sum = array[end] - array[start];
            max = Integer.max(max,sum);
            if (end < nums.length){
                while (start < end && nums[end] != nums[start]){
                    sign[nums[start]] = false;
                    start++;
                }
                sign[nums[start]] = false;
                start++;
            }
        }
        return max;
    }

    @Test
    public void test(){
        System.out.println(reformatNumber("--17-5 229 35-39475 "));
    }
}
