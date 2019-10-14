package com.lzq.study.lettcode.diffcult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengqiu on 2019/10/14.
 */
public class EightQueen {

    private List<List<String>> data = null;
    public List<List<String>> solveNQueens(int n) {
        data = new ArrayList<>();
        calqueens(0,new int[n]);
        return data;
    }

    private int number = 0;
    public int totalNQueens(int n) {
        number = 0;
        calqueens(0,new int[n]);
        return number;
    }
    public void calqueens(int row, int[] result) { // 调用方式：cal8queens(0);
        if (row == result.length) { // 8 个棋子都放置好了，打印结果
            number++;
            return; // 8 行棋子都放好了，已经没法再往下递归了，所以就 return
        }
        for (int column = 0; column < result.length; ++column) { // 每一行都有 8 中放法
            if (isOk(row, column,result)) { // 有些放法不满足要求
                result[row] = column; // 第 row 行的棋子放到了 column 列
                calqueens(row+1,result); // 考察下一行
            }
        }
    }
    private boolean isOk(int row, int column, int[] result) {// 判断 row 行 column 列放置是否合适
        int leftup = column - 1, rightup = column + 1;
        for (int i = row-1; i >= 0; --i) { // 逐行往上考察每一行
            if (result[i] == column) return false; // 第 i 行的 column 列有棋子吗？
            if (leftup >= 0) { // 考察左上对角线：第 i 行 leftup 列有棋子吗？
                if (result[i] == leftup) return false;
            }
            if (rightup < result.length) { // 考察右上对角线：第 i 行 rightup 列有棋子吗？
                if (result[i] == rightup) return false;
            }
            --leftup; ++rightup;
        }
        return true;
    }
    private void printQueens(int[] result) { // 打印出一个二维矩阵
        List<String> res = new ArrayList<>();
        for (int row = 0; row < result.length; ++row) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int column = 0; column < result.length; ++column) {
                if (result[row] == column){
                    stringBuilder.append("Q");
                } else{
                    stringBuilder.append(".");
                }
            }
            res.add(stringBuilder.toString());
        }
        data.add(res);
    }

}
