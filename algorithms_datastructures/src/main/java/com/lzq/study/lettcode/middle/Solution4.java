package com.lzq.study.lettcode.middle;

import com.lzq.study.lettcode.common.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution4 {

    public int[][] generateMatrix(int n) {
        if (n == 0) return new int[0][0];
        int[][] res = new int[n][n];
        int number = 1;
        int row_start = 0;
        int row_end = n - 1;
        int column_start = 0;
        int column_end = n - 1;
        while (number <= n * n) {
            if (row_start == row_end || column_start == column_end) {
                res[row_start][column_start] = number;
                break;
            }
            for (int j = column_start; j < column_end; j++) {
                res[row_start][j] = number;
                number++;
            }
            for (int i = row_start; i < row_end; i++) {
                res[i][column_end] = number;
                number++;
            }
            for (int j = column_end; j > column_start; j--) {
                res[row_end][j] = number;
                number++;
            }
            for (int i = row_end; i > row_start; i--) {
                res[i][column_start] = number;
                number++;
            }
            row_start++;
            row_end--;
            column_start++;
            column_end--;
        }
        return res;
    }

    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
            used[i] = false;
        }
        List<String> pre = new ArrayList<>();
        return dfs(nums, used, n, k, 0, pre);
    }
    private int factorial(int n) {
        // 这种编码方式包括了 0 的阶乘是 1 这种情况
        int res = 1;
        while (n > 0) {
            res *= n;
            n -= 1;
        }
        return res;
    }
    private String dfs(int[] nums, boolean[] used, int n, int k, int depth, List<String> pre) {
        if (depth == n) {
            StringBuilder sb = new StringBuilder();
            for (String c : pre) {
                sb.append(c);
            }
            return sb.toString();
        }
        int ps = factorial(n - 1 - depth);
        for (int i = 0; i < n; i++) {
            if (used[i]) {
                continue;
            }
            if (ps < k) {
                k -= ps;
                continue;
            }
            pre.add(nums[i] + "");
            used[i] = true;
            return dfs(nums, used, n, k, depth + 1, pre);
        }
        // 如果参数正确的话，代码不会走到这里
        throw new RuntimeException("参数错误");
    }

    public ListNode rotateRight(ListNode head, int k) {
        int step = 0;
        if (head == null || head.next == null) return head;
        int size = 0;
        ListNode bHead = head;
        while (head != null){
            size++;
            head = head.next;
        }
        k = k % size;
        while (step < k){
            ListNode aHead = bHead;
            ListNode tail = bHead;
            while (tail.next.next != null){
                tail = tail.next;
            }
            bHead = tail.next;
            bHead.next = aHead;
            tail.next = null;
            step++;
        }
        return bHead;
    }

    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0)
                    res[0][j] = 1;
                else if (j == 0)
                    res[i][0] = 1;
                else
                    res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }
        return res[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int columns = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0;
        obstacleGrid[0][0] = 1;
        for (int i = 1; i < rows; i++){
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i-1][0] == 1) ? 1 : 0;
        }
        for (int j = 1; j < columns; j++){
            obstacleGrid[0][j] = (obstacleGrid[0][j] == 0 && obstacleGrid[0][j-1] == 1) ? 1 : 0;
        }
        for (int i = 1; i < rows; i++){
            for (int j = 1; j < columns; j++){
                if (obstacleGrid[i][j] == 0){
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                }else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[rows-1][columns-1];
    }
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] res = new int[rows][columns];
        res[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++){
            res[i][0] = res[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++){
            res[0][j] = res[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++){
            for (int j = 1; j < columns; j++){
                res[i][j] = Math.min(res[i-1][j],res[i][j-1]) + grid[i][j];
            }
        }
        return res[rows-1][columns-1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,2},{5,6},{1,1}};
        int result = new Solution4().minPathSum(grid);
        System.out.println(result);
    }

}
