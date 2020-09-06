package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

public class ThirtyFour {

    public int diagonalSum(int[][] mat) {
        int result = 0;
        for (int i=0; i<mat.length; i++){
            int row = (mat.length-1-i);
            if (row == i){
                result += mat[i][i];
            }else {
                result += (mat[i][i] + mat[row][i]);
            }
        }
        return result;
    }

    public int numWays(String s) {
        int res = 1000000007;
        int number = 0;
        for (char ch : s.toCharArray()){
            if (ch == '1') number++;
        }
        if (number % 3 != 0) return 0;
        if (number == 0){
            long result = (s.length()-1)*1L*(s.length()-2)/2;
            return (int) (result%res);
        }
        int everyCount = number/3;
        int startNumber = 1;
        int count = 0;
        for (int i=0; i<s.length(); i++){
            if (s.charAt(i)=='0'&&count<everyCount) continue;
            else if (s.charAt(i)=='0'&&count==everyCount){
                startNumber++;
            }
            else if (s.charAt(i)=='1'&&count<everyCount) count++;
            else {
                break;
            }
        }
        count = 0;
        int endNumber = 1;
        for (int i=s.length()-1; i>=0; i--){
            if (s.charAt(i)=='0'&&count<everyCount) continue;
            else if (s.charAt(i)=='0'&&count==everyCount){
                endNumber++;
            }
            else if (s.charAt(i)=='1'&&count<everyCount) count++;
            else {
                break;
            }
        }
        long result = startNumber*1L*endNumber;
        return (int) (result%res);
    }

    public int findLengthOfShortestSubarray(int[] arr) {
        int i = 0;
        for (;i<arr.length-1;i++){
            if (arr[i] > arr[i+1]) break;
        }
        int left = i;
        if (left == arr.length - 1) return 0;

        i = arr.length-1;
        for (; i > 0; i--){
            if (arr[i] < arr[i-1]) break;
        }
        int right = i;

        if (right - left == arr.length - 1){
            if (arr[right] >= arr[left]) return  arr.length - 2;
            else return arr.length - 1;
        }

        System.out.println("left="+left+",right="+right);
        if (arr[left] <= arr[right]) return right-left-1;

        int l = 0;
        int r = right;
        int cnt = 0;
        while (l < left+1 && r < arr.length){
            if (arr[l] > arr[r]){
                cnt++;
                l++;
                r++;
            }else {
                l++;
            }
        }

        return right-left-1 + cnt;

    }



    @Test
    public void test(){
        System.out.println(findLengthOfShortestSubarray(new int[]{1,2,3,10,4,2,3,5}));
    }
}
