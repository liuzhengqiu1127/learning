package com.lzq.study.lettcode.weekly.oneseven;

import org.junit.Test;

/**
 * Created by liuzhengqiu on 2020/1/12.
 */
public class OneSevenOneCompetition {

    public int[] getNoZeroIntegers(int n) {
        for(int i=1; i<n;i++)
        {
            String a = String.valueOf(i);
            String b = String.valueOf(n-i);
            if (a.contains("0")||b.contains("0")){
                continue;
            }
            return new int[]{i,n-i};
        }
        return new int[]{};
    }

    @Test
    public void test01()
    {
        System.out.println(minFlips(8,3,5));
    }

    public int minFlips(int a, int b, int c) {
        int count = 0;
        String aStr = Integer.toBinaryString(a);
        String bStr = Integer.toBinaryString(b);
        String cStr = Integer.toBinaryString(c);
        int maxLen = Integer.max(Integer.max(aStr.length(),bStr.length()),cStr.length());
        int aT = maxLen-aStr.length();
        for (int i=0; i<aT;i++){
            aStr = "0" + aStr;
        }
        aT = maxLen-bStr.length();
        for (int i=0; i<aT;i++){
            bStr = "0" + bStr;
        }
        aT = maxLen-cStr.length();
        for (int i=0; i<aT;i++){
            cStr = "0" + cStr;
        }
        for (int i=0; i<maxLen; i++)
        {
            if (cStr.charAt(i)=='0'&&aStr.charAt(i)=='1'&&bStr.charAt(i)=='1')
            {
                count += 2;
            }else if (cStr.charAt(i)=='0'&&(aStr.charAt(i)=='1'||bStr.charAt(i)=='1'))
            {
                count += 1;
            }else if (cStr.charAt(i)=='0'&&aStr.charAt(i)=='0'&&bStr.charAt(i)=='0')
            {
                count += 0;
            }else if (cStr.charAt(i)=='1'&&(aStr.charAt(i)=='1'||bStr.charAt(i)=='1'))
            {
                count += 0;
            }else{
                count += 1;
            }
        }
        return count;
    }

    @Test
    public void test02(){
        System.out.println(makeConnected(6,new int[][]{{0,1},{0,2},{0,3},{1,2},{1,3}}));
    }

    public int makeConnected(int n, int[][] connections) {
        int rows = connections.length;
        if (rows < n - 1) return -1;
        int[] h = new int[n];
        int res = n;
        for (int i = 0; i < n; i++) h[i] = i;
        for (int i=0; i < rows; i++){
            int a = connections[i][0], b = connections[i][1];
            int af = find(h,a), bf = find(h,b);
            if (af != bf){
                h[af] = bf;
                res--;
            }
        }
        return res-1;
    }
    private int find(int[] h, int x){
        if (h[x] != x) h[x] = find(h,h[x]);
        return h[x];
    }


}
