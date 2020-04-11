package com.lzq.study.lettcode.weekly.oneeight;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OneEightOne {
    @Test
    public void test01(){

    }
    public int[] createTargetArray(int[] nums, int[] index) {
        int length = nums.length;
        List<Integer> result = new LinkedList<>();
        for (int i=0; i<length; i++){
            result.add(index[i],nums[i]);
        }
        int[] target = new int[length];
        int i = 0;
        for (Integer integer : result) {
            target[i] = integer;
            i++;
        }
        return target;
    }

    @Test
    public void test02(){
        Assert.assertTrue(sumFourDivisors(new int[]{21,4,7})==32);
    }
    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for (int num : nums){
            sum += getSumOfBen(num);
        }
        return sum;
    }
    public int getSumOfBen(int number){
        List<Integer> result = new ArrayList<>();
        boolean flag = false;
        for (int i=2; i<=Math.sqrt(number); i++){
            if (number % i == 0){
                if (i == number/i || flag){
                    return 0;
                }
                result.add(i);
                result.add(number/i);
                flag = true;
            }
        }
        if (!flag) return 0;
        result.add(1);
        result.add(number);
        return result.stream().mapToInt(Integer::intValue).sum();
    }

    @Test
    public void test03(){
        Assert.assertTrue(hasValidPath(new int[][]{{2,4,3},{6,5,2}}));
    }
    int[][] dires = {{0,1,1,3,5},{1,0,2,5,6},{0,-1,1,4,6},{-1,0,2,3,4}};
    int[][] cset = {{},{0,2},{1,3},{1,2},{0,1},{2,3},{0,3}};
    public boolean hasValidPath(int[][] grid) {
        return dfs(grid,new boolean[grid.length][grid[0].length],0,0);
    }
    private boolean dfs(int[][] grid, boolean[][] visited, int x, int y){
        int m=grid.length, n=grid[0].length;
        if (x==m-1 && y==n-1) return true;
        visited[x][y] = true;
        int[] rdires = cset[grid[x][y]];
        for (int rd : rdires){
            int[] d = dires[rd];
            int x1 = x + d[0], y1 = y + d[1];
            if (x1 >= m || x1 < 0 || y1 >= n || y1 < 0 || visited[x1][y1]) continue;
            if (grid[x1][y1]==d[2] || grid[x1][y1]==d[3]|| grid[x1][y1]==d[4]){
                if (dfs(grid,visited,x1,y1)) return true;
            }
        }
        return false;
    }
    private boolean bfs(int[][] grid) {
        int m = grid.length, n=grid[0].length;
        boolean[][] reach = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,1});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            if (cur[0]==m-1&&cur[1]==n-1) return true;
            int[] rdires = cset[grid[cur[0]][cur[1]]];
            for (int rd : rdires){
                int[] d = dires[rd];
                int x = cur[0]+d[0], y=cur[1]+d[1];
                if (x>=m || x <0 || y>=n || y<0 || reach[x][y]) continue;
                if (grid[x][y]==d[2] || grid[x][y]==d[3] || grid[x][y]==d[4]){
                    queue.add(new int[]{x,y,1});
                    reach[x][y] = true;
                }
            }
        }
        return false;
    }


    @Test
    public void test04(){

    }
}
