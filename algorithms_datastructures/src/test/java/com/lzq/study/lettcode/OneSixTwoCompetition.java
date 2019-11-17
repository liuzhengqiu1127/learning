package com.lzq.study.lettcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liuzhengqiu on 2019/11/17.
 */
public class OneSixTwoCompetition {

    @Test
    public void test01(){
        Assert.assertTrue(oddCells(2,3,new int[][]{{0,1},{1,1}})==6);
        Assert.assertTrue(oddCells(2,2,new int[][]{{1,1},{0,0}})==0);
    }

    public int oddCells(int n, int m, int[][] indices) {
        int[][] result = new int[n][m];
        for (int i=0; i < indices.length; i++)
        {
            int row = indices[i][0];
            int column = indices[i][1];
            for (int c = 0; c < m; c++){
                result[row][c] += 1;
            }
            for (int r = 0; r < n; r++){
                result[r][column] += 1;
            }
        }
        int oddSum = 0;
        for (int r = 0; r < n; r++){
            for (int c = 0; c < m; c++){
                if (result[r][c] %2 == 1){
                    oddSum++;
                }
            }
        }
        return oddSum;
    }

    @Test
    public void test02(){
        System.out.println(reconstructMatrix(2,1,new int[]{1,1,1}));
        System.out.println(reconstructMatrix(2,3,new int[]{2,2,1,1}));
        System.out.println(reconstructMatrix(5,5,new int[]{2,1,2,0,1,0,1,2,0,1}));
    }

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int length = colsum.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (int i = 0; i < length; i++){
            int number = colsum[i];
            if (number == 0) {
                first.add(0);
                second.add(0);
            }else if (number == 2){
                if (upper==0 || lower==0) return result;
                first.add(1);
                upper--;
                second.add(1);
                lower--;
            }else {
                if (upper==0 && lower==0) return result;
                else if (upper > 0 && upper >= lower){
                    first.add(1);
                    upper--;
                    second.add(0);
                }else {
                    first.add(0);
                    second.add(1);
                    lower--;
                }
            }
        }
        if (upper!=0 || lower!=0) return result;
        result.add(first);
        result.add(second);
        return result;
    }

    @Test
    public void test03(){
        int[][] grid = new int[][]{
                {1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1},
                {1,0,1,1,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,1,1,0,1},
                {1,0,0,0,0,0,1},
                {1,1,1,1,1,1,1}
        };
        Assert.assertTrue(closedIsland(grid)==2);
        grid = new int[][]{{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}};
        Assert.assertTrue(closedIsland(grid)==1);
    }


    public int closedIsland(int[][] grid) {
        Set<String> stringSet = new HashSet<>();
        int rowLen = grid.length;
        int columnLen = grid[0].length;
        for (int i=0;i<rowLen;i++){
            for (int j=0;j<columnLen;j++)
                if (grid[i][j] == 0) stringSet.add(i+""+j);
        }
        int sum = 0;
        for (int i=0;i<rowLen;i++){
            for (int j=0;j<columnLen;j++) {
                if (grid[i][j] == 0) {
                   boolean flag = !stringSet.contains(i-1 + "" +j) && !stringSet.contains(i+1 + "" +j)
                           && !stringSet.contains(i + "" + (j-1)) && !stringSet.contains(i + "" + (j+1));
                   if (flag) sum++;
                }
            }
        }
        return sum;
    }
}
