package com.lzq.study.lettcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liuzhengqiu on 2019/12/22.
 */
public class OneSixFiveCompetition {
    @Test
    public void test01(){
        int[][] moves = {{0,0},{2,0},{1,1},{2,1},{2,2}};
        Assert.assertTrue(tictactoe(moves).equals("A"));
        int[][] moves2 = {{0,0},{1,1},{0,1},{0,2},{1,0},{2,0}};
        Assert.assertTrue(tictactoe(moves2).equals("B"));
    }

    public String tictactoe(int[][] moves) {
        int[][] chessSheet = new int[3][3];
        int rows = moves.length;
        for (int i = 0; i < rows; i++){
            if (i%2==0){
                chessSheet[moves[i][0]][moves[i][1]] = 1;
            }else {
                chessSheet[moves[i][0]][moves[i][1]] = 2;
            }
        }
        for (int i = 0; i < 3; i++){
            int value = chessSheet[0][i];
            if (value==1&&isWin(value,0,i,chessSheet)){
                return "A";
            }
            if (value==2&&isWin(value,0,i,chessSheet)){
                return "B";
            }
        }
        if (chessSheet[1][0]!=0&&chessSheet[1][1]==chessSheet[1][0]&&chessSheet[1][2]==chessSheet[1][0]){
            if (chessSheet[1][0] == 1) return "A";
            else return "B";
        }
        if (chessSheet[2][0]!=0&&chessSheet[2][1]==chessSheet[2][0]&&chessSheet[2][2]==chessSheet[2][0]){
            if (chessSheet[2][0] == 1) return "A";
            else return "B";
        }
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (chessSheet[i][j]==0){
                    return "Pending";
                }
            }
        }
        return "Draw";
    }
    private boolean isWin(int value, int row, int column, int[][] chessSheet){
        boolean first = chessSheet[row][(column+1)%3] == value
                &&chessSheet[row][(column+2)%3] == value;
        if (first) return true;
        boolean second = chessSheet[(row+1)%3][column] == value
                && chessSheet[(row+2)%3][column] == value;
        if (second) return true;
        boolean third = (row==column)
                && chessSheet[(row+1)%3][(column+1)%3] == value
                && chessSheet[(row+2)%3][(column+2)%3] == value;
        if (third) return true;
        boolean four = (row==column+2||row==column-2||row==column)
                &&(chessSheet[0][2]==value)
                &&(chessSheet[1][1]==value)
                &&(chessSheet[2][0]==value);
        if (four) return true;
        return false;
    }

}
