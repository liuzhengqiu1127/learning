package com.lzq.study.lettcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liuzhengqiu on 2019/9/29.
 */
public class Solution2 {
    int[] nums;
    int target;

    private int find_rotate_index(int left, int right){
        if (nums[left] < nums[right]) return 0;
        while (left <= right){
            int pivot = (left + right) >> 1;
            if (nums[pivot] > nums[pivot + 1]) return pivot + 1;
            else{
                if (nums[pivot] < nums[left])   right = pivot - 1;
                else left = pivot + 1;
            }
        }
        return 0;
    }
    private int search(int left, int right){
        while (left <= right){
            int pivot = (left + right) >> 1;
            if (nums[pivot] == target) return pivot;
            else {
                if (target < nums[pivot]) right = pivot - 1;
                else left = pivot + 1;
            }
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        this.nums = nums;
        this.target = target;

        int n = nums.length;

        if (n == 0)
            return -1;
        if (n == 1)
            return this.nums[0] == target ? 0 : -1;

        int rotate_index = find_rotate_index(0, n - 1);

        // if target is the smallest element
        if (nums[rotate_index] == target)
            return rotate_index;
        // if array is not rotated, search in the entire array
        if (rotate_index == 0)
            return search(0, n - 1);
        if (target < nums[0])
            // search in the right side
            return search(rotate_index, n - 1);
        // search in the left side
        return search(0, rotate_index);
    }

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int[] result = new int[]{-1,-1};
        if (n == 0) return result;
        if (n == 1 && target == nums[0]) {
            result = new int[]{0,0};
            return result;
        }
        int left = 0;
        int right = n - 1;
        while (left <= right){
            int pivot = (left + right) >> 1;
            if (nums[pivot] < target) left = pivot + 1;
            else if (nums[pivot] > target) right = pivot - 1;
            else {
                int l_pivot = pivot;
                while (l_pivot >= 0 && nums[l_pivot] == target){
                    l_pivot--;
                }
                result[0] = l_pivot  + 1;
                int r_pivot = pivot;
                while (r_pivot <= n-1 && nums[r_pivot] == target){
                    r_pivot++;
                }
                result[1] = r_pivot - 1;
                break;
            }
        }
        return result;
    }
    public boolean isValidSudoku(char[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (!judgeEvery(board[i][j],board,i,j)) return false;
            }
        }
        return true;
    }
    private boolean judgeEvery(char ch, char[][] board,int row,int column) {
        if (ch == '.') return true;
        for (int i = 0; i < board.length; i++) {
            if (row != i && board[i][column] == ch) {
                return false;
            }
        }
        for (int j = 0; j < board[0].length; j++) {
            if (column != j && board[row][j] == ch) {
                return false;
            }
        }
        int start_row = (row / 3) * 3;
        int end_row = start_row + 2;
        int start_column = (column / 3) * 3;
        int end_column = start_column + 2;
        for (int i = start_row; i <= end_row; i++) {
            for (int j = start_column; j <= end_column; j++) {
                if (i != row && j != column && board[i][j] == ch) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        System.out.println(new Solution2().searchRange(nums,target));
    }
}
