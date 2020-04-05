package com.lzq.study.lettcode.weekly.oneeight;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneEightThree {

    @Test
    public void test01(){
        System.out.println(minSubsequence(new int[]{4,3,10,9,8}));
        System.out.println(minSubsequence(new int[]{4,4,7,6,7}));
        System.out.println(minSubsequence(new int[]{6}));
    }

    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
        }
        List<Integer> result = new ArrayList<>();
        int countSum = 0;
        for (int i=nums.length-1; i>=0; i--){
            countSum += nums[i];
            result.add(nums[i]);
            if (countSum > sum - countSum){
                break;
            }
        }
        return result;
    }

    @Test
    public void test02(){
        Assert.assertTrue(numSteps("1")==0);
    }

    public int numSteps(String s) {
        return getSteps(s,0);
    }
    private int getSteps(String s, int sum){
        if (s.length()==1){
            return sum;
        }
        int length = s.length();
        char lastChar = s.charAt(length-1);
        if (lastChar=='0'){
            return getSteps(s.substring(0,length-1),sum+1);
        }else {
            return getSteps(addOne(s),sum + 1);
        }
    }
    private String addOne(String s){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('0');
        int i=s.length()-2;
        boolean flag = false;
        for (; i>=0; i--){
            if (flag){
                stringBuilder.append(s.charAt(i));
            }else{
                if (s.charAt(i) == '1'){
                    stringBuilder.append('0');
                }else{
                    stringBuilder.append('1');
                    flag = true;
                }
            }
        }
        if (!flag){
            stringBuilder.append('1');
        }
        return stringBuilder.reverse().toString();
    }

    
    public String longestDiverseString(int a, int b, int c) {
        MyChar[] myChars = new MyChar[]{
                new MyChar('a', a),
                new MyChar('b', b),
                new MyChar('c', c),
        };
        StringBuilder sb = new StringBuilder();
        while (true) {
            Arrays.sort(myChars);
            //先放最多的, 如果上个放的2个字符串和剩余个数最多的字符相同，则放置次多的字符
            if (sb.length() >= 2 &&
                    sb.charAt(sb.length() - 1) == myChars[2].ch &&
                    sb.charAt(sb.length() - 2) == myChars[2].ch) {
                if (myChars[1].count-- > 0) {
                    sb.append(myChars[1].ch);
                } else {
                    break;
                }

            } else {
                if (myChars[2].count-- > 0) {
                    sb.append(myChars[2].ch);
                } else {
                    break;
                }
            }
        }
        return sb.toString();
    }
    private class MyChar implements Comparable {
        char ch;
        int count;

        public MyChar(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
        @Override
        public int compareTo(Object o) {
            MyChar other = (MyChar)o;
            return this.count - other.count;
        }
    }

}
