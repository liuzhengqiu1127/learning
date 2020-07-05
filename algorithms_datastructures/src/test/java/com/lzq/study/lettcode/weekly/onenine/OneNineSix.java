package com.lzq.study.lettcode.weekly.onenine;

import org.junit.Test;

import java.util.Arrays;

public class OneNineSix {

    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        long value = arr[1]-arr[0];
        for (int i=1; i<arr.length; i++){
            if (arr[i] - arr[i-1] != value){
                return false;
            }
        }
        return true;
    }

    public int getLastMoment(int n, int[] left, int[] right) {
        Arrays.sort(left);
        Arrays.sort(right);
        if (left.length==0&&right.length==0) return 0;
        else if (left.length == 0) return n - right[0];
        else if (right.length == 0) return left[left.length-1];
        else return Integer.max(n-right[0],left[left.length-1]);
    }

    public int numSubmat(int[][] mat) {
        int rows = mat.length;
        int columns = mat[0].length;
        int sum = 0;
        for (int i=0; i < rows; i++){
            for (int j=0; j < columns; j++){
                if (mat[i][j] == 0) continue;
                sum += getNumber(mat, i, j);
            }
        }
        return sum;
    }
    private int getNumber(int[][] mat, int i, int j){
        int sum = 1;
        int row = i+1;
        for (;row<mat.length;row++){
            if (mat[row][j] == 1){
                sum+=1;
            }
            else break;
        }
        int column = j + 1;
        for (;column<mat[0].length;column++){
            if (mat[i][column] == 1) sum+=1;
            else break;
        }

        for (int k1=i+1;k1<row;k1++){
            for (int k2=j+1;k2<column;k2++){
                if (isOk(mat,i,k1,j,k2)) sum+=1;
                else break;
            }
        }
        return sum;
    }
    private boolean isOk(int[][] mat,int startRow, int endRow, int startColumn, int endColumn){
        for (int i=startRow;i<=endRow;i++){
            for (int j=startColumn;j<=endColumn;j++){
                if (mat[i][j] == 0) return false;
            }
        }
        return true;
    }


    @Test
    public void test(){
        System.out.println(numSubmat(new int[][]{{1,0,1},{1,1,0},{1,1,0}}));
        System.out.println(numSubmat(new int[][]{{0,1,1,0},{0,1,1,1},{1,1,1,0}}));
        System.out.println(numSubmat(new int[][]{{1,1,1,1,1,1}}));
        System.out.println(numSubmat(new int[][]{{1,0,1},{0,1,0},{1,0,1}}));
    }

}
