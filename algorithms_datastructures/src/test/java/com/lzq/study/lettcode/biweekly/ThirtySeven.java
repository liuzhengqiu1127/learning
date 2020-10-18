package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by liuzhengqiu on 2020/10/17.
 */
public class ThirtySeven {

    public double trimMean(int[] arr) {
        int length = arr.length;
        int number = length * 5 / 100;
        if (number == 0) {
            return Arrays.stream(arr).sum()*1.0/length;
        }
        Arrays.sort(arr);
        for (int i=0; i < number; i++){
            arr[i] = 0;
            arr[length-1-i] = 0;
        }
        return Arrays.stream(arr).sum()*1.0/(length-2*number);
    }

    public int[] bestCoordinate(int[][] towers, int radius) {
        int[] result = new int[2];
        int rows = towers.length;
        int max = 0;
        for (int i = 0; i < rows; i++){
            int value = towers[i][2];
            for (int j=0; j<rows; j++){
                if (j == i) continue;
                int distance = (towers[i][0] - towers[j][0])*(towers[i][0] - towers[j][0])
                        + (towers[i][1] - towers[j][1])*(towers[i][1] - towers[j][1]);
                if (radius >= Math.sqrt(distance)){
                    value += (int)((towers[j][2]/(1+Math.sqrt(distance))));
                }
            }
            if (value > max){
                max = value;
                result[0] = towers[i][0];
                result[1] = towers[i][1];
            }
            if (value == max){
                if (towers[i][0] < result[0] || (towers[i][0] == result[0] && towers[i][1] < result[1])){
                    result[0] = towers[i][0];
                    result[1] = towers[i][1];
                }
            }
        }
        return result;
    }

    /**
     记 f[i][j] 表示使用 0 .. i 的点构造了 j 条线段的方案数。我们需要区分第 jj 条线段的右端点是否就是 i，因此可以考虑把 f[i][j] 拆分成两个状态：
     f[i][j][0] 表示第 j 条线段的右端点不是 i，也就是说我们没有办法继续延长第 j 条线段；
     f[i][j][1] 表示第 j 条线段的右端点就是 i，也就是说我们可以选择是否继续延长第 j 条线段。
     如何进行状态转移呢？
     首先考虑 f[i][j][0]，因为第 j 条线段的右端点不是 i，因此第 i 个点没有用上，那么 0 .. i-1 的点构造了 j 条线段，即
     f[i][j][0] = f[i-1][j][0] + f[i-1][j][1]
     再考虑 f[i][j][1]，因为第 j 条线段的右端点就是 i，因此有两种情况：
     第 j 条线段长度为 1，那么 0 .. i-1 的点构造了 j-1 条线段，即
     f[i][j][1] = f[i-1][j-1][0] + f[i-1][j-1][1]
     第 j 条线段长度大于 1，那么删去第 j 条线段 i-1 .. i 的这一部分，0 .. i-1 的点仍然构造了 j 条线段，并且点 i-1 是属于第 j 条线段的，即
     f[i][j][1] = f[i-1][j][1]
     加上边界条件 f[0][0][0]=1，最终答案即为 f[n−1][k][0]+f[n−1][k][1]。
     */
    private static final int mod = 1000000007;
    public int numberOfSets(int n, int k) {
        int[][][] f = new int[n][k+1][2];
        f[0][0][0] = 1;
        for (int i=1; i<n; i++){
            for (int j=0; j <=k; j++){
                f[i][j][0] = (f[i-1][j][0] + f[i-1][j][1]) % mod;
                f[i][j][1] = f[i-1][j][1];
                if (j > 0){
                    f[i][j][1] += f[i-1][j-1][0];
                    f[i][j][1] %= mod;
                    f[i][j][1] += f[i-1][j-1][1];
                    f[i][j][1] %= mod;
                }
            }
        }
        return (f[n-1][k][0] + f[n-1][k][1]) % mod;
    }


    @Test
    public void test(){
        int[][] towers = new int[][]{{1,2,5},{2,1,7},{3,1,9}};
        System.out.println(bestCoordinate(towers,2));
    }
}
