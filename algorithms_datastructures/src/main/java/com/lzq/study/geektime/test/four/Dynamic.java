package com.lzq.study.geektime.test.four;

/**
 * 动态规划
 */
public class Dynamic {

    /**
     * 求最大推增序列
     * @param array
     * @return
     */
    public int longestIncreaseSubArrayDP(int[] array) {
        if (array.length < 2) return array.length;
        int[] state = new int[array.length];
        state[0] = 1;
        for (int i = 1; i < state.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    if (state[j] > max) max = state[j];
                }
            }
            state[i] = max + 1;
        }
        int result = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] > result) result = state[i];
        }
        return result;
    }

    /**
     * 回溯算法的变体
     */
    static class Recall {
        // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
        private int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
        private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
        private int n = 5; // 物品个数
        private int w = 9; // 背包承受的最大重量
        private boolean[][] mem = new boolean[5][10]; // 备忘录 默认值 false

        public void f(int i, int cw) { // 调用 f(0, 0)
            if (cw == w || i == n) { // cw==w 表示装满了，i==n 表示物品都考察完了
                if (cw > maxW) maxW = cw;
                return;
            }
            if (mem[i][cw]) return; // 重复状态
            mem[i][cw] = true; // 记录 (i,cw) 这个状态
            f(i + 1, cw); // 选择不装第 i 个物品
            if (cw + weight[i] <= w) {
                f(i + 1, cw + weight[i]); // 选择装第 i 个物品
            }
        }
    }

    /**
     * 0 和 1 背包问题
     */
    static class Knapsack {
        /**
         * @param weight 物品重量
         * @param n      物品个数
         * @param w      背包可承载重量
         * @return
         */
        public int pack(int[] weight, int n, int w) {
            boolean[][] states = new boolean[n][w + 1];
            states[0][0] = true;
            if (weight[0] <= w) states[0][weight[0]] = true;
            for (int i = 1; i < n; ++i) {
                for (int j = 0; j <= w; ++j) {
                    if (states[i - 1][j] == true) { // 第1个是基于第0个开始
                        states[i][j] = states[i - 1][j]; // 不放进背包
                    }
                }
                for (int j = 0; j <= w - weight[i]; ++j) {
                    if (states[i - 1][j]) states[i][j + weight[i]] = true; // 放进背包
                }
            }
            for (int i = w; i >= 0; --i) {
                if (states[n - 1][i] == true) return i;
            }
            return 0;
        }

        /**
         * @param weight 物品重量
         * @param n      物品个数
         * @param w      背包可承载重量
         * @param value  物品价值
         * @return
         */
        public int pack(int[] weight, int[] value, int n, int w) {
            int[][] states = new int[n][w + 1];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < w + 1; ++j) {
                    states[i][j] = -1;
                }
            }
            states[0][0] = 0;
            if (weight[0] <= w) {
                states[0][weight[0]] = value[0];
            }
            for (int i = 1; i < n; ++i) {
                for (int j = 0; j <= w; ++j) {
                    if (states[i - 1][j] >= 0) {
                        states[i][j] = states[i - 1][j];
                    }
                }
                for (int j = 0; j <= w - weight[i]; ++j) {
                    if (states[i - 1][j] >= 0) {
                        int v = states[i - 1][j] + value[i];
                        if (v > states[i][j + weight[i]]) {
                            states[i][j + weight[i]] = v;
                        }
                    }
                }
            }
            int maxValue = -1;
            for (int j = 0; j <= w; ++j) {
                if (states[n - 1][j] > maxValue) maxValue = states[n - 1][j];
            }
            return maxValue;
        }
    }

    /**
     * 最短路径问题
     */
    static class MinPath {

        /**
         * 1 2 3 4 5 6
         * 2 3 4 5 6 7
         * 3 4 5 6 7 8 状态转移表法
         * @param matrix
         * @return
         */
        public int minDistDP(int[][] matrix) {
            int[][] minStates = new int[matrix.length][matrix[0].length];
            int sum = 0;
            for (int i = 0; i < matrix.length; i++) {
                sum += matrix[i][0];
                minStates[i][0] = sum;
            }
            sum = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sum += matrix[0][j];
                minStates[0][j] = sum;
            }
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    int minData = Integer.min(minStates[i][j - 1], minStates[i - 1][j]);
                    minStates[i][j] = minData + matrix[i][j];
                }
            }
            return minStates[matrix.length - 1][matrix[0].length - 1];
        }

        private int[][] matrix =
                {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
        private int[][] mem = new int[4][4];

        /**
         * 状态转移方程
         * @param i
         * @param j
         * @return
         */
        public int minDist(int i, int j) { // 调用 minDist(n-1, n-1);
            if (i == 0 && j == 0) return matrix[0][0];
            if (mem[i][j] > 0) return mem[i][j];
            int minLeft = Integer.MAX_VALUE;
            if (j - 1 >= 0) {
                minLeft = minDist(i, j - 1);
            }
            int minUp = Integer.MAX_VALUE;
            if (i - 1 >= 0) {
                minUp = minDist(i - 1, j);
            }
            int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
            mem[i][j] = currMinDist;
            return currMinDist;
        }
    }

    /**
     * 莱文斯坦距离
     */
    static class LevenshteinDs{
        private String str1 = "mitcmu";
        private String str2 = "mtacnu";
        private int minDist = Integer.MAX_VALUE;

        /**
         * 回溯算法
         * @param i
         * @param j
         * @param edist
         */
        public void lwstBT(int i,int j,int edist){
            if (i == str1.length() || j == str2.length()){
                if (i < str1.length()) edist += str1.length() - i;
                if (j < str2.length()) edist += str2.length() - j;
                if (edist < minDist) minDist = edist;
                return;
            }
            if (str1.getBytes()[i] == str2.getBytes()[j]){
                lwstBT(i+1,j+1,edist);
            }else {
                lwstBT(i+1,j,edist+1);
                lwstBT(i,j+1,edist+1);
                lwstBT(i+1,j+1,edist+1);
            }
        }

        /**
         * 动态规划算法
         * @param a
         * @param n
         * @param b
         * @param m
         * @return
         */
        public int lwstDP(char[] a, int n, char[] b, int m) {
            int[][] minDist = new int[n][m];
            for (int j = 0; j < m; ++j) { // 初始化第 0 行:a[0..0] 与 b[0..j] 的编辑距离
                if (a[0] == b[j]) minDist[0][j] = j;
                else if (j != 0) minDist[0][j] = minDist[0][j-1]+1;
                else minDist[0][j] = 1;
            }
            for (int i = 0; i < n; ++i) { // 初始化第 0 列:a[0..i] 与 b[0..0] 的编辑距离
                if (a[i] == b[0]) minDist[i][0] = i;
                else if (i != 0) minDist[i][0] = minDist[i-1][0]+1;
                else minDist[i][0] = 1;
            }
            for (int i = 1; i < n; ++i) { // 按行填表
                for (int j = 1; j < m; ++j) {
                    if (a[i] == b[j]) minDist[i][j] = min(
                            minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]);
                    else minDist[i][j] = min(
                            minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]+1);
                }
            }
            return minDist[n-1][m-1];
        }

        private int min(int x, int y, int z) {
            int minv = Integer.MAX_VALUE;
            if (x < minv) minv = x;
            if (y < minv) minv = y;
            if (z < minv) minv = z;
            return minv;
        }

    }

    static class MaxCommonSub{
        public int lcs(char[] a, int n, char[] b, int m) {
            int[][] maxlcs = new int[n][m];
            for (int j = 0; j < m; ++j) {// 初始化第 0 行：a[0..0] 与 b[0..j] 的 maxlcs
                if (a[0] == b[j]) maxlcs[0][j] = 1;
                else if (j != 0) maxlcs[0][j] = maxlcs[0][j-1];
                else maxlcs[0][j] = 0;
            }
            for (int i = 0; i < n; ++i) {// 初始化第 0 列：a[0..i] 与 b[0..0] 的 maxlcs
                if (a[i] == b[0]) maxlcs[i][0] = 1;
                else if (i != 0) maxlcs[i][0] = maxlcs[i-1][0];
                else maxlcs[i][0] = 0;
            }
            for (int i = 1; i < n; ++i) { // 填表
                for (int j = 1; j < m; ++j) {
                    if (a[i] == b[j]) maxlcs[i][j] = max(
                            maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]+1);
                    else maxlcs[i][j] = max(
                            maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]);
                }
            }
            return maxlcs[n-1][m-1];
        }

        private int max(int x, int y, int z) {
            int maxv = Integer.MIN_VALUE;
            if (x > maxv) maxv = x;
            if (y > maxv) maxv = y;
            if (z > maxv) maxv = z;
            return maxv;
        }

    }


    public static void main(String[] args) {
        Recall recall = new Recall();
        recall.f(0, 0);
        System.out.println(recall.maxW);

        int[][] matrix = new int[][]{{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}, {4, 3, 6, 2}};
        MinPath minPath = new MinPath();
        System.out.println(minPath.minDistDP(matrix));
        System.out.println(minPath.minDist(3,3));

        LevenshteinDs levenshteinDs = new LevenshteinDs();
        levenshteinDs.lwstBT(0,0,0);
        System.out.println(levenshteinDs.minDist);
        System.out.println(levenshteinDs.lwstDP("mitcmu".toCharArray(),"mitcmu".length(),"mtacnu".toCharArray(),"mtacnu".length()));
    }

}
