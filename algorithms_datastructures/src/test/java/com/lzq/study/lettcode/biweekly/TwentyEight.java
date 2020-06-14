package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TwentyEight {

    public int[] finalPrices(int[] prices) {
        int len = prices.length;
        int[] result = new int[len];
        for (int i=0; i < len; i++){
            int price = prices[i];
            int reduce = 0;
            for (int j = i+1; j < len; j++){
                if (prices[j] <= price){
                    reduce = prices[j];
                    break;
                }
            }
            result[i] = price - reduce;
        }
        return result;
    }

    private int[][] rectangle;
    public void SubrectangleQueries(int[][] rectangle) {
        int rows = rectangle.length;
        int columns = rectangle[0].length;
        this.rectangle = new int[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                this.rectangle[i][j] = rectangle[i][j];
            }
        }
    }
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i=row1; i<=row2; i++){
            for (int j = col1; j<=col2; j++){
                this.rectangle[i][j] = newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        return this.rectangle[row][col];
    }

    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        List<int[]> list = getList(arr, target, n);
        if (list.size() < 2) return -1;
        int len = list.size();
        int[] dp = getDp(list, len);
        int[] ps = getPs(list, len);
        int min = getMin(list, len, dp, ps);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    private List<int[]> getList(int[] arr, int target, int n) {
        List<int[]> list = new ArrayList<>();//记录所有和为target的区间
        for (int l = 0, r = 0, sum = 0; r < n; r++){
            if (arr[r] == target){
                list.add(new int[]{r,r});
                l = r + 1;
                sum = 0;
            } else if (arr[r] > target){
                l = r + 1;
                sum = 0;
            }else {
                sum += arr[r];
                while (sum > target){
                    sum -= arr[l];
                    l++;
                }
                if (sum == target){
                    list.add(new int[]{l,r});
                }
            }
        }
        return list;
    }

    /**
     * 从后往前，dp[i]表示从第i个往后，最小的区间宽度是多少
     * @param list
     * @param len
     * @return
     */
    private int[] getDp(List<int[]> list, int len) {
        int[] dp = new int[len + 1];
        dp[len] = Integer.MAX_VALUE;
        for (int i = len - 1; i >= 0; i--){
            int[] p = list.get(i);
            dp[i] = Math.min(dp[i+1], p[1]-p[0]+1);
        }
        return dp;
    }
    /**
     * 记录所有区间的左端点
     * @param list
     * @param len
     * @return
     */
    private int[] getPs(List<int[]> list, int len) {
        int[] ps = new int[len];
        int pi = 0;
        for (int[] p : list) ps[pi++] = p[0];
        return ps;
    }
    private int getMin(List<int[]> list, int len, int[] dp, int[] ps) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len - 1; i++){
            int[] a = list.get(i);

            int l = i+1, r=len-1;
            while (l < r){
                int mid = l + (r - l) / 2;
                if (ps[mid] <= a[1]) l = mid + 1;
                else r = mid;
            }

            if (ps[l] > a[1]){
                min = Math.min(min, (a[1] - a[0] + 1) + dp[l]);
            }
        }
        return min;
    }

    @Test
    public void test(){
        int[] test = {3,1,1,1,5,1,2,1};
        System.out.println(minSumOfLengths(test,3));
    }



}
