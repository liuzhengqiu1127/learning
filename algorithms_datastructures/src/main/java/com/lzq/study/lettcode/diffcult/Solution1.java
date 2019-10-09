package com.lzq.study.lettcode.diffcult;

import java.util.*;

/**
 * Created by liuzhengqiu on 2019/9/29.
 */
public class Solution1 {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        for (int i = 0; i < s.length();i++){
            for (int j = i + 2; j <= s.length(); j+= 2){
                if (isValid(s.substring(i,j))) maxLen = Math.max(maxLen,j-i);
            }
        }
        return maxLen;
    }
    public int longestValidParentheses2(String s){
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                stack.push(i);
            }else {
                stack.pop();
                if (stack.empty()){
                    stack.push(i);
                }else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
    private boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(') stack.push('(');
            else if (!stack.isEmpty()&&stack.peek()=='('){
                stack.pop();
            }else {
                return false;
            }
        }
        return stack.empty();
    }
    private static Map<String,List<Character>> resultMaps = new HashMap<>();
    private static int[] number = {1,2,3,4,5,6,7,8,9};
    private static boolean[][] flag = new boolean[9][9];
    public void solveSudoku(char[][] board) {
        remark(board);
        int all_row = board.length;
        int all_column = board[0].length;
        for (int i = 0; i < all_row; i++){
            int time = 0;
            boolean tag = false;
            for (int j = 0; j < all_column; j++){
                if (flag[i][j] && !tag) continue;
                if (flag[i][j] && tag){
                    if (j > 0) j -= 2;
                    else {
                        i--;
                        j = 7;
                    }
                    continue;
                }
                board[i][j] = generateNumber(time);
                if (board[i][j] == 0){
                    if (i != 0 || j != 0) resultMaps.remove(""+i+j);
                    if (j > 0) j -= 2;
                    else {
                        i--;
                        j = 7;
                    }
                    tag = true;
                    time = 0;
                    continue;
                }
                if (isCorrect(i,j,board)){
                    String key = "" + i + j;
                    if (!resultMaps.containsKey(key)){
                        List<Character> list = new ArrayList<>();
                        list.add(board[i][j]);
                        resultMaps.put(key,list);
                    }else {
                        resultMaps.get(key).add(board[i][j]);
                    }
                    time = 0;
                }else{
                    time++;
                    j--;
                }
                tag = false;
            }
        }
    }
    private boolean isCorrect(int row, int col, char[][] board){
        String key = "" + row + col;
        if (resultMaps.containsKey(key)&&resultMaps.get(key).contains(board[row][col]))
            return false;
        for (int i = 0; i < board.length; i++){
            if (row != i && board[i][col] == board[row][col]){
                return false;
            }
        }
        for (int j = 0; j < board[0].length; j++){
            if (col != j && board[row][j] == board[row][col]){
                return false;
            }
        }
        int start_row = (row/3) * 3;
        int end_row = start_row + 2;
        int start_column = (col/3) * 3;
        int end_column = start_column + 2;
        for (int i = start_row; i <= end_row; i++){
            for (int j = start_column; j <= end_column; j++){
                if (i != row && j != col && board[i][j] == board[row][col]) return false;
            }
        }
        return true;
    }

    private void remark(char[][] board){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.'){
                    flag[i][j] = true;
                }
            }
        }
    }
    private static Random random = new Random();
    private char generateNumber(int time){
        if (time == 0) {
            for (int i = 0; i < 9; i++) {
                number[i] = i + 1;
            }
        }
        if (time == 9) return 0;
        int randNumber = random.nextInt(9 - time);
        int temp = number[8 - time];
        number[8 - time] = number[randNumber];
        number[randNumber] = temp;
        return String.valueOf(number[8 - time]).charAt(0);
    }
    public static void main(String[] args) {
        char[][] init = new char[][]{{'.','.','.','2','.','.','.','6','3'},{'3','.','.','.','.','5','4','.','1'},{'.','.','1','.','.','3','9','8','.'},
                {'.','.','.','.','.','.','.','9','.'},{'.','.','.','5','3','8','.','.','.'},{'.','3','.','.','.','.','.','.','.'},
                {'.','2','6','3','.','.','5','.','.'},{'5','.','3','7','.','.','.','.','8'},{'4','7','.','.','.','1','.','.','.'}};
        Solution1 solution1 = new Solution1();
        solution1.solveSudoku(init);
        System.out.println(init);
    }
}
