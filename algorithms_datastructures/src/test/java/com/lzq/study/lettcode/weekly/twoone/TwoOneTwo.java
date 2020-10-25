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

    public int minimumEffortPath(int[][] heights) {
        int result = 0;
        int startRow = 0, startColumn = 0;
        int endRow = heights.length, endColumn = heights[0].length;

        return result;
    }
}
