package com.lzq.study.lettcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合
 */
public class GenerateBracket {
    public List<String> generateParenthesis(int n){
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n],0,combinations);
        return combinations;
    }

    /**
     * 长度为 n 的序列就是 '(' 加上所有长度为 n-1 的序列，以及 ')' 加上所有长度为 n-1 的序列
     * @param current
     * @param pos
     * @param result
     */
    private void generateAll(char[] current,int pos,List<String> result){
        if (pos == current.length){
            if (valid(current)) result.add(new String(current));
        }else {
            current[pos] = '(';
            generateAll(current,pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }
    private boolean valid(char[] current){
        int balance = 0;
        for (char ch : current){
            if (ch == '(') balance ++;
            else balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    /**
     * 回溯算法，最主要的问题需要学会描述问题
     * @param ans
     * @param cur
     * @param open
     * @param close
     * @param max
     */
    private void backtrack(List<String> ans,String cur,int open,int close,int max){
        if (cur.length() == max * 2){
            ans.add(cur);
            return;
        }
        if (open < max) backtrack(ans,cur+"(",open+1,close,max);
        if (close < open) backtrack(ans, cur + ")", open, close + 1, max);
    }

    public static void main(String[] args) {
        GenerateBracket bracket = new GenerateBracket();
        bracket.generateParenthesis(5).forEach(str -> System.out.println(str));
    }
}
