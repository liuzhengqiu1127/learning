package com.lzq.study.lettcode.weekly.oneseven;

import com.lzq.study.lettcode.weekly.process.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneSevenTwoCompetition {

    @Test
    public void test01() {
        Assert.assertEquals(maximum69Number(9669), 9969);
        Assert.assertEquals(maximum69Number(9996), 9999);
        Assert.assertEquals(maximum69Number(9999), 9999);
    }

    public int maximum69Number(int num) {
        String string = String.valueOf(num);
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = false;
        for (char ch : string.toCharArray()) {
            if (ch == '6' && !flag) {
                stringBuilder.append('9');
                flag = true;
                continue;
            }
            stringBuilder.append(ch);
        }
        return Integer.valueOf(stringBuilder.toString());
    }

    @Test
    public void test02() {
        System.out.println(printVertically("HOW ARE YOU"));
        System.out.println(printVertically("TO BE OR NOT TO BE"));
        System.out.println(printVertically("CONTEST IS COMING"));
    }

    public List<String> printVertically(String s) {
        List<String> result = new ArrayList<>();
        String[] strings = s.split(" ");
        int maxLen = 0;
        for (String string : strings) {
            maxLen = (string.length() > maxLen ? string.length() : maxLen);
        }
        for (int i = 0; i < maxLen; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String string : strings) {
                if (string.length() > i) {
                    stringBuilder.append(string.charAt(i));
                } else {
                    stringBuilder.append(" ");
                }
            }
            result.add(trimEnd(stringBuilder.toString()));
        }
        return result;
    }

    private String trimEnd(String string) {
        char[] value = string.toCharArray();
        int len = value.length;
        int st = 0;
        char[] val = value;
        while ((st < len) && (val[len - 1] <= ' ')) {
            len--;
        }
        return ((st > 0) || (len < value.length)) ? new String(val).substring(st, len) : new String(val);
    }

    @Test
    public void test03() {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(2);
        TreeNode treeNode5 = new TreeNode(4);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        System.out.println(removeLeafNodes(treeNode, 2));
    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        TreeNode head = root;
        if (head.left != null) {
            deleteTargetNode(head, head.left, 0, target);
        }
        if (head.right != null) {
            deleteTargetNode(head, head.right, 1, target);
        }
        if (head.left == null && head.right == null) {
            if (head.val == target) return null;
            else return root;
        }
        return root;
    }
    private void deleteTargetNode(TreeNode parent, TreeNode child, int index, int target) {
        if (child.left != null) {
            deleteTargetNode(child, child.left, 0, target);
        }
        if (child.right != null) {
            deleteTargetNode(child, child.right, 1, target);
        }
        if (child.left == null && child.right == null && child.val == target) {
            if (index == 0) parent.left = null;
            else parent.right = null;
        }
    }

    @Test
    public void test04() {
        Assert.assertTrue(minTaps(5,new int[]{3,4,1,1,0,0})==1);
        Assert.assertTrue(minTaps(3,new int[]{0,0,0,0})==-1);
        Assert.assertTrue(minTaps(7,new int[]{1,2,1,0,2,1,0,1})==3);
        Assert.assertTrue(minTaps(8,new int[]{4,0,0,0,0,0,0,0,4})==2);
        Assert.assertTrue(minTaps(8,new int[]{4,0,0,0,4,0,0,0,4})==1);
    }
    private int sum;
    public int minTaps(int n, int[] ranges) {
        List<List<Integer>> rangeList = new ArrayList<>();
        for (int i=0; i < ranges.length; i++){
            rangeList.add(Arrays.asList(i-ranges[i],i+ranges[i]));
        }
        sum = 0;
        getTaps(0,n,rangeList);
        return sum;
    }
    private void getTaps(int start,int end,List<List<Integer>> rangeList)
    {
        if (start >= end) return ;
        List<Integer> integerList = rangeList.stream().filter(list -> list.get(0)<=start).max((list1,list2)->(list1.get(1)-list2.get(1))).get();
        if (integerList==null||integerList.isEmpty()||integerList.get(1)<=start){
            sum = -1;
            return;
        }
        sum++;
        getTaps(integerList.get(1),end,rangeList);
    }
}
