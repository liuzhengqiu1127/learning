package com.lzq.study.lettcode.weekly.twoone;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liuzhengqiu on 2020/10/11.
 */
public class TwoOneZero {

    public int maxDepth(String s) {
        if (s.isEmpty() || !s.contains("(")) return 0;
        int maxDepth = 0;
        int currentValidDepth = 0;
        for (char ch : s.toCharArray()){
            if (ch == '('){
                currentValidDepth++;
                maxDepth = Integer.max(maxDepth,currentValidDepth);
            }
            if (ch == ')'){
                currentValidDepth--;
            }
        }
        return maxDepth;
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] hasRoad = new boolean[n][n]; // 这个数组定义的很完美
        int[] degree = new int[n];
        for (int i = 0; i < roads.length; i++){
            int src = roads[i][0];
            int des = roads[i][1];
            hasRoad[src][des] = true;
            hasRoad[des][src] = true;
            degree[src]++;
            degree[des]++;
        }
        int max = 0;
        for (int i = 0; i < n; i++){
            for (int j = i+1; j < n; j++){
                int cur = degree[i] + degree[j];
                if (hasRoad[i][j]) cur--;
                max = Math.max(cur, max);
            }
        }
        return max;
    }

    public boolean checkPalindromeFormation(String a, String b) {
        return check(a,b)||check(b,a);
    }

    private boolean check(String a,String b){
        char[] stra = a.toCharArray();
        char[] strb = b.toCharArray();
        int left = 0;
        int length = stra.length;
        while (left<=length/2){
            if (stra[left]==strb[length-1-left])
                left++;
            else
                break;
        }
        //如果超过一半都是相等的，直接返回true
        if (left==length/2)
            return true;
        //否则判断中间未遍历的部分是否是回文
        if (isPa(a.substring(left,length-left)) || isPa(b.substring(left,length-left)))
            return true;
        return false;
    }

    //判断是否回文
    private boolean isPa(String string){
        char[] str  =string.toCharArray();
        for (int i=0;i<str.length/2;i++){
            if (str[i]!=str[str.length-1-i])
                return false;
        }
        return true;
    }

    @Test
    public void test(){
        Assert.assertTrue(checkPalindromeFormation("abdef","fecab"));
//        Assert.assertTrue(maxDepth("(1+(2*3)+((8)/4))+1")==3);
//        Assert.assertTrue(maximalNetworkRank(2,new int[][]{{1,0}})==1);
    }

}
