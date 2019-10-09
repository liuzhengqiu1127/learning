package com.lzq.study.lettcode.middle;

import sun.util.resources.cldr.xh.CurrencyNames_xh;

import java.util.*;

public class Solution {
    public int myAtoi(String str) {
        str = str.trim();
        if (str == null || "".equals(str)) return 0;
        String first = str.substring(0, 1);
        if (!first.matches("[0-9]|\\+|-")) {
            return 0;
        }
        if (first.matches("-|\\+") && (str.length() == 1 || !str.substring(1, 2).matches("[0-9]"))) {
            return 0;
        }
        StringBuilder result = new StringBuilder();
        int start = 0;
        if (first.matches("-|\\+")) {
            result.append(first);
            start = 1;
        }
        for (; start < str.length(); start++) {
            if (!("" + str.charAt(start)).matches("[0-9]")) {
                break;
            }
            result.append(str.charAt(start));
        }
        try {
            return Integer.valueOf(result.toString());
        } catch (Exception e) {
            if (first.equals("-")) return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;
        }
    }

    /**
     * 暴力破解法
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int minValue = Integer.min(height[i], height[j]);
                int area = (j - i) * minValue;
                if (area > max) {
                    max = area;
                }
            }
        }
        return max;
    }

    /**
     * 两指针方法
     *
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int maxArea = 0;
        int l = 0, r = height.length - 1;
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return maxArea;
    }

    public String intToRoman(int num) {
        if (num >= 1 && num < 10) {
            return getRoman(num, "I", "V", "X");
        } else if (num >= 10 && num < 100) {
            return getRoman(num / 10, "X", "L", "C") +
                    getRoman(num % 10, "I", "V", "X");
        } else if (num >= 100 && num < 1000) {
            return getRoman(num / 100, "C", "D", "M") +
                    getRoman((num % 100) / 10, "X", "L", "C") +
                    getRoman(num % 10, "I", "V", "X");
        } else {
            return getRoman(num / 1000, "M", "", "") +
                    getRoman((num % 1000) / 100, "C", "D", "M") +
                    getRoman((num % 100) / 10, "X", "L", "C") +
                    getRoman(num % 10, "I", "V", "X");
        }
    }

    private String getRoman(int num, String r1, String r5, String r10) {
        switch (num) {
            case 0:
                return "";
            case 1:
                return r1;
            case 2:
                return r1 + r1;
            case 3:
                return r1 + r1 + r1;
            case 4:
                return r1 + r5;
            case 5:
                return r5;
            case 6:
                return r5 + r1;
            case 7:
                return r5 + r1 + r1;
            case 8:
                return r5 + r1 + r1 + r1;
            case 9:
                return r1 + r10;
            default:
                return "";
        }
    }

    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int ans = 0;
        for (int i = 0; i < s.length(); ) {
            if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                ans += map.get(s.substring(i, i + 2));
                i += 2;
            } else {
                ans += map.get(s.substring(i, i + 1));
                i++;
            }
        }
        return ans;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;
        Arrays.sort(nums);
        for (int i = 0, len = nums.length; i < len; i++) {
            int center = nums[i];
            if (center > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int value = nums[i] + nums[left] + nums[right];
                if (value < 0) left++;
                else if (value > 0) right--;
                else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        int result = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0, len = nums.length; i < len; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }
                if (sum > target) right--;
                else if (sum < target) left++;
                else return result;

            }
        }
        return result;
    }

    private List<String> result = new ArrayList<>();
    private char[][] chars = new char[10][];
    private int[] numbers;
    public List<String> letterCombinations(String digits) {
        if (digits == null || "".equals(digits)) {
            return result;
        }
        chars[2] = new char[]{'a', 'b', 'c'};
        chars[3] = new char[]{'d', 'e', 'f'};
        chars[4] = new char[]{'g', 'h', 'i'};
        chars[5] = new char[]{'j', 'k', 'l'};
        chars[6] = new char[]{'m', 'n', 'o'};
        chars[7] = new char[]{'p', 'q', 'r', 's'};
        chars[8] = new char[]{'t', 'u', 'v'};
        chars[9] = new char[]{'w', 'x', 'y', 'z'};
        char[] input = digits.toCharArray();
        numbers = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            numbers[i] = input[i] - 48;
        }
        for (int i = 0; i < chars[numbers[0]].length; i++) {
            dfs(chars[numbers[0]][i] + "", 1);//遍历第一个数组的各个字符
        }
        return result;
    }
    private void dfs(String preStr, int level) {
        if (level == numbers.length) {
            result.add(preStr);
        } else {
            char[] chars = this.chars[numbers[level]];//下一个数组
            for (char ch : chars) {
                dfs(preStr + ch, level + 1);//每次进入一层 level + 1，遍历所有层之后就退出
            }
        }
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return kSum(nums,target,4);
    }
    /**
     * 我是一个接口，在系统提供的他们的方法里面调用我即可
     *
     * 相当加了一层包装，对外提供了一个系统可以使用的接口
     * @param nums 系统给定的数组
     * @param target 系统要求的目标值
     * @return 系统要求的返回值
     */
    public List<List<Integer>> kSum(int[] nums, int target, int k) {
        // 先排序，这个是必须的。
        Arrays.sort(nums);

        // 根据模板方法的要求，将该方法需要的输入都准备好。
        int[] stack = new int[k];
        Arrays.fill(stack, 0x3f3f3f3f);
        int stack_index = -1;
        int begin = 0;
        // 递归开始
        List<List<Integer>> ans = K_Sum_Recursive_Template(nums, stack, stack_index, k, begin, target);
        // 递归结束，返回解
        return ans;
    }

