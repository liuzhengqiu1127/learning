package com.lzq.study.lettcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OneSevenThreeCompetition {

    @Test
    public void test01() {
        Assert.assertTrue(removePalindromeSub("abb") == 2);
        Assert.assertTrue(removePalindromeSub("ababa") == 1);
        Assert.assertTrue(removePalindromeSub("baabb") == 2);
        Assert.assertTrue(removePalindromeSub("") == 0);
    }

    public int removePalindromeSub(String s) {
        if (s.isEmpty()) return 0;
        if (isPalindrome(s)) return 1;
        return 2;
    }

    private boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            stringBuilder.append(s.charAt(i));
        }
        return s.equals(stringBuilder.toString());
    }

    @Test
    public void test02() {
        int[][] restaurants = {{1, 4, 1, 40, 10}, {2, 8, 0, 50, 5}, {3, 8, 1, 30, 4}, {4, 10, 0, 10, 3}, {5, 1, 1, 15, 1}};
        System.out.println(filterRestaurants(restaurants, 1, 50, 10));
    }

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        int rows = restaurants.length;
        List<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            int id = restaurants[i][0];
            int rating = restaurants[i][1];
            int veganFriend = restaurants[i][2];
            int price = restaurants[i][3];
            int distance = restaurants[i][4];
            if (price <= maxPrice && distance <= maxDistance) {
                Integer[] every = new Integer[2];
                if (veganFriendly == 1 && veganFriend == 0) {
                    continue;
                }
                every[0] = id;
                every[1] = rating;
                result.add(every);
            }
        }
        Collections.sort(result, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if (o1[1].compareTo(o2[1]) == 0) {
                    return o2[0].compareTo(o1[0]);
                } else {
                    return o2[1].compareTo(o1[1]);
                }
            }
        });
        return result.stream().map(integers -> integers[0]).collect(Collectors.toList());
    }

    @Test
    public void test03() {
        Assert.assertTrue(findTheCity(4, new int[][]{{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}}, 4) == 3);
        Assert.assertTrue(findTheCity(5, new int[][]{{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}}, 2) == 0);
    }

    final int INF = 100000000;
    int[][] graph;

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = INF;
            }
        }
        for (int i = 0; i < n; i++) {
            graph[i][i] = 0;
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph[from][to] = weight;
            graph[to][from] = weight;
        }
        floyd(n);//得到每个点与点之间的最小距离

        int min = INF;
        int minIndex = -1;
        for (int i = graph.length - 1; i >= 0; i--) {//从大到小遍历
            int count = 0;
            for (int j : graph[i]) {
                if (j <= distanceThreshold) { // 每个点满足小于等于distanceThreshold的个数
                    count++;
                }
            }
            if (count < min) {
                min = count;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void floyd(int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] != INF && graph[k][j] != INF && graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    @Test
    public void test04() {
        Assert.assertTrue(minDifficulty(new int[]{6,5,4,3,2,1},2)==7);
    }

    int hard[][];
    public int minDifficulty(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) return -1;
        hard = new int[300][300];
        int dp[][] = new int[300][11];
        int i, j;
        for (i=1; i <= d; i++){
            for (j=0; j < jobDifficulty.length; j++){
                if (i == 1){ // 1天完成
                    if (j == 0) dp[0][1] = jobDifficulty[0];
                    else dp[j][1] = Integer.max(jobDifficulty[j],dp[j-1][1]);
                }else{
                    int k;
                    for (k = i - 2; k < j; k++){
                        if (k == i - 2) dp[j][i] = dp[k][i-1] + maxHard(k+1,j,jobDifficulty);//初始化值 dp[j][i]; i-1天至少要完成 i-1项工作所以 index = i - 2
                        else dp[j][i] = Integer.min(dp[j][i],dp[k][i-1]+maxHard(k+1,j,jobDifficulty));
                    }
                }
            }
        }
        return dp[jobDifficulty.length-1][d];
    }
    private int maxHard(int i, int j, int[] jobDifficulty)
    {
        if (hard[i][j]!=0) return hard[i][j];
        int k, maxN = 0;
        for (k = i; k <= j; k++){
            maxN = Integer.max(maxN,jobDifficulty[k]);
        }
        hard[i][j] = maxN;
        return maxN;
    }

}
