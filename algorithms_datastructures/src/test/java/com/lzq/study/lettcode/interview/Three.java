package com.lzq.study.lettcode.interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Three {

    @Test
    public void test01(){
        System.out.println(sequentialDigits(10,841554424));
    }

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        int beginLength = String.valueOf(low).length();
        int beginNumber = Integer.valueOf(String.valueOf(low).substring(0,1));
        initSequentialDigit(beginNumber,beginLength,low, high,result);
        return result;
    }
    private void initSequentialDigit(int beginNumber, int beginLength, int low, int high, List<Integer> result){
        if (beginLength > 9) return;
        if (beginNumber > (10-beginLength)){
            initSequentialDigit(1,beginLength+1, low, high,result);
            return;
        }
        int value = getValue(beginNumber,beginLength);
        if (value > high) return;
        if (value >= low) {
            result.add(value);
        }
        if (beginNumber==(10-beginLength)){
            initSequentialDigit(1,beginLength+1, low, high,result);
        }else {
            initSequentialDigit(beginNumber+1,beginLength, low, high,result);
        }
    }
    private int getValue(int beginNumber, int length){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(beginNumber);
        for (int i=1; i<length; i++){
            stringBuilder.append(beginNumber+i);
        }
        return Integer.valueOf(stringBuilder.toString());
    }

    public boolean isPossibleDivide(int[] nums, int k) {
        int len = nums.length;
        if (len % k !=0) return false;
        Arrays.sort(nums);
        return false;
    }
}
