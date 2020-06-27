package com.lzq.study.lettcode.biweekly;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TwentyNine {

    public double average(int[] salary) {
        double sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int sal : salary){
            min = Integer.min(min, sal);
            max = Integer.max(max, sal);
            sum += sal;
        }
        return (sum - min - max)/(salary.length-2);
    }

    public int kthFactor(int n, int k) {
        int count = 0;
        for (int i=1; i<=n; i++){
            if (n % i == 0){
                count++;
            }
            if (count == k){
                return i;
            }

        }
        return -1;
    }

    public int longestSubarray(int[] nums) {

        int result = 0;

        int firstMax = 0;
        int secondMax = 0;

        boolean isRemove = false;

        for (int i=0; i<nums.length; i++){

            if (nums[i]==0&&firstMax==0) {
                isRemove = false;
                continue;
            }

            else if (nums[i]==0&&isRemove==true){
                result = Integer.max(result,firstMax);
                firstMax = secondMax;
                secondMax = 0;
            }

            else if (nums[i]==0&&isRemove==false){
                isRemove = true;
            }

            else if (nums[i]==1&&isRemove==false){
                firstMax++;
            }

            else if (nums[i]==1&&isRemove==true){
                firstMax++;
                secondMax++;
            }

        }

        result =  Integer.max(result,firstMax);
        if (firstMax == nums.length) result--;

        return result;
    }

    @Test
    public void test(){
        System.out.println(longestSubarray(new int[]{1,1,0,1}));
        System.out.println(longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
        System.out.println(longestSubarray(new int[]{1,1,1}));
        System.out.println(longestSubarray(new int[]{1,1,0,0,1,1,1,0,1}));
        System.out.println(longestSubarray(new int[]{0,0,0}));
    }

    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        if (dependencies.length == 0){
            return n%k==0 ? n/k : (n/k + 1);
        }

        List<Tree> root = new ArrayList<>();

        for (int i=0; i<dependencies.length; i++){
            int child = dependencies[i][0];
            int parent = dependencies[i][1];

        }

        return 0;

    }

    class Tree{
        int value;
        List<Tree> treeList;
        public Tree(int value){
            this.value = value;
        }
    }

}
