package com.lzq.study.lettcode.middle;

import java.util.ArrayList;
import java.util.List;

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

}
