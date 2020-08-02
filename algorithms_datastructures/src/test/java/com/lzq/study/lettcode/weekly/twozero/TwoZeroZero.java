package com.lzq.study.lettcode.weekly.twozero;

import org.junit.Test;

import java.util.Arrays;

public class TwoZeroZero {

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int sum = 0;
        for (int i=0; i < arr.length; i++){
            for (int j=i+1; j < arr.length; j++){
                if (Math.abs(arr[i]-arr[j]) > a){
                    continue;
                }
                for (int k=j+1; k < arr.length; k++){
                    if (Math.abs(arr[j]-arr[k])<=b && Math.abs(arr[i]-arr[k])<=c){
                        sum++;
                    }
                }
            }
        }
        return sum;
    }

    public int getWinner(int[] arr, int k) {
        int len = arr.length;
        if (len <= k){
            return Arrays.stream(arr).max().getAsInt();
        }
        int compare = 0;
        int biggerIndex = 0;
        for (int i=1;i<len;i++){
            if (arr[biggerIndex] - arr[i] > 0){
                compare++;
            }else{
                biggerIndex = i;
                compare = 1;
            }
            if (compare >= k){
                break;
            }
        }
        return arr[biggerIndex];
    }

    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] record = new int[n]; // 记录每行从右到左0的个数
        for (int row=0; row < n; row++){
            int sum = 0;
            for (int column=n-1;column>=0;column--){
                if (grid[row][column]==0) sum++;
                else break;
            }
            record[row] = sum;
        }

        int change = 0;
        for (int row=0; row<n-1; row++){
            boolean isEnd = true;
            int endRow = row;//需要交换的行
            for (int i=row;i<n;i++){
                if (record[i]>=(n-1-row)){
                    isEnd = false;
                    endRow = i;
                    break;
                }
            }
            if (isEnd){
                return -1;
            }

            int endRowNumber = record[endRow];
            for (int i=endRow;i>=(row+1);i--){
                record[i] = record[i-1];
            }
            record[row] = endRowNumber;

            change += (endRow-row);
        }

        return change;

    }

    /**
     * 两个指针，在交叉点进行选择就ok了
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxSum(int[] nums1, int[] nums2) {
        long sum1 = 0, sum2 = 0;
        long res = 0;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] == nums2[j]){
                res += (Long.max(sum1, sum2) + nums1[i]);
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            }
            else if (nums1[i] < nums2[j]){
                sum1 += nums1[i];
                i++;
            }
            else {
                sum2 += nums2[j];
                j++;
            }
        }
        while (i < nums1.length){
            sum1 += nums1[i];
            i++;
        }
        while (j < nums2.length){
            sum2 += nums2[j];
            j++;
        }
        res += Long.max(sum1, sum2);
        return (int)(res % ((int)Math.pow(10,9) + 7));

    }

    @Test
    public void test01(){
        System.out.println(minSwaps(new int[][]{{0,0,1},{1,1,0},{1,0,0}}));
        System.out.println(minSwaps(new int[][]{{1,0,0},{1,1,0},{1,1,1}}));
        System.out.println(minSwaps(new int[][]{{0,1,1,0},{0,1,1,0},{0,1,1,0},{0,1,1,0}}));
    }

}
