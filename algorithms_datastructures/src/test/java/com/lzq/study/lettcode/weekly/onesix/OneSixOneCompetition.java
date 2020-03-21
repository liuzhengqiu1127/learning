package com.lzq.study.lettcode.weekly.onesix;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by liuzhengqiu on 2019/11/9.
 */
public class OneSixOneCompetition {

    @Test
    public void test001(){
        Assert.assertTrue(minimumSwap("xx","yy")==1);
        Assert.assertTrue(minimumSwap("xy","yx")==2);
        Assert.assertTrue(minimumSwap("xx","xy")==-1);
        Assert.assertTrue(minimumSwap("xxyyxyxyxx","xyyxyxxxyx")==4);
    }

    public int minimumSwap(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Map<Character,Integer> diffMap = new HashMap<>();
        int diff = 0;
        for (int i = 0; i < chars1.length; i++){
            if (chars1[i] != chars2[i]) {
                diff++;
                diffMap.put(chars1[i],diffMap.getOrDefault(chars1[i],0)+1);
            }
        }
        if (diff%2!=0) return -1;
        int xCount = diffMap.getOrDefault('x',0);
        int yCount = diffMap.getOrDefault('y',0);
        int result = xCount/2 + yCount/2 + (xCount%2) + (yCount%2);
        return result;
    }

    @Test
    public void test002(){
        Assert.assertTrue(numberOfSubarrays(new int[]{1,1,2,1,1},3)==2);
        Assert.assertTrue(numberOfSubarrays(new int[]{2,4,6},1)==0);
        Assert.assertTrue(numberOfSubarrays(new int[]{2,2,2,1,2,2,1,2,2,2},2)==16);
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int res = 0, arr[] = new int[nums.length+2], len=0;
        for (int i = 0; i < nums.length; i++){
            if ((nums[i] & 1) == 1){
                arr[++len] = i;
            }
        }
        arr[0] = -1;
        arr[len + 1] = nums.length;
        for (int i=1; i+k<len+2; i++){
            res += (arr[i] - arr[i-1]) * (arr[i+k] - arr[i+k-1]);
        }
        return res;
    }

    @Test
    public void test003(){
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValid("a)b(c)d"));
        System.out.println(minRemoveToMakeValid("))(("));
        System.out.println(minRemoveToMakeValid("(a(b(c)d)"));
    }
    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();
        Stack<String> stack = new Stack<>();
        List<Integer> removeIndex = new ArrayList<>();
        for (int i = 0; i < s.length(); i++){
            if (chars[i] == '('){
                stack.push(i+"@"+chars[i]);
            }
            if (chars[i] == ')'){
                if (stack.isEmpty()) removeIndex.add(i);
                else stack.pop();
            }
        }
        while (!stack.isEmpty()){
            removeIndex.add(Integer.valueOf(stack.pop().split("@")[0]));
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if (!removeIndex.contains(i)){
                result.append(chars[i]);
            }
        }
        return result.toString();
    }

    public boolean isGoodArray(int[] nums) {
        int len = nums.length, res = nums[0];
        for(int i = 1; i < len; i ++){
            res = gcd(res, nums[i]);
        }
        return res == 1;
    }
    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
