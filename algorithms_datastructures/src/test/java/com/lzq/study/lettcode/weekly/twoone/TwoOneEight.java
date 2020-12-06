package com.lzq.study.lettcode.weekly.twoone;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by liuzhengqiu on 2020/12/6.
 */
public class TwoOneEight {
    public String interpret(String command) {
        command = command.replaceAll("\\(al\\)", "al");
        command = command.replaceAll("\\(\\)", "o");
        return command;
    }

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        boolean[] temp = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {

            if (k < 2 * nums[i] || temp[i] == true) continue;
            temp[i] = true;

            int target = k - nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {

                    if (temp[mid] == false) {
                        count++;
                        temp[mid] = true;
                        break;
                    }

                    /**
                     * 相等时先搜索右边
                     */
                    int tmp = mid + 1;
                    boolean flag = false;
                    while (tmp < nums.length && nums[tmp] == target){
                        if (temp[tmp] == false){
                            temp[tmp] = true;
                            flag = true;
                            count++;
                            break;
                        }else {
                            tmp++;
                        }
                    }
                    if (flag) break;

                    /**
                     * 再搜索左边
                     */
                    tmp = mid - 1;
                    while (tmp > i && nums[tmp] == target){
                        if (temp[tmp] == false){
                            temp[tmp] = true;
                            count++;
                            break;
                        }else {
                            tmp--;
                        }
                    }
                    break;

                }
            }
        }
        return count;
    }

    public int concatenatedBinary(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(Integer.toBinaryString(i));
        }
        return (int) (binToOct(sb.toString()));
    }

    long binToOct(String bin){
        long total=0;
        char [] binCharArray=bin.toCharArray();
        for (int i = 0; i < binCharArray.length; i++) {
            total=total+binCharArray[i]-48;
            if(i!=binCharArray.length-1){
                total=(total<<1) % 1000000007; //在此处取余就好了
            }
        }
        return total;
    }

    @Test
    public void test() {
        System.out.println(concatenatedBinary(72387));
    }
}
