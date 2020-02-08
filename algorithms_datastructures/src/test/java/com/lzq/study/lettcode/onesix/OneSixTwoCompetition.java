package com.lzq.study.lettcode.onesix;

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
        int ret = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 0){
                    ret += dfs(grid,i,j);
                }
            }
        }
        return ret;
    }
    private int dfs(int[][] grid, int r, int c){
        if (r < 0 || r >= grid.length || c < 0 || c>= grid[0].length){
            return 0;
        }
        if (grid[r][c] == 1){
            return 1;
        }
        grid[r][c] = 1;
        int[] vr = {0,1,0,-1};
        int[] vc = {1,0,-1,0};
        int ret = 1;
        for (int i = 0; i < 4; i++){
            ret = Math.min(ret,dfs(grid,r+vr[i],c+vc[i]));
        }
        return ret;
    }

    @Test
    public void test04(){
        String[] words = {"dog","cat","dad","good"};
        char[] letters = {'a','a','c','d','d','d','g','o','o'};
        int[] score = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};
        Assert.assertTrue(maxScoreWords(words,letters,score)==23);
    }
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] l = new int[26];
        for (char c : letters){
            l[c - 'a']++;//记录已有字符出现的次数
        }
        int ans = 0;
        // 1 << words.length 表示words有多少选择的子集
        for (int i = 0; i < (1 << words.length); i++){
            int[] g = group(words,i); // 记录每一个子集中字符出现的次数
            int temp = 0;
            for (int j = 0; j< 26; j++){
                if (l[j] < g[j]){ //某个字符出现的次数，已经大于已有字符的次数
                    temp = 0;
                    break;
                }else {
                    temp += g[j] * score[j]; // 记录字符次数*分数 = 总得分
                }
            }
            ans = Integer.max(ans,temp);
        }
        return ans;
    }

    /**
     * 此次选中了哪几个词
     * @param words
     * @param bit
     * @return
     */
    private int[] group(String[] words, int bit){
        int[] g = new int[26];
        for (int i = 0; i < words.length; i++){
            if ( (bit & (1 << i)) != 0 ){
                for (char c : words[i].toCharArray())
                    g[c - 'a']++;//记录每个字符出现的次数
            }
        }
        return g;
    }

}
