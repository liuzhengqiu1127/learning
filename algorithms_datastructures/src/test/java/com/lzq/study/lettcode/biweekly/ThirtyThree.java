package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThirtyThree {

    public String thousandSeparator(int n) {
        String string = String.valueOf(n);
        int sum = 1;
        StringBuilder result = new StringBuilder();
        for (int index=string.length()-1;index>=0;index--){
            result.append(string.charAt(index));
            if (sum % 3 == 0){
                result.append(".");
            }
            sum++;
        }
        result = result.reverse();
        if (result.charAt(0) == '.'){
            return result.substring(1);
        }
        return result.toString();
    }

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] temp = new int[n];
        for (int i=0; i<n; i++) temp[i] = -1;
        for (List<Integer> edge : edges){
            int begin = edge.get(0);
            int end = edge.get(1);
            if (temp[begin] != -2) {
                temp[begin] = end;
            }
            temp[end] = -2;
        }
        List<Integer> result = new ArrayList<>();
        for (int i=0; i<n; i++){
            if (temp[i]!=-1&&temp[i]!=-2) result.add(i);
        }
        return result;
    }

    public int minOperations(int[] nums) {
        int ans = 0;
        while (true){
            for (int i=0; i<nums.length; i++){
                if (nums[i]%2 != 0){
                    ans++;
                }
                nums[i] = (nums[i]>>1) ;
            }
            int zero = 0;
            for (int num : nums){
                if (num == 0) zero++;
            }
            if (zero >= nums.length) break;
            ans++;
        }
        return ans;
    }

    int[][] fx = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[][] isvisit = new int[505][505];
    boolean res = false;

    public boolean containsCycle(char[][] grid) {
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                //若没访问过则dfs搜索看是否有环
                if(isvisit[i][j] == 0)
                    //一开始父节点选(-1, -1)是因为起点没有父节点，不用担心会发生回退
                    dfs(i, j, -1, -1, grid);
                if (res) return true;
            }
        }
        return res;
    }
    //cx:当前点横坐标 cy:当前点纵坐标 px:父节点横坐标 py:父节点纵坐标
    void dfs(int cx, int cy, int px, int py, char[][] grid){
        //若搜索到访问过的节点，则存在环
        if(isvisit[cx][cy] == 1){
            res = true;
            return;
        }
        //记录当前节点被走过
        isvisit[cx][cy] = 1;
        for(int[] f : fx){
            int x = f[0] + cx;
            int y = f[1] + cy;

            //若搜索到父节点，即回退了，则跳过
            if(x == px && y == py){
                continue;
            }
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == grid[cx][cy]){
                dfs(x, y, cx, cy, grid);
            }
        }
    }


    @Test
    public void test(){
        System.out.println(minOperations(new int[]{1000000000}));
    }
}
