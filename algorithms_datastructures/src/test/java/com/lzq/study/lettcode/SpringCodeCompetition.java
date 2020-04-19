package com.lzq.study.lettcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
        int[] result = new int[requirements.length];
        for (int i=0; i<requirements.length;i++) result[i]=-1;
        for (int i=1; i<increase.length;i++){
            increase[i][0] += increase[i-1][0];
            increase[i][1] += increase[i-1][1];
            increase[i][2] += increase[i-1][2];
        }
        for (int i=0; i<requirements.length; i++){
            int left = 0, right = increase.length - 1, mid = 0;
            while (left < right){
                mid = (left + right) >> 1;
                if (isTrue(increase[mid],requirements[i])){
                    right = mid;
                }else{
                    left = mid + 1;
                }
            }
            if (isTrue(increase[left],requirements[i])){
                if(requirements[i][0] + requirements[i][1] + requirements[i][2] == 0) result[i] =  0;
                else result[i] = left + 1;
            }
        }
        return result;
    }
    private boolean isTrue(int[] increase, int[] requirement){
        return requirement[0] <= increase[0] && requirement[1] <= increase[1] && requirement[2] <= increase[2];
    }


    /**
     * 非常经典的动态规划题目
     * @param jump
     * @return
     */
    public int minJump(int[] jump) {
        int[] dp = new int[jump.length];
        for (int i=jump.length-1; i>=0; i--){
            if (i + jump[i] >= jump.length){
                dp[i] = 1;
            }else {
                dp[i] = dp[i + jump[i]] + 1;
            }
            for (int j=i+1; j<jump.length&&j<i+jump[i]&&dp[j]>dp[i]; j++){
                dp[j] = Integer.min(dp[j],dp[i]+1);
            }
        }
        return dp[0];
    }


    @Test
    public void test(){
        int[] prints = getTriggerTime(new int[][]{{1,1,1}},new int[][]{{0,0,0}});
        for (int print : prints) System.out.println(print);
    }
}
