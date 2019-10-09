package com.lzq.study.lettcode.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution1 {

    public String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0) return "";
        String maxSuffix = strs[0];
        for (int i=1,len=strs.length;i<len;i++){
            maxSuffix = getMaxSuffix(strs[i],maxSuffix);
        }
        return maxSuffix;
    }

    private String getMaxSuffix(String str1,String str2){
        StringBuilder result = new StringBuilder();
        int len = Math.min(str1.length(),str2.length());
        for (int i=0;i<len;i++){
            if (str1.charAt(i) == str2.charAt(i)) result.append(str1.charAt(i));
            else break;
        }
        return result.toString();
    }

    /**
     * 栈终于派上用场了
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Character,Character> mappings = new HashMap<>();
        mappings.put(')','(');
        mappings.put(']','[');
        mappings.put('}','{');
        Stack<Character> stack = new Stack<>();
        for (int i=0,len=s.length();i<len;++i){
            char c = s.charAt(i);
            if (mappings.containsKey(c)){
                char topEle = stack.empty() ? '#' : stack.pop();
                if (topEle != mappings.get(c)) return false;
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public int removeDuplicates(int[] nums) {
        if (nums==null||nums.length==0) return 0;
        Arrays.sort(nums);
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public int removeElement(int[] nums, int val){
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++){
            if (nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public int strStr(String haystack, String needle) {
        if (haystack==null&&haystack.isEmpty()) return 0;
        if (needle==null&&needle.isEmpty()) return 0;
        return haystack.indexOf(needle);
    }

    public int searchInsert(int[] nums, int target) {
        if (nums==null||nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int pivot = (left + right) >> 1;
            if (nums[pivot] == target) return pivot;
            else if (nums[pivot] > target) right = pivot - 1;
            else left = pivot + 1;
        }
        return left;
    }
    public String countAndSay(int n) {
        String[] strings = new String[31];
        strings[1] = "1";
        strings[2] = "11";
        strings[3] = "21";
        strings[4] = "1211";
        strings[5] = "111221";
        for (int i = 6; i <= n; i++) {
            String ts = strings[i - 1], rs = "";
            char tc = ts.charAt(0);
            int cnt = 0;
            for (char c: ts.toCharArray()) {
                if (c == tc) {
                    cnt++;
                } else {
                    rs = rs + cnt + tc;
                    tc = c;
                    cnt = 1;
                }
            }
            rs = rs + cnt + tc;
            strings[i] = rs;
        }
        return strings[n];
    }
}
