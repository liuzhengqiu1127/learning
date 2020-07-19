package com.lzq.study.lettcode.weekly.onenine;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OneNineEight {

    public int numWaterBottles(int numBottles, int numExchange) {
        int sum = numBottles;
        while (numBottles >= numExchange){
            sum += (numBottles/numExchange);
            numBottles = (numBottles/numExchange) + (numBottles%numExchange);
        }
        return sum;
    }

    int[] res;
    boolean[] visited;
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        /**
         * 用一个二维数组破解无向图结构
         */
        List<Integer>[] points = new List[n];
        for (int i=0; i < n; i++){
            points[i] = new ArrayList<>();
        }
        for (int[] p : edges){
            points[p[0]].add(p[1]);
            points[p[1]].add(p[0]);
        }

        /**
         * 记录0-n节点对应的字母值
         */
        int[] ls = new int[n];
        for (int i=0; i < n; i++){
            ls[i] = labels.charAt(i) - 'a';
        }

        res = new int[n];
        visited = new boolean[n];
        visited[0] = true;

        dfs(0, points, ls);
        return res;
    }
    private int[] dfs(int i, List<Integer>[] points, int[] ls){
        int[] curLs = new int[26];
        curLs[ls[i]]++;
        for (int child : points[i]){ // 1
            /**
             * 记录是否已经访问
             */
            if (visited[child]){
                continue;
            }
            visited[child] = true;

            int[] childLs = dfs(child, points, ls); // 2 这里 1 + 2是DFS的关键
            for (int k=0; k<26; k++){
                curLs[k] += childLs[k];//记录每个值
            }

        }

        res[i] = curLs[ls[i]];
        return curLs;
    }

    @Test
    public void test(){
        int[] result = countSubTrees(4, new int[][]{{0,2},{0,3},{1,2}},"aeed");
        for (int re : result) System.out.println(re);
    }

}
