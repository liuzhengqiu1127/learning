package com.lzq.study.lettcode.biweekly;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ThirtyTwo {

    public int findKthPositive(int[] arr, int k) {
        int max = arr[arr.length-1];
        int less = max - arr.length;
        if (k <= less){
            for (int num=1; num <= max; num++){

                for (int cur : arr){
                    if (cur == num){
                        break;
                    }else if (cur < num){
                        continue;
                    }else {
                        k--;
                        break;
                    }
                }

                if (k == 0){
                    return num;
                }

            }
        }
        return max + (k - less);
    }

    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) return false;
        char[] sourceArr = s.toCharArray();
        char[] targetArr = t.toCharArray();
        int len = s.length();
        Map<Integer, Integer> record = new HashMap<>();
        for (int i=0; i < len; i++){
            int distant = targetArr[i] - sourceArr[i];
            if (distant==0) {
                continue;
            }else if (distant > 0 && distant > k){
                return false;
            }else if (distant > 0 && distant <= k){
                if (record.containsKey(distant)){
                    int mul = 26 * record.get(distant);
                    if (distant + mul > k) return false;
                    record.put(distant, record.get(distant) + 1);
                }else{
                    record.put(distant, 1);
                }
            }else if (distant < 0 && (26+distant) > k){
                return false;
            }else {
                if (record.containsKey(26+distant)){
                    int mul = 26 * record.get(26+distant);
                    if (26+distant + mul > k) return false;
                    record.put(26+distant, record.get(26+distant) + 1);
                }else {
                    record.put(26+distant, 1);
                }
            }
        }
        return true;
    }

    public int minInsertions(String s) {
        int result = 0;
        int record = 0;
        for (char ch : s.toCharArray()){
            if (ch == '(' && record%2!=0){
                result++;
                record += -1;
            }else if (ch == '(' && record%2==0){
                record += -2;
            }
            else if (ch == ')' && record >= 0){
                result++;
                record += -1;
            }
            else {
                record += 1;
            }
        }
        if (record == 0) return result;
        return result + Math.abs(record);
    }


    @Test
    public void test(){
        Assert.assertTrue(minInsertions("(()))")==1);
        Assert.assertTrue(minInsertions("())")==0);
        Assert.assertTrue(minInsertions("))())(")==3);
        Assert.assertTrue(minInsertions("((((((")==12);
        Assert.assertTrue(minInsertions(")))))))")==5);
        Assert.assertTrue(minInsertions("(()))(()))()())))")==4);
        Assert.assertTrue(minInsertions("))(()()))()))))))()())()(())()))))()())(()())))()(")==16);
    }

}