    /**
     * K数之和问题的模板方法，所有K数问题都可以调用这个方法
     * @param nums 输入的数组
     * @param stack 定义的一个长度为 k_sum 问题中的 k 的数组，初始化为0x3f3f3f3f
     * @param stack_index 栈指针，初始化值为-1
     * @param k 表明当前问题被 分解/递归 成了 k数之和 的问题
     * @param begin 当前问题要固定的值的起点
     * @param target 当前 k数之和 的目标和
     * @return 当前 k数之和 的解集，要在上一层合并到最终解集里去
     */
    private List<List<Integer>> K_Sum_Recursive_Template(int[] nums, int[] stack, int stack_index, int k, int begin, int target){
        List<List<Integer>> ans = new ArrayList<>();

        // 当递归到两数之和的时候，不再进行递归，直接使用左右指针法解决
        if(k == 2){
            List<Integer> temp_ans;

            int left = begin;
            int right = nums.length - 1;

            while(left < right){
                if(nums[left] + nums[right] > target){
                    // 过大，因此右指针左移
                    right--;
                }else if(nums[left] + nums[right] < target){
                    // 过小，因此左指针右移
                    left++;
                }else {
                    // 相等，加入序列中，左右指针同时向内移动一次
                    temp_ans = new ArrayList<>();
                    stack[++stack_index] = nums[left];
                    stack[++stack_index] = nums[right];

                    // 当前栈中的元素符合题目要求， 将其加入到List中去，并将该List加入到当前问题的解集中
                    for(int i = 0; i <= stack_index; i++){
                        temp_ans.add(stack[i]);
                    }
                    ans.add(temp_ans);

                    // 栈的清理工作，其实不做也可以，因为栈指针属于形参，不会影响外面的那个栈指针，
                    // 但是还是清理掉比较好，方便调试。
                    stack[stack_index--] = 0x3f3f3f3f;
                    stack[stack_index--] = 0x3f3f3f3f;

                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]){
                        left++;
                    }
                    while (right > left && right < nums.length - 1 && nums[right] == nums[right + 1]){
                        right--;
                    }
                }
            }
        }else {
            int target_temp;
            for(int i = begin; i < nums.length - k + 1; i++){
                if(i > begin && nums[i] == nums[i - 1]){
                    continue;
                }
                // 在固定一个数后，问题被降级为一个 k - 1 数之和 问题
                // 确定 k - 1 数之和 的目标和
                target_temp = target - nums[i];
                // 将当前选定的数字压入栈中，便于最后加入解集中
                stack[++stack_index] = nums[i];
                // 递归调用 k - 1 数之和 问题的求解
                List<List<Integer>> ans_temp = K_Sum_Recursive_Template(nums,stack, stack_index, k - 1, i + 1, target_temp);
                // 在选定当前数字的情况下， k - 1 数之和 问题求解完毕，
                // 将该数弹出栈，为选择下一个被选值做准备
                stack[stack_index--] = 0x3f3f3f3f;
                // 将当前解集加入当前 k数之和的解集中
                ans.addAll(ans_temp);
            }
        }
        return ans;
    }
}
