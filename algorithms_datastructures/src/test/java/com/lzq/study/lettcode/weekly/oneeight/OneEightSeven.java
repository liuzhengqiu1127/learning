package com.lzq.study.lettcode.weekly.oneeight;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class OneEightSeven {
    public String destCity(List<List<String>> paths) {
        List<String> startPaths = new ArrayList<>();
        List<String> endPaths = new ArrayList<>();
        for (List<String> path : paths){
            startPaths.add(path.get(0));
            endPaths.add(path.get(1));
        }
        for (String endPath : endPaths){
            if (!startPaths.contains(endPath)){
                return endPath;
            }
        }
        return "";
    }

    public boolean kLengthApart(int[] nums, int k) {
        List<Integer> oneIndexList = new ArrayList<>();
        for (int i=0; i<nums.length; i++){
            if (nums[i] == 1){
                oneIndexList.add(i);
            }
        }
        if (oneIndexList.isEmpty() || k == 0) return true;
        int min = Integer.MAX_VALUE;
        int begin = oneIndexList.get(0);
        for (int i=1; i<oneIndexList.size(); i++){
            min = Integer.min(min, oneIndexList.get(i) - begin);
            begin = oneIndexList.get(i);
        }
        return min > k;
    }

    public int longestSubarray(int[] nums, int limit) {
        int max = 0;
        for (int index=0; index < nums.length; index++){
            if (max >= (nums.length-index)) break;
            int len = getEverySub(nums, index, limit);
            max = Integer.max(len, max);
        }
        return max;
    }
    private int getEverySub(int[] nums, int index, int limit){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int len = 0;
        for (int i=index; i<nums.length; i++){
            min = Integer.min(min,nums[i]);
            max = Integer.max(max,nums[i]);
            if (Math.abs(min-nums[i])<=limit && Math.abs(max-nums[i]) <= limit){
                len++;
            }else {
                break;
            }
        }
        return len;
    }

    /**
     * BFS 
     * @param mat
     * @param k
     * @return
     */
    public int kthSmallest(int[][] mat, int k) {
        int sum = 0;
        for (int i=0; i < mat.length; i++) sum += mat[i][0];
        PriorityQueue<Item> priorityQueue = new PriorityQueue<>((o1,o2) -> o1.total - o2.total);
        Set<String> visit = new HashSet<>();
        Item first = new Item(new int[mat.length], sum);
        visit.add(Arrays.toString(first.pick));
        priorityQueue.offer(first);
        while (k > 1){
            Item item = priorityQueue.poll();
            for (int i=0; i < mat.length; i++){
                item.pick[i]++;
                if (item.pick[i] < mat[i].length && !visit.contains(Arrays.toString(item.pick))){
                    visit.add(Arrays.toString(item.pick));
                    int[] pick = Arrays.copyOf(item.pick, item.pick.length);
                    int total = item.total - mat[i][item.pick[i]-1] + mat[i][item.pick[i]];
                    priorityQueue.add(new Item(pick,total));
                }
                item.pick[i]--;
            }
            k--;
        }
        return priorityQueue.peek().total;
    }
    private class Item{
        private int[] pick;
        private int total;
        public Item(int[] pick, int total){
            this.pick = pick;
            this.total = total;
        }
    }

    @Test
    public void test(){
        int[][] mat = new int[][]{{1,1,10},{2,2,9}};
        Assert.assertTrue(kthSmallest(mat,7)==12);
    }
}
