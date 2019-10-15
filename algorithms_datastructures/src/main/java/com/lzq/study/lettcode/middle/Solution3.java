package com.lzq.study.lettcode.middle;

import java.util.*;

/**
 * Created by liuzhengqiu on 2019/10/10.
 */
public class Solution3 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        int first = 0;
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) numList.add(num);
        backtrack(numList,first,len,result);
        return result;
    }
    private void backtrack(List<Integer> numList, int first, int n, List<List<Integer>> result)
    {
        if (first == n){
            result.add(new ArrayList<>(numList));
            return;
        }
        for (int i = first; i < n; i++){
            Collections.swap(numList,first,i);
            backtrack(numList,first+1,n,result);
            Collections.swap(numList,first,i);
        }
    }
    List<List<Integer>> list =new ArrayList<>();
    ArrayList<Integer> asdd = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {

        if(nums.length <= 0) return list;
        if(nums.length == 1){//如果长度为1则直接返回
            asdd.add(nums[0]);
            list.add(asdd);
            return list;
        }
        int[] v = new int[nums.length];//记录改位置是否呗使用
        Arrays.sort(nums);//排序，也可以不排序，但是时间复杂度较高
        co1(list,asdd,nums,v);//递归调用
        return list;
    }
    public void co1(List<List<Integer>> list,List<Integer> cc,int[] nums,int v[]){
        if(cc.size() == nums.length){//退出递归条件：当list长度等于数组长度时代表已经遍历完，可以退出
            list.add(new ArrayList<>(cc));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            //#############排序实现
            if(v[i] != 1) { //如果改位置已被使用则直接跳过
                if(i > 0 && nums[i] == nums[i-1] && v[i - 1] != 1){//去除重复的值，如果该位置的值为5，上一个位置的也为5，则冲突，跳过
                    continue;
                }
                cc.add(nums[i]);//加入List
                v[i] = 1;//标记该位置已被使用
                co1(list, cc, nums,v);//递归查找List的下一个位置
                v[i] = 0;//递归完成之后，复原该位置未被使用
                cc.remove(cc.size() - 1);//还原该位置，移除
            }
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return new ArrayList<>();
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();

        int row_start = 0;
        int row_end = m - 1;
        int column_start = 0;
        int column_end = n - 1;

        while (row_start <= row_end && column_start <= column_end){
            if (row_end == row_start){
                for (int j = column_start; j <= column_end; j++){
                    res.add(matrix[row_start][j]);
                }
                return res;
            }
            if (column_start == column_end){
                for (int i = row_start; i <= row_end; i++){
                    res.add(matrix[i][column_start]);
                }
                return res;
            }
            for (int j = column_start; j <= column_end; j++){
                res.add(matrix[row_start][j]);
            }
            for (int i = row_start + 1; i <= row_end; i++){
                res.add(matrix[i][column_end]);
            }
            for (int j = column_end - 1; j >= column_start; j--){
                res.add(matrix[row_end][j]);
            }
            for (int i = row_end - 1; i > row_start; i--){
                res.add(matrix[i][column_start]);
            }

            row_start++;
            column_start++;
            row_end--;
            column_end--;
        }

        return res;

    }

    Index[] memo;
    public boolean canJump1(int[] nums) {
        return canJumpFromPosition1(0,nums);
    }
    private boolean canJumpFromPosition1(int position, int[] nums){
        if (position == nums.length - 1){
            return true;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++){
            if (canJumpFromPosition1(nextPosition,nums)){
                return true;
            }
        }
        return false;
    }
    public boolean canJump2(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++){
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPosition2(0,nums);
    }
    private boolean canJumpFromPosition2(int position, int[] nums){
        if (memo[position] != Index.UNKNOWN){
            return memo[position] == Index.GOOD ? true : false;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++){
            if (canJumpFromPosition2(nextPosition, nums)){
                memo[position] = Index.GOOD;
                return true;
            }
        }
        memo[position] = Index.BAD;
        return false;
    }
    public boolean canJump3(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++){
            memo[i] = Index.UNKNOWN;
        }
        memo[nums.length - 1] = Index.GOOD;
        for (int position = nums.length - 2; position >= 0; position--){
            int furthestJump = Math.min(position + nums[position], nums.length - 1);
            for (int j = position + 1; j <= furthestJump; j++){
                if (memo[j] == Index.GOOD){
                    memo[position] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD;
    }
    public boolean canJump4(int[] nums){
        int lastPosition = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--){
            if ( i + nums[i] >= lastPosition){
                lastPosition = i;
            }
        }
        return lastPosition == 0;
    }
    enum Index{
        GOOD, BAD, UNKNOWN
    }
    public int[][] merge(int[][] intervals) {
        List<Interval> intervalList = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++){
            intervalList.add(new Interval(intervals[i][0],intervals[i][1]));
        }
        Collections.sort(intervalList,new IntervalComparator());
        LinkedList<Interval> merged = new LinkedList<>();
        for (Interval interval : intervalList){
            if (merged.isEmpty()||merged.getLast().end < interval.start){
                merged.add(interval);
            }else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }
        int[][] res = new int[merged.size()][2];
        int i = 0;
        for (Interval interval : merged){
            res[i][0] = interval.start;
            res[i][1] = interval.end;
            i++;
        }
        return res;
    }
    class IntervalComparator implements Comparator<Interval>{
        @Override
        public int compare(Interval o1, Interval o2) {
            return (o1.start < o2.start) ? -1 : (o1.start == o2.start ? 0:1);
        }
    }
    class Interval{
        int start;
        int end;
        Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        List<List<Integer>> result = new Solution3().permuteUnique(nums);
        result.forEach(list -> System.out.println(list));
    }
}
