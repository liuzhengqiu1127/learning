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
    public int lengthOfLastWord(String s) {
        if(s == null || s.trim().isEmpty()) return 0;
        String[] strings = s.split(" ");
        return strings[strings.length-1].length();
    }

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        if (digits[len - 1] < 9){
            digits[len - 1] += 1;
            return digits;
        }
        int i = len - 1;
        for (; i >= 0; i--){
            if (digits[i] == 9){
                digits[i] = 0;
            }else {
                digits[i] += 1;
                break;
            }
        }
        if ( i < 0){
            int[] res = new int[len+1];
            res[0] = 1;
            return res;
        }
        return digits;
    }

    public String addBinary(String a, String b) {
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        int sum = 0;
        StringBuilder res = new StringBuilder();
        while (aIndex >= 0 && bIndex >= 0){
            int two_sum = (Integer.valueOf(a.charAt(aIndex)) - 48) + (Integer.valueOf(b.charAt(bIndex)) - 48) + sum;
            if (two_sum >= 2){
                res.append(two_sum%2);
                sum = 1;
            }else {
                res.append(two_sum);
                sum = 0;
            }
            aIndex--;
            bIndex--;
        }
        while (aIndex >= 0){
            int two_sum = (Integer.valueOf(a.charAt(aIndex)) - 48) + sum;
            if (two_sum == 2){
                res.append(0);
                sum = 1;
            }else {
                res.append(two_sum);
                sum = 0;
            }
            aIndex--;
        }
        while (bIndex >= 0){
            int two_sum = (Integer.valueOf(b.charAt(bIndex)) - 48) + sum;
            if (two_sum == 2){
                res.append(0);
                sum = 1;
            }else {
                res.append(two_sum);
                sum = 0;
            }
            bIndex--;
        }
        if (sum == 1){
            res.append(1);
        }
        StringBuilder result = new StringBuilder();
        for (int i = res.length()-1;i>=0;i--){
            result.append(res.charAt(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String result = new Solution1().addBinary("10","110010");
        System.out.println(result);
    }
}
