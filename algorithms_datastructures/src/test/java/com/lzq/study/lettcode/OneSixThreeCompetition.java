package com.lzq.study.lettcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengqiu on 2019/12/1.
 */
public class OneSixThreeCompetition {

    @Test
    public void test01(){
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(shiftGrid(grid,1));
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int row = grid.length;
        int column = grid[0].length;
        for (int i = 0; i < k; i++){
            process(grid);
        }
        for (int i = 0; i < row; i++){
            List<Integer> every = new ArrayList<>();
            for (int j = 0; j < column; j++){
                every.add(grid[i][j]);
            }
            result.add(every);
        }
        return result;
    }
    private void process(int[][] grid){
        int[][] every = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < (grid[0].length-1); j++){
                every[i][j+1] = grid[i][j];
            }
        }
        every[0][0] = grid[grid.length-1][grid[0].length-1];
        for (int i = 1; i < grid.length; i++){
            every[i][0] = grid[i-1][grid[0].length-1];
        }
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = every[i][j];
            }
        }
    }

    @Test
    public void test02(){
        int[] nums = {3,6,5,1,8};
        Assert.assertTrue(maxSumDivThree(nums)==18);
    }

    public int maxSumDivThree(int[] nums) {
        int dp[] = {0,0,0};//动态规划的一种算法
        for (int i = 0; i < nums.length; ++i){
            int mod = nums[i] % 3; //取模：0，1，2
            int a = dp[(3 + 0 - mod)%3];
            int b = dp[(3 + 1 - mod)%3];
            int c = dp[(3 + 2 - mod)%3];

            if (a != 0 || mod == 0) dp[0] = Integer.max(dp[0],a + nums[i]);//表示选取的数字类加和 % 3 = 0 的数字和
            if (b != 0 || mod == 1) dp[1] = Integer.max(dp[1],b + nums[i]); //表示选取的数字类加和 % 3 = 1 的数字和
            if (c != 0 || mod == 2) dp[2] = Integer.max(dp[2],c + nums[i]); //表示选取的数字类加和 % 3 = 2 的数字和
        }
        return dp[0];
    }
}
