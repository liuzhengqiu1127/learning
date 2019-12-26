package com.lzq.study.lettcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by liuzhengqiu on 2019/12/26.
 */
public class OneSixSixCompetition {

    @Test
    public void test01(){
        Assert.assertTrue(subtractProductAndSum(234)==15);
        Assert.assertTrue(subtractProductAndSum(4421)==21);
    }

    public int subtractProductAndSum(int n) {
        List<Integer> numbers = new ArrayList<>();
        while (n >= 10){
            numbers.add(n%10);
            n = n/10;
        }
        numbers.add(n);
        int sum =  numbers.stream().mapToInt(Integer::intValue).sum();
        int multiplication = 1;
        for (Integer number : numbers){
            multiplication *= number.intValue();
        }
        return multiplication - sum;
    }

    @Test
    public void test002(){
        int[] groupSizes = {3,3,3,3,3,1,3};
        System.out.println(groupThePeople(groupSizes));
    }

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++){
            if (map.containsKey(groupSizes[i])){
                map.get(groupSizes[i]).add(i);
            }else {
                List<Integer> integers = new ArrayList<>();
                integers.add(i);
                map.put(groupSizes[i], integers);
            }
        }
        for(Map.Entry<Integer,List<Integer>> entry : map.entrySet()){
            int key = entry.getKey();
            List<Integer> values = entry.getValue();
            List<Integer> everyValue = new ArrayList<>();
            for (int i=1; i <= values.size(); i++){
                everyValue.add(values.get(i-1));
                if (i%key==0){
                    result.add(everyValue);
                    everyValue = new ArrayList<>();
                }
            }
        }
        return result;
    }

    @Test
    public void test003(){
        int[] groupSizes = {1,2,5,9};
        Assert.assertTrue(smallestDivisor(groupSizes,6)==5);
        int[] groupSizes2 = {2,3,5,7,11};
        Assert.assertTrue(smallestDivisor(groupSizes2,11)==3);
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int lo = 1, hi = 1_000_000;
        while (lo < hi){
            int mid = (lo + hi) >> 1;
            int res = cal(nums,mid);
            if (res > threshold) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
    private int cal(int[] nums, int div){
        int sum = 0;
        for (int n : nums){
            sum += n/div;
            if (n % div != 0) sum += 1;
        }
        return sum;
    }
}
