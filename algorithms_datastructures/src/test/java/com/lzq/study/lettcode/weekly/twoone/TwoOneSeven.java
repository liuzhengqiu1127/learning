package com.lzq.study.lettcode.weekly.twoone;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by liuzhengqiu on 2020/12/5.
 */
public class TwoOneSeven {

    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int i = 0; i < accounts.length; i++) {
            max = Integer.max(max, Arrays.stream(accounts[i]).sum());
        }
        return max;
    }

    public int[] mostCompetitive(int[] nums, int k) {

        Stack<Integer> stack = new Stack<>();
        stack.add(-1);
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            //当前元素比队尾元素小，将队尾元素出栈
            //此处需要另外判断数组剩余长度够不够填满栈，不然最后答案长度可能会小于k
            while (nums[i] < stack.peek() && k - stack.size() + 1 < len - i) {
                stack.pop();
            }
            if (stack.size() < k + 1) {
                stack.add(nums[i]);
            }
        }

        int[] ret = new int[k];

        while (k > 0) {
            ret[--k] = stack.pop();
        }

        return ret;
    }


}
