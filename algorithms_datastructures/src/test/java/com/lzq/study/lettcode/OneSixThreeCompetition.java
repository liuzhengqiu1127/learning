package com.lzq.study.lettcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by liuzhengqiu on 2019/12/1.
 */
public class OneSixThreeCompetition {

    @Test
    public void test01(){
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(shiftGrid(grid,1));
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int row = grid.length;
        int column = grid[0].length;
        for (int i = 0; i < k; i++){
            process(grid);
        }
        for (int i = 0; i < row; i++){
            List<Integer> every = new ArrayList<>();
            for (int j = 0; j < column; j++){
                every.add(grid[i][j]);
            }
            result.add(every);
        }
        return result;
    }
    private void process(int[][] grid){
        int[][] every = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < (grid[0].length-1); j++){
                every[i][j+1] = grid[i][j];
            }
        }
        every[0][0] = grid[grid.length-1][grid[0].length-1];
        for (int i = 1; i < grid.length; i++){
            every[i][0] = grid[i-1][grid[0].length-1];
        }
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = every[i][j];
            }
        }
    }

    @Test
    public void test02(){
        int[] nums = {3,6,5,1,8};
        Assert.assertTrue(maxSumDivThree(nums)==18);
    }

    public int maxSumDivThree(int[] nums) {
        int dp[] = {0,0,0};//动态规划的一种算法
        for (int i = 0; i < nums.length; ++i){
            int mod = nums[i] % 3; //取模：0，1，2
            int a = dp[(3 + 0 - mod)%3];
            int b = dp[(3 + 1 - mod)%3];
            int c = dp[(3 + 2 - mod)%3];

            if (a != 0 || mod == 0) dp[0] = Integer.max(dp[0],a + nums[i]);//表示选取的数字类加和 % 3 = 0 的数字和
            if (b != 0 || mod == 1) dp[1] = Integer.max(dp[1],b + nums[i]); //表示选取的数字类加和 % 3 = 1 的数字和
            if (c != 0 || mod == 2) dp[2] = Integer.max(dp[2],c + nums[i]); //表示选取的数字类加和 % 3 = 2 的数字和
        }
        return dp[0];
    }

    @Test
    public void test03(){
        char[][] grid = {
                {'#','#','#','#','#','#'},
                {'#','T','#','#','#','#'},
                {'#','.','.','B','.','#'},
                {'#','.','#','#','.','#'},
                {'#','.','.','.','S','#'},
                {'#','#','#','#','#','#'}
        };
        Assert.assertTrue(minPushBox(grid)==3);
        char[][] grid2 = {
                {'#','#','#','#','#','#'},
                {'#','T','.','.','#','#'},
                {'#','.','#','B','.','#'},
                {'#','.','.','.','.','#'},
                {'#','.','.','.','S','#'},
                {'#','#','#','#','#','#'}
        };
        Assert.assertTrue(minPushBox(grid2)==5);
    }

    // BFS找箱子到目标的最短距离，注意方向,箱子可以往回移动
    // 箱子在移动的时候，人一定要跟着箱子走，用来判断下一个人应该在的位置，从当前位置是否可达，可达则加入队列
    public int minPushBox(char[][] grid){
        if (grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        LinkedList<int[]> from = new LinkedList<>();
        LinkedList<int[]> to = new LinkedList<>();
        // 0-上， 1-左， 2-下， 3-右
        boolean[][][] visited = new boolean[grid.length][grid[0].length][4];
        int[] px = new int[]{-1, 0, 1, 0};
        int[] py = new int[]{0, -1, 0, 1};
        Map<Character, int[]> point = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'B') {
                    point.put('B', new int[]{i, j, 0, 0, 0});
                } else if (grid[i][j] == 'T') {
                    point.put('T', new int[]{i, j});
                } else if (grid[i][j] == 'S') {
                    point.put('S', new int[]{i, j});
                }
            }
        }
        point.get('B')[3] = point.get('S')[0];
        point.get('B')[4] = point.get('S')[1];
        int step = 0;
        from.add(point.get('B'));
        // 判断箱子到目标的最短距离，
        // 箱子移动的时候，人始终跟着箱子，在做BFS的时候一定要判断，前一步人所在的位置是否能移到当前人应该在的位置
        while (!from.isEmpty()) {
            int[] xy = from.pop();
            if (!visited[xy[0]][xy[1]][xy[2]]) {
                visited[xy[0]][xy[1]][xy[2]] = true;
                if (grid[xy[0]][xy[1]] == 'T') {
                    return step;
                } else {
                    for (int n = 0; n < 4; n++) {
                        if (0 <= xy[0] + px[n] && xy[0] + px[n] < grid.length) {
                            if (0 <= xy[1] + py[n] && xy[1] + py[n] < grid[0].length) {
                                if (grid[xy[0] + px[n]][xy[1] + py[n]] != '#') {
                                    if(0 <= xy[0] - px[n] && xy[0] - px[n] < grid.length) {
                                        if(0 <= xy[1] - py[n] && xy[1] - py[n] < grid[0].length) {
                                            // 这个target 代表的是如果箱子在下一位置，人应该在的位置相对箱子的当前位置
                                            int[] target = new int[]{xy[0] - px[n], xy[1] - py[n]};
                                            //to.add(new int[]{xy[0] + px[n], xy[1] + py[n], n, xy[0], xy[1]});
                                            if (bfs(grid, new int[]{xy[3], xy[4]}, target, xy) != -1) {
                                                to.add(new int[]{xy[0] + px[n], xy[1] + py[n], n, xy[0], xy[1]});
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (from.isEmpty()) {
                step++;
                from = new LinkedList<>(to);
                to.clear();
            }
        }
        return -1;
    }
    public int bfs(char[][] grid, int[] src, int[] dst, int[] box){
        if (grid[dst[0]][dst[1]] == '#' || grid[dst[0]][dst[1]] == 'T'){
            return -1;
        }
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[] px = new int[]{-1,0,1,0};
        int[] py = new int[]{0,-1,0,1};
        int step = 0;
        queue.add(src);
        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] xy = queue.pop();
                if (!visited[xy[0]][xy[1]]) {
                    visited[xy[0]][xy[1]] = true;
                    if (xy[0] == dst[0] && xy[1] == dst[1]) {
                        return step;
                    } else {
                        for (int n = 0; n < 4; n++) {
                            if (0 <= xy[0] + px[n] && xy[0] + px[n] < grid.length) {
                                if (0 <= xy[1] + py[n] && xy[1] + py[n] < grid[0].length) {
                                    if (grid[xy[0] + px[n]][xy[1] + py[n]] != '#' && (xy[0] + px[n] != box[0] || xy[1] + py[n] != box[1])) {
                                        queue.add(new int[]{xy[0] + px[n], xy[1] + py[n]});
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
