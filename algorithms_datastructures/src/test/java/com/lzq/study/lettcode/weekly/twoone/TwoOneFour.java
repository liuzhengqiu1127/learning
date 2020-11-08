package com.lzq.study.lettcode.weekly.twoone;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by liuzhengqiu on 2020/11/8.
 */
public class TwoOneFour {

    public int getMaximumGenerated(int n) {
        if (n == 0 || n == 1) return n;
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) nums[i] = nums[i / 2];
            else {
                nums[i] = nums[(i - 1) / 2] + nums[(i - 1) / 2 + 1];
            }
        }
        return Arrays.stream(nums).max().getAsInt();
    }

    public int minDeletions(String s) {
        int[] result = new int[26];
        for (char ch : s.toCharArray()) {
            result[ch - 'a']++;
        }
        Arrays.sort(result);
        int delete = 0;
        for (int i = 0; i < 26; i++) {

            if (result[i] == 0) continue;

            while (true){

                if (result[i] == 0) break;
                boolean flag = false;

                for (int j = 0; j < 26; j++){

                    if (result[i] == 0) break;

                    if (j != i && result[i] == result[j]){
                        result[i]--;
                        delete++;
                        flag = true;
                    }
                }

                if (!flag) break;

            }

        }
        return delete;
    }

    private static long abs =  1000000007;
    public int maxProfit(int[] inventory, int orders) {
        long sum = Arrays.stream(inventory).sum();
        long rest = sum - orders;
        int length = inventory.length;
        long everyRest = rest / length; // 平均剩余球
        long count = 0; // 还几个数组 多余1球
        if (rest % length != 0){
            count = rest - everyRest*length;
        }
        long maxProfit = 0;
        Arrays.sort(inventory);
        for (int invent : inventory){
            if (count == 0 || invent < everyRest+1 ){
                maxProfit += getSum(invent,everyRest);
            }else {
                maxProfit += getSum(invent, everyRest+1);
                count--;
            }
        }
        return (int) (maxProfit % abs);
    }
    private long getSum(long invent,long rest){
        if (invent <= rest) return 0;
        long begin = rest + 1;
        return ((begin+invent)*(invent-begin+1)/2) % abs;
    }

    @Test
    public void test(){
        System.out.println(maxProfit(new int[]{497978859,167261111,483575207,591815159},836556809));
    }
}
