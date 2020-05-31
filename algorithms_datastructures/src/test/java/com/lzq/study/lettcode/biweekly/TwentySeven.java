package com.lzq.study.lettcode.biweekly;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by liuzhengqiu on 2020/5/30.
 */
public class TwentySeven {

    @Test
    public void test1(){
        System.out.println("hello world");
    }
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        int len = target.length;
        for (int i=0; i < len; i++){
            if (target[i] != arr[i]) return false;
        }
        return true;
    }

    @Test
    public void test2(){
        Assert.assertTrue(hasAllCodes("00110110",2));
        Assert.assertTrue(hasAllCodes("00110",2));
        Assert.assertTrue(hasAllCodes("0110",1));
        Assert.assertTrue(!hasAllCodes("0110",2));
        Assert.assertTrue(!hasAllCodes("0000000001011100",4));
    }

    public boolean hasAllCodes(String s, int k) {
        int number = s.length() - k + 1;
        double count = Math.pow(2,k);
        if (number < count) return false;
        Set<String> stringSet = new HashSet<>();
        for (int i=0; i<number; i++){
            String sub = s.substring(i,i+k);
            stringSet.add(sub);
        }
        return stringSet.size() == count;
    }

    @Test
    public void test3(){
        System.out.println(checkIfPrerequisite(5,new int[][]{{0,1},{1,2},{2,3},{3,4}}, new int[][]{{0,4},{4,0},{1,3},{3,0}}));
    }
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] temp = new boolean[n][n];
        for (int[] prerequisite : prerequisites){
            temp[prerequisite[0]][prerequisite[1]] = true;
            for (int i=0; i < n; i++){
                if (temp[i][prerequisite[0]]) temp[i][prerequisite[1]] = true;
            }
        }
        List<Boolean> result = new ArrayList<>(queries.length);
        for (int[] querie : queries){
            result.add(temp[querie[0]][querie[1]]);
        }
        return result;
    }

    /**
     * 
     *
     *
     * @param grid
     * @return
     */
    public int cherryPickup(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][][] dp = new int[rows][cols][cols];
        int res = 0;

        for (int row=0; row<rows; row++){

            for (int head=0, headMax=Math.min(cols,row+1); head < headMax; head++){

                for (int rear=cols-1, rearMin=Math.max(0,cols-row-1); rear>=rearMin; rear--){

                    if (row == 0){

                        if (head == rear) dp[row][head][rear] += grid[row][head];
                        else dp[row][head][rear] += grid[row][head] + grid[row][rear];

                    }else{

                        int[] left = new int[]{head-1,head,head+1};
                        int[] right = new int[]{rear-1, rear, rear+1};

                        int headMaxLast = Math.min(cols, row);
                        int rearMinLast = Math.max(0, cols-row);

                        for (int x : left){
                            for (int y : right){
                                if (x >= 0 && x < headMaxLast && y >= rearMinLast && y <= cols-1){
                                    dp[row][head][rear] = Math.max(dp[row][head][rear], dp[row-1][x][y]);
                                }
                            }
                        }

                        if (head == rear) dp[row][head][rear] += grid[row][head];
                        else dp[row][head][rear] += grid[row][head] + grid[row][rear];

                    }

                }
            }

            if (row == rows - 1){
                for (int i = 0, imax = Math.min(cols,rows); i < imax; i++){
                    for (int j = cols-1, jmin = Math.max(0,cols-rows); j >= jmin; j--){
                        res = Math.max(res, dp[rows-1][i][j]);
                    }
                }
            }

        }

        return res;
    }


}
