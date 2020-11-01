package com.lzq.study.lettcode.weekly.twoone;

import java.util.*;

/**
 * Created by liuzhengqiu on 2020/10/25.
 */
public class TwoOneTwo {

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int length = releaseTimes.length;
        int maxTime = releaseTimes[0];
        char result = keysPressed.charAt(0);
        for (int i = 1; i < length; i++){
            int nextTime = releaseTimes[i] - releaseTimes[i-1];
            if (nextTime > maxTime){
                maxTime = nextTime;
                result = keysPressed.charAt(i);
            }
            if (nextTime == maxTime && keysPressed.charAt(i) > result){
                result = keysPressed.charAt(i);
            }
        }
        return result;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<>();
        int len = l.length;
        for (int i=0; i < len; i++){
            int start = l[i];
            int end = r[i];
            int[] temp = new int[end-start+1];
            int index = 0;
            for (int j = start; j <= end; j++){
                temp[index] = nums[j];
                index++;
            }
            Arrays.sort(temp);
            int juli = temp[1] - temp[0];
            boolean flag = true;
            for (int j = 2; j < temp.length; j++){
                if (temp[j] - temp[j-1] != juli){
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }

        return result;
    }

    /**
     * 竟然使用的是2分法不停逼近的思路
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        this.n = heights.length;
        this.m = heights[0].length;
        int l = 0, r = 1000000;
        while (l < r){
            int mid = (l + r) >> 1;
            if (check(mid, heights)) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    private int[][] d = new int[110][110];
    int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
    private int n, m;
    private boolean check(int mid, int[][] heights){
        for (int i = 0; i < 110; i++){
            for (int j = 0; j < 110; j++){
                d[i][j] = -1;
            }
        }
        d[0][0] = 1;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        while (!q.isEmpty()){
            int[] t = q.poll();
            if (t[0] == n-1 && t[1] == m-1) return true;
            for (int i = 0; i < 4; i++){
                int x = t[0] + dx[i], y = t[1] + dy[i];
                if (x < 0 || x >= n || y < 0 || y >= m) continue;
                if (d[x][y] != -1) continue;
                if (Math.abs(heights[x][y] - heights[t[0]][t[1]]) <= mid){
                    d[x][y] = 1;
                    q.add(new int[]{x,y});
                }
            }
        }

        return false;
    }
}
