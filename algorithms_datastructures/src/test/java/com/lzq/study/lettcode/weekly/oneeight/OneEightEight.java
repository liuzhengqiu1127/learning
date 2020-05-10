package com.lzq.study.lettcode.weekly.oneeight;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengqiu on 2020/5/10.
 */
public class OneEightEight {

    @Test
    public void test01(){
        System.out.println(buildArray(new int[]{1,3},3));
        System.out.println(buildArray(new int[]{1,2,3},3));
        System.out.println(buildArray(new int[]{1,2},4));
        System.out.println(buildArray(new int[]{2,3,4},4));
    }

    public List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        int j = 0;
        int len = target.length;
        for (int i=1; i<=n; i++){
            if (j == len){
                break;
            }
            if (target[j] == i){
                result.add("Push");
                j++;
            }else{
                result.add("Push");
                result.add("Pop");
            }
        }
        return result;
    }

    @Test
    public void test02() {
        Assert.assertTrue(countTriplets(new int[]{2, 3, 1, 6, 7}) == 4);
        Assert.assertTrue(countTriplets(new int[]{1, 1, 1, 1, 1}) == 10);
        Assert.assertTrue(countTriplets(new int[]{2, 3}) == 0);
        Assert.assertTrue(countTriplets(new int[]{1, 3, 5, 7, 9}) == 3);
        Assert.assertTrue(countTriplets(new int[]{7, 11, 12, 9, 5, 2, 7, 17, 22}) == 8);
        Assert.assertTrue(countTriplets2(new int[]{7, 11, 12, 9, 5, 2, 7, 17, 22}) == 8);
    }

    public int countTriplets(int[] arr) {
        if (arr.length<2) return 0;
        int len = arr.length;
        int result = 0;
        for (int i=0; i<len-1; i++){
            for (int j=i+1; j<len; j++){
                for (int k=j; k<len; k++){
                    if (check(i,j,k,arr)){
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private boolean check(int i, int j, int k, int[] arr){
        int a = arr[i], b = arr[j];
        for (int m=i+1; m<j; m++){
            a ^= arr[m];
        }
        for (int m=j+1; m<=k; m++){
            b ^= arr[m];
        }
        if (a==b) return true;
        return false;
    }

    /**
     * 给你一个整数数组 arr 。
     现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
     a 和 b 定义如下：
     a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     注意：^ 表示 按位异或 操作。
     请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     * @param arr
     * @return
     *
     * 解题:  如果a == b 则 a^ b == 0 => arr[i] ^ arr[i+1] ^ ... ^ arr[k] == 0
     */
    public int countTriplets2(int[] arr){
        if (arr.length < 2) return 0;
        int ans = 0;
        for (int i=0; i<arr.length-1; i++){
            int sum = 0;
            for (int k=i+1;k<arr.length;k++){
                for (int j=i; j<=k; j++){ // j 可能的取值就是 k - i
                    sum ^= arr[j];
                }
                if (sum == 0) ans += k - i;
                sum = 0;
            }
        }
        return ans;
    }

    @Test
    public void test03(){

    }

    int ans = 0;
    int[] reverseEdges;
    boolean[] visited;
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        reverseEdges = new int[n];
        for (int[] edge : edges){
            reverseEdges[edge[1]] = edge[0];
        }
        visited = new boolean[n];
        visited[0] = true;
        for (int i=0; i < n; i++){
            if (hasApple.get(i)){
                dfsEdge(i);
            }
        }
        return ans * 2;
    }
    private void dfsEdge(int to){
        if (!visited[to]){
            visited[to] = true;
            ans++;
            dfsEdge(reverseEdges[to]);
        }
    }

}
