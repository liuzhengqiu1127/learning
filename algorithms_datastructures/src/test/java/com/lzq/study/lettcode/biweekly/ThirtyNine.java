package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by liuzhengqiu on 2020/11/14.
 */
public class ThirtyNine {

    public int[] decrypt(int[] code, int k) {
        int[] result = new int[code.length];
        if (k == 0) return result;
        for (int i = 0; i < code.length; i++) {
            int count = 0;
            int sum = 0;
            int index = i;
            while (count < Math.abs(k)) {
                if ( k > 0) {
                    index = (index >= code.length-1) ? 0 : (index+1);
                }else{
                    index = (index <= 0) ? code.length-1 : (index - 1);
                }
                sum += code[index];
                count++;
            }
            result[i] = sum;
        }
        return result;
    }

    //数组dp[i][0] 表示以a结尾 dp[i][1]表示以b结尾 所需的最小操作数
    // aababbab
    // 如果要以a结尾则必须: 前面都是a, dp[i][0] = dp[i-1][0] + (要不要转换)
    // 如果要以b结尾则必须: 前面aaaab 或者 bbbb , dp[i][1] = dp[i-1][1] + 1 , Math.min(dp[i-1][0],dp[i-1][1])
    public int minimumDeletions(String s) {
        int len = s.length();
        int[][] dp = new int[len + 1][2]; //dp[0][0] dp[0][1] = 0
        for (int i = 1; i <= len; i++) {
            if (s.charAt(i-1) == 'a') {  // 当前位为a
                dp[i][0] = dp[i-1][0];   // a结尾,不需要操作
                dp[i][1] = dp[i-1][1] + 1;  // b结尾,a->b 操作数+1
            }else { // 当前位为b
                dp[i][0] = dp[i-1][0] + 1; // a结尾,b->a 操作数+1
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]); //b结尾, 取前一个以a/b结尾的最小操作数
            }
        }
        return Math.min(dp[len][0], dp[len][1]);
    }

    @Test
    public void test() {
        System.out.println(minimumDeletions("aababbab"));
    }
}
