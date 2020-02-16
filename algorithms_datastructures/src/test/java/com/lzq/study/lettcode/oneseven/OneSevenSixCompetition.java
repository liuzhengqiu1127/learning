package com.lzq.study.lettcode.oneseven;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class OneSevenSixCompetition {

    public int countNegatives(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int sum = 0;
        for (int i=0; i < rows; i++){
            for (int j=0; j<columns; j++){
                if (grid[i][j] < 0) sum++;
            }
        }
        return sum;
    }

    private List<Integer> collects = null;
    public void ProductOfNumbers() {
        collects = new ArrayList<>();
    }
    public void add(int num) {
        collects.add(num);
    }

    public int getProduct(int k) {
        int len = collects.size();
        int end = len - k;
        int mul = 1;
        for (int index = len-1; index >= end; index--){
            mul *= collects.get(index);
        }
        return mul;
    }

    @Test
    public void test(){
        Assert.assertTrue(maxEvents(new int[][]{{1,2},{2,3},{3,4}})==3);
    }

    /**
     * 先排序，再一个个取就可以
     * @param events
     * @return
     */
    public int maxEvents(int[][] events) {
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });
        boolean[] visit = new boolean[100001];
        int result = 0;
        for (int i = 0; i < events.length; i++){
            for (int value = events[i][0]; value <= events[i][1]; value++){
                if (visit[value] == false){
                    result++;
                    visit[value] = true;
                    break;
                }
            }
        }
        return result;
    }

    @Test
    public void test02(){
        Assert.assertTrue(isPossible(new int[]{9,3,5}));
        Assert.assertTrue(!isPossible(new int[]{1,1,1,2}));
        Assert.assertTrue(isPossible(new int[]{8,5}));
    }

    /**
     * 追求性能控制
     * @param target
     * @return
     */
    public boolean isPossible(int[] target) {
        Arrays.sort(target);
        int len = target.length;
        if (target[len - 1] == 1) return true; // 还原为全1
        if (target[len - 1] < len) return false; // 剪枝

        int otherSum = 0; // 前面的总和
        for (int i = 0; i < len - 1; i++) {
            otherSum += target[i];
            if (target[len - 1] <= otherSum) return false; // 剪枝，已超过最后一个
        }
        target[len - 1] -= otherSum;
        return isPossible(target);
    }

    public boolean isPossible2(int[] target) {
        if(Arrays.stream(target).filter(i->i<1).findAny().isPresent()) return false;
        int sum = Arrays.stream(target).sum();
        if(sum==target.length) return true;
        int maxValue = Arrays.stream(target).max().getAsInt();
        for (int i=0; i < target.length; i++) {
            if (target[i] == maxValue) {
                target[i] = 2 * maxValue - sum;
                break;
            }
        }
        return isPossible2(target);
    }
}
