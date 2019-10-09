package com.lzq.study.geektime.algorithms.famous;

/**
 * 动态规划算法
 */
public class DyProgramme {

    private int[][] matrix = {{1,3,5,9}, {2,1,3,4},{5,2,6,7},{6,8,4,3}};
    private int[][] mem = new int[4][4];

    public static void main(String[] args) {
        int[][] data = new int[][]{{1,3,5,9},{2,1,3,4},{5,2,6,7},{6,8,4,3}};
        DyProgramme dyProgramme = new DyProgramme();
        System.out.println(dyProgramme.minDistDP(data,4,4));
        System.out.println(dyProgramme.getMinValue(3,3));
        System.out.println(dyProgramme.minCoins(197));
        int[] array = new int[]{2,9,3,6,5,1,7};
        System.out.println(dyProgramme.longestIncreaseSubArrayDP(array));
    }

    /**
     * 求数组的最大有序长度
     * @param array
     * @return
     */
    public int longestIncreaseSubArrayDP(int[] array){
        if (array.length < 2) return array.length;
        int[] state = new int[array.length];//state记录每个位置最大有序长度
        state[0] = 1;//第0个位置最大长度为1
        for (int i = 1; i < state.length; i++){
            int max = 0;
            for (int j = 0; j < i; j++){
                if (array[j] < array[i]){
                    if (state[j] > max) max = state[j];
                }
            }
            state[i] = max + 1;//[1,n-1]位置的最大长度
        }
        int result = 0;
        for (int i = 0;i<state.length;i++){
            if (state[i]>result) result = state[i];
        }
        return result;
    }

    /**
     * 求1，3，5元的硬币，组成money元钱，最少的硬币个数
     * @param money
     * @return
     */
    public int minCoins(int money) {
        if (money == 1 || money == 3 || money == 5) return 1;
        boolean [][] state = new boolean[money][money + 1];
        if (money >= 1) state[0][1] = true;
        if (money >= 3) state[0][3] = true;
        if (money >= 5) state[0][5] = true;
        for (int i = 1; i < money; i++) {
            for (int j = 1; j <= money; j++) {
                if (state[i - 1][j]) {
                    if (j + 1 <= money) state[i][j + 1] = true;
                    if (j + 3 <= money) state[i][j + 3] = true;
                    if (j + 5 <= money) state[i][j + 5] = true;
                    if (state[i][money]) return i + 1;
                }
            }
        }
        return money;
    }

    /**
     * 最短路径问题，状态转移方法
     * @param i
     * @param j
     * @return
     */
    public int getMinValue(int i,int j){
        if (i==0&&j==0){
            return matrix[0][0];
        }
        if (mem[i][j] > 0) return mem[i][j];
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0){
            minLeft = getMinValue(i,j-1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0){
            minUp = getMinValue(i-1,j);
        }

        int currMinDist = matrix[i][j] + Math.min(minLeft,minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

    /**
     * 求二维空间最短路径问题
     * 状态表
     * @param matrix
     * @param n
     * @param m
     * @return
     */
    public int minDistDP(int[][] matrix, int n, int m)
    {
        int[][] states = new int[n][m];
        int sum = 0;
        for (int i=0;i<n;i++)
        {
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        int columnSum = 0;
        for (int i=0;i<m;i++){
            columnSum += matrix[0][i];
            states[0][i] = columnSum;
        }
        for (int i=1;i<n;i++){
            for (int j=1;j<m;j++){
                states[i][j] = matrix[i][j]+Integer.min(states[i][j-1],states[i-1][j]);
            }
        }
        return states[n-1][m-1];
    }


    //weight: 物品重量,n: 物品个数,w: 背包可承载重量
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w+1]; // 默认值 false
        states[0][0] = true;  // 第一行的数据要特殊处理,可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第 i 个物品放入背包
                if (states[i-1][j] == true) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; ++j) {// 把第 i 个物品放入背包
                if (states[i-1][j]==true) states[i][j+weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n-1][i] == true) return i;
        }
        return 0;
    }

    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1]; // 默认值 false
        states[0] = true;  // 第一行的数据要特殊处理,可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w-items[i]; j >= 0; --j) {// 把第 i 个物品放入背包
                if ( states[j]==true ) states[j+items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if ( states[i] == true ) return i;
        }
        return 0;
    }

    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w+1];
        for (int i = 0; i < n; ++i) { // 初始化 states
            for (int j = 0; j < w+1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { // 动态规划,状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第 i 个物品
                if (states[i-1][j] >= 0) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; ++j) { // 选择第 i 个物品
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+weight[i]]) {
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n-1][j] > maxvalue) maxvalue = states[n-1][j];
        }
        return maxvalue;
    }

    // items 商品价格,n 商品个数, w 表示满减条件,比如 200
    public static void double11advance(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][3*w+1];// 超过 3 倍就没有薅羊毛的价值了
        states[0][0] = true;  // 第一行的数据要特殊处理
        if (items[0] <= 3*w) {
            states[0][items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = 0; j <= 3*w; ++j) {// 不购买第 i 个商品
                if (states[i-1][j] == true) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= 3*w-items[i]; ++j) {// 购买第 i 个商品
                if (states[i-1][j]==true) states[i][j+items[i]] = true;
            }
        }

        int j;
        for (j = w; j < 3*w+1; ++j) {
            if (states[n-1][j] == true) break; // 输出结果大于等于 w 的最小值
        }
        if (j == 3*w+1) return; // 没有可行解
        for (int i = n-1; i >= 1; --i) { // i 表示二维数组中的行,j 表示列
            if(j-items[i] >= 0 && states[i-1][j-items[i]] == true) {
                System.out.print(items[i] + " "); // 购买这个商品
                j = j - items[i];
            } // else 没有购买这个商品,j 不变。
        }
        if (j != 0) System.out.print(items[0]);
    }

    /**
     * 求莱文斯坦距离
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

    /**
     * 求最大公共后缀
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
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
