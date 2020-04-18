package com.lzq.study.lettcode;

import com.lzq.study.geektime.algorithms.hash.Hash;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SpringCodeCompetition {

    public int minCount(int[] coins) {
        int sum = 0;
        for (int coin : coins){
            sum += getCount(coin);
        }
        return sum;
    }
    private int getCount(int coin){
        if (coin == 1) return 1;
        if (coin == 0) return 0;
        return getCount(coin-2) + 1;
    }

    Set<String> stringSet;
    public int numWays(int n, int[][] relation, int k) {
        stringSet = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(0);
        initWays(relation,k,0,stringBuilder);
        int sum  = 0;
        for (String string : stringSet){
            if (n-1 == Integer.valueOf(string.substring(string.length()-1,string.length()))){
                sum++;
            }
        }
        return sum;
    }
    private void initWays(int[][] relation, int k, int start, StringBuilder stringBuilder){
        if (k == 0){
            stringSet.add(stringBuilder.toString());
            return ;
        }
        int i=0;
        for (; i<relation.length; i++){
            if (start == relation[i][0]){
                StringBuilder temp = new StringBuilder();
                temp.append(stringBuilder);
                temp.append(relation[i][1]);
                initWays(relation,k-1,relation[i][1], temp);
            }
        }
    }


    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int length = requirements.length;
        List<Integer> records = new ArrayList<>();
        int[] triggerTime = new int[length];
        for (int i=0; i<length; i++) triggerTime[i] = -1;

        for (int i=0; i<length; i++){
            if (requirements[i][0]==0 && requirements[i][1]==0 && requirements[i][2]==0){
                records.add(i);
                triggerTime[i] = 0;
            }
        }

        int sumA = 0;
        int sumB = 0;
        int sumC = 0;
        int days = 0;
        for (int k=0; k<increase.length; k++){
            sumA += increase[k][0];
            sumB += increase[k][1];
            sumC += increase[k][2];
            days++;
            for (int i=0; i<length; i++){
                if (records.contains(i)) continue;
                if (sumA >= requirements[i][0] && sumB >= requirements[i][1] && sumC >= requirements[i][2]){
                    triggerTime[i] = days;
                    records.add(i);
                }
            }
        }
        return triggerTime;
    }


    @Test
    public void test(){
        int[] prints = getTriggerTime(new int[][]{{1,1,1}},new int[][]{{0,0,0}});
        for (int print : prints) System.out.println(print);
    }
}
