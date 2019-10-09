package com.lzq.study.geektime.algorithms.famous;

/**
 * 回溯算法
 */
public class EightQueen {

    int[] result = new int[8];// 全局或成员变量, 下标表示行, 值表示 queen 存储在哪一列

    private static int minDist = Integer.MAX_VALUE;

    private static int[][] data = new int[][]{{1,3,5,9},{2,1,3,4},{5,2,6,7},{6,8,4,3}};

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.cal8queens(0);
        eightQueen.minDisByBT(0,0,data,4,0);
        System.out.println(minDist);
    }

    public void minDisByBT(int i, int j, int[][] w, int n, int distance){
        distance += w[i][j];
        if (i == n - 1 && j == n - 1) {
            if (distance < minDist) minDist = distance;
            return;
        }
        if (i < n - 1) {
            minDisByBT(i + 1, j, w, n, distance);
        }
        if (j < n - 1) {
            minDisByBT(i, j + 1, w, n, distance);
        }
    }

    public void cal8queens(int row) { // 调用方式：cal8queens(0);
        if (row == 8) { // 8 个棋子都放置好了，打印结果
            printQueens(result);
            return; // 8 行棋子都放好了，已经没法再往下递归了，所以就 return
        }
        for (int column = 0; column < 8; ++column) { // 每一行都有 8 中放法
            if (isOk(row, column)) { // 有些放法不满足要求
                result[row] = column; // 第 row 行的棋子放到了 column 列
                cal8queens(row+1); // 考察下一行
            }
        }
    }

    private boolean isOk(int row, int column) {// 判断 row 行 column 列放置是否合适
        int leftup = column - 1, rightup = column + 1;
        for (int i = row-1; i >= 0; --i) { // 逐行往上考察每一行
            if (result[i] == column) return false; // 第 i 行的 column 列有棋子吗？
            if (leftup >= 0) { // 考察左上对角线：第 i 行 leftup 列有棋子吗？
                if (result[i] == leftup) return false;
            }
            if (rightup < 8) { // 考察右上对角线：第 i 行 rightup 列有棋子吗？
                if (result[i] == rightup) return false;
            }
            --leftup; ++rightup;
        }
        return true;
    }

    private void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n = 6;
    private int m = 6;
    private int minDist2 = Integer.MAX_VALUE; // 存储结果
    // 调用方式 lwstBT(0, 0, 0);
    public void lwstBT(int i, int j, int edist) {
        if (i == n || j == m) {
            if (i < n) edist += (n-i);
            if (j < m) edist += (m - j);
            if (edist < minDist2) minDist2 = edist;
            return;
        }
        if (a[i] == b[j]) { // 两个字符匹配
            lwstBT(i+1, j+1, edist);
        } else { // 两个字符不匹配
            lwstBT(i + 1, j, edist + 1); // 删除 a[i] 或者 b[j] 前添加一个字符
            lwstBT(i, j + 1, edist + 1); // 删除 b[j] 或者 a[i] 前添加一个字符
            lwstBT(i + 1, j + 1, edist + 1); // 将 a[i] 和 b[j] 替换为相同字符
        }
    }

}
