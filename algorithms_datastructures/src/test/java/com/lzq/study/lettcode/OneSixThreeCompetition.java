package com.lzq.study.lettcode;

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
}
