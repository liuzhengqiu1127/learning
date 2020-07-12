package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Thirty {

    public String reformatDate(String date) {
        String[] dateArr = date.split(" ");
        String year = dateArr[2];
        String[] monthArr = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        List<String> monthList = Arrays.asList(monthArr);
        int index = monthList.indexOf(dateArr[1]) + 1;
        String month = index < 10 ? ("0"+index) : String.valueOf(index);
        String day = (dateArr[0].charAt(1)+"").matches("[0-9]")?(dateArr[0].substring(0,2)):("0"+dateArr[0].charAt(0));
        return year + "-" + month + "-" + day;
    }

    public int rangeSum(int[] nums, int n, int left, int right) {
        List<Integer> newList = new ArrayList<>();
        initNewArr(nums,0, n, newList);
        Collections.sort(newList);
        int abs = 1000000007;
        long sum = 0;
        for (int index=left-1; index < right; index++){
            sum += newList.get(index);
        }
        return (int) (sum % abs);
    }
    private void initNewArr(int[] nums, int startIndex, int length, List<Integer> newList){
        if (startIndex == length) return;
        int sum = 0;
        for (int i=startIndex; i < length; i++){
            sum += nums[i];
            newList.add(sum);
        }
        initNewArr(nums,startIndex+1, length, newList);
    }

    /**
     * 3一定要看一个整体来做这套题
     * @param nums
     * @return
     */
    public int minDifference(int[] nums) {
        if (nums.length < 5) return 0;
        Arrays.sort(nums);
        int len = nums.length;
        int first = nums[len-4] - nums[0];
        int second = nums[len-1] - nums[3];
        int third = nums[len-2] - nums[2];
        int four = nums[len-3] - nums[1];
        return Integer.min(Integer.min(Integer.min(first,second),third),four);
    }

    /**
     * dp表示个数为n时，=0表示输了（对方走的，接下来你走），=1表示赢了（你走的，接下里对方走）
     * list记录可以取的个数 num
     * num<=总个数i,并且i-num时为对方走的
     * @param n
     * @return
     */
    public boolean winnerSquareGame(int n){
        List<Integer> list = new ArrayList<>();
        for (int i=1; i * i <= n; i++) list.add(i*i);//可以取的个数
        int[] dp = new int[n + 1];
        for (int i=1; i<=n; i++){
            for (int num : list){
                if (num <= i && dp[i-num] == 0){
                    dp[i] = 1;
                }
                if (num > i) break;
            }
        }
        return dp[n] == 1;
    }


    @Test
    public void test(){
        System.out.println(winnerSquareGame(17));
    }

}
