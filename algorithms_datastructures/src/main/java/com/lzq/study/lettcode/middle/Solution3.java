package com.lzq.study.lettcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        List<List<Integer>> result = new Solution3().permuteUnique(nums);
        result.forEach(list -> System.out.println(list));
    }
}
