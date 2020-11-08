package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.*;

public class ThirtyEight {

    public int[] frequencySort(int[] nums) {
        int[] result = new int[nums.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, (o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {
                return o2.getKey().compareTo(o1.getKey());
            }
            return o1.getValue().compareTo(o2.getValue());
        });

        int index = 0;
        for (Map.Entry<Integer, Integer> entry : entries) {
            int key = entry.getKey();
            int value = entry.getValue();
            int count = 0;
            while (count < value) {
                result[index] = key;
                count++;
                index++;
            }
        }
        return result;

    }

    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (p1, p2) -> {
            return p1[0] - p2[0];
        });
        int max = 0;
        int begin = points[0][0];
        for (int i = 1; i < points.length; i++) {
            max = Integer.max(points[i][0] - begin, max);
            begin = points[i][0];
        }
        return max;
    }

    public int countSubstrings(String s, String t) {
        int sum = 0;
        int length = Integer.min(s.length(),t.length());
        int index = 1;
        while (index <= length){
            for (int i = 0; i <= s.length() - index; i++){
                String sub = s.substring(i,i+index);
                for (int j = 0; j <= t.length() - index; j++){
                    String sub2 = t.substring(j,j+index);
                    if (check(sub,sub2)) sum++;
                }
            }
            index++;
        }
        return sum;
    }
    private boolean check(String str1, String str2){
        int num = 0;
        for (int index=0; index<str1.length(); index++){
            if (str1.charAt(index) == str2.charAt(index)) continue;
            else num++;
            if (num > 1) return false;
        }
        return num != 0;
    }

    int MOD = (int)1e9+7;
    int n,m,t;
    public int numWays(String[] words, String target) {
        n = words[0].length();
        m = words.length;
        t = target.length();

        int[][] f = new int[n][26]; //记录每个字符串[a-z]的个数
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int k = words[i].charAt(j) - 'a';
                f[j][k]++;
            }
        }

        int[][] dp = new int[t+1][n+1];
        Arrays.fill(dp[0],1);

        for(int i=1;i<=t;i++){
            int k = target.charAt(i-1) - 'a';

            for(int j=i;j<=n;j++){
                if(f[j-1][k]>0){
                    dp[i][j] = (int)(((long)dp[i-1][j-1] * f[j-1][k] + dp[i][j-1])%MOD);
                }else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[t][n];
    }


    @Test
    public void test() {
        System.out.println(countSubstrings("abe","bbc"));
    }

}
