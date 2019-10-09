package com.lzq.study.lettcode.diffcult;

import com.lzq.study.lettcode.common.ListNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by liuzhengqiu on 2019/9/24.
 */
public class Solution {

    /**
     * 暴力回溯算法
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match = (!s.isEmpty()) && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s,p.substring(2)) || (first_match && isMatch(s.substring(1),p));
        } else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }

    /**
     * 动态规划
     * @param s
     * @param p
     * @return
     */
    private Map<String,Boolean> memo = new HashMap<>();
    public boolean isMatch2(String s, String p){
        return dp(0,0,s,p);
    }
    private boolean dp(int i,int j,String s,String p){
        if (memo.containsKey(i+","+j)) return memo.get(i+","+j);
        if (j == p.length()) return i == s.length();
        boolean first = i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');
        boolean ans = false;
        if (j <= p.length() - 2 && p.charAt(j+1) == '*'){
            ans = dp(i,j+2,s,p) || (first && dp(i+1,j,s,p));
        }else {
            ans = first && dp(i+1,j+1,s,p);
        }
        memo.put(i+","+j,ans);
        return ans;
    }

    /**
     * 合并K个有序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        queue.addAll(Stream.of(lists).filter(Objects::nonNull).collect(Collectors.toList()));
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            p = p.next;
            if (node.next != null)
                queue.add(node.next);
        }
        return dummy.next;
    }
    public ListNode mergeKLists2(ListNode[] lists) {
        List<Integer> values = new ArrayList<>();
        for (ListNode node : lists){
            while (node != null){
                values.add(node.val);
                node = node.next;
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        Collections.sort(values);
        for (Integer value : values){
            p.next = new ListNode(value);
            p = p.next;
        }
        return dummy.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null){
            for (int i=0;i<k && end!=null;i++) end = end.next;
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }
    private ListNode reverse(ListNode head){
        ListNode node = null;
        ListNode prev = null;
        while (head != null){
            node = head;
            head = head.next;
            node.next = prev;
            prev = node;
        }
        return node;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) return res;
        int wordLen = words[0].length();
        Map<String,Integer> allWords = new HashMap<>();
        for (String word : words){
            int value = allWords.getOrDefault(word,0);
            allWords.put(word,value + 1);
        }
        for (int i=0; i < s.length() - wordNum * wordLen + 1; i++){
            Map<String,Integer> hashWords = new HashMap<>();
            int number = 0;
            while (number < wordNum){
                String word = s.substring(i+number*wordLen,i+(number+1)*wordLen);
                if (allWords.containsKey(word)){
                    int value = hashWords.getOrDefault(word,0);
                    hashWords.put(word,value+1);
                    if (hashWords.get(word) > allWords.get(word)) break;
                }else {
                    break;
                }
                number++;
            }
            if (number == wordNum) res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
