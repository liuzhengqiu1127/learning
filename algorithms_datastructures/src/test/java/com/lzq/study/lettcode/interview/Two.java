package com.lzq.study.lettcode.interview;

import com.lzq.study.lettcode.common.ListNode;
import com.lzq.study.lettcode.weekly.process.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Two {

    @Test
    public void test(){
        Assert.assertTrue(countCharacters(new String[]{"boygirdlggnh", "lnnvsdcrvzfmrvurucrzlfyigcycffpiuoo"}, "usdruypficfbpfbivlrhutcgvyjenlxzeovdyjtgvvfdjzcmikjraspdfp")==0);
        Assert.assertTrue(countCharacters(new String[]{"hello","world","leetcode"}, "welldonehoneyr")==10);
    }

    public int countCharacters(String[] words, String chars) {
        int sum = 0;
        for (String word : words){
            if (word.length() > chars.length()){
                continue;
            }
            if (isContain(word,chars)){
                sum += word.length();
            }
        }
        return sum;
    }
    private boolean isContain(String word, String chars){
        Map<Character,Integer> charIndex = new HashMap<>();
        for (char ch : word.toCharArray()){
            int beginIndex = charIndex.getOrDefault(ch,-1)+1;
            int index = chars.indexOf(ch,beginIndex);
            if (index == -1){
                return false;
            }
            charIndex.put(ch,index);
        }
        return true;
    }

    public int findTilt(TreeNode root) {
        if (root==null) return 0;
        if (root.left==null&&root.right==null) return 0;
        return Math.abs(getTreeVal(root.left)-getTreeVal(root.right)) + findTilt(root.left) + findTilt(root.right);
    }
    private int getTreeVal(TreeNode root){
        if (root==null){
            return 0;
        }
        return root.val + getTreeVal(root.left) + getTreeVal(root.right);
    }

    private ListNode head;
    private int count = 0;
    public void Solution(ListNode head) {
        this.head = head;
        ListNode tmp = head;
        while (tmp!=null){
            this.count++;
            tmp = tmp.next;
        }
    }

    /** Returns a random node's value. */
    public int getRandom() {
        Random random = new Random(count);
        int seed = random.nextInt(count);
        ListNode tmp = this.head;
        int number = 0;
        while (tmp!=null){
            if (number==seed) return tmp.val;
            number++;
            tmp = tmp.next;
        }
        return 0;
    }

}
