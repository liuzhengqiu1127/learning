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

    public int longestAwesome(String s){
        if (s.length() <= 1) return s.length();
        Map<Integer,Integer> umap = new HashMap<>();
        umap.put(0,-1);
        int state = 0;
        int ans = 1;
        for (int i = 0; i<s.length(); i++){
            /**
             * 0： 1
             * 1： 10
             * 2： 100
             * 3： 1000
             * 统计前缀每个字符异或求值，可以分为三种情况
             * 如果是回文分为如下两种情况：
             * 1，state == 0, 表示已经为回文
             * 2，state == (1,10,100,1000,...,1000000000)任一一个，表示只有一个奇数，也是回文
             * 如果不是回文：
             * 1和2都不满足，这个时候就把这个state和下标进行记录
             */
            int mask = 1 << (s.charAt(i) - '0');
            state ^= mask;

            /**
             * 解决场景问题：
             *  123321
             *  562323
             */
            if (umap.containsKey(state)){
                ans = Math.max(i-umap.get(state), ans);
            }

            /**
             * 解决场景问题：
             * 12321
             * 5612321
             */
            mask = 1;
            int cnt = 10;
            while (cnt-- > 0){
                int key = state ^ mask;
                if (umap.containsKey(key)){
                    ans = Math.max(i-umap.get(key),ans);
                }
                mask<<=1;
            }

            if (!umap.containsKey(state)){
                umap.put(state,i);
            }
        }
        return ans;
    }


    @Test
    public void test(){
        System.out.println(longestAwesome("12332"));
    }

}
