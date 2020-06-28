package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

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
        System.out.println(minNumberOfSemesters(11,new int[][]{},2));
    }

    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        if (dependencies.length == 0){
            return n%k==0 ? n/k : (n/k + 1);
        }

        Map<Integer,List<Integer>> record = new HashMap<>();

        for (int i=0; i<dependencies.length; i++){
            int child = dependencies[i][0];
            int parent = dependencies[i][1];
            if (record.containsKey(parent)&&record.containsKey(child)){
                record.get(parent).add(child);
            }else if (record.containsKey(parent)&&!record.containsKey(child)){
                record.get(parent).add(child);
                record.put(child,new ArrayList<>());
            }else if (!record.containsKey(parent)&&!record.containsKey(child)){
                List<Integer> childList = new ArrayList<>();
                childList.add(child);
                record.put(parent,childList);
                record.put(child,new ArrayList<>());
            } else{
                List<Integer> childList = new ArrayList<>();
                childList.add(child);
                record.put(parent,childList);
            }
        }

        int sum = 0;
        List<Integer> yeZiList = record.entrySet().stream().filter(integerListEntry -> integerListEntry.getValue().isEmpty()).map(integerListEntry -> integerListEntry.getKey()).collect(Collectors.toList());
        sum += (yeZiList.size()%k==0)?(yeZiList.size()/k):(yeZiList.size()/k+1);
        Queue<List<Integer>> queue = new LinkedBlockingQueue<>();
        queue.add(yeZiList);
        while (queue.isEmpty()){
            List<Integer> popList = queue.poll();
            List<Integer> parentList = record.entrySet().stream()
                    .filter(integerListEntry -> isContain(integerListEntry.getValue(), popList))
                    .map(integerListEntry -> integerListEntry.getKey())
                    .collect(Collectors.toList());
            if (parentList.isEmpty()) break;
            sum += (parentList.size()%k==0)?(parentList.size()/k):(parentList.size()/k+1);
            queue.add(parentList);
        }
        return sum;

    }

    private boolean isContain(List<Integer> childList, List<Integer> recordList){
        return recordList.stream().filter(integer -> childList.contains(integer)).findAny().isPresent();
    }

}
