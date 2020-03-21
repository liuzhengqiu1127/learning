package com.lzq.study.lettcode.oneseven;

import com.lzq.study.lettcode.common.ListNode;
import com.lzq.study.lettcode.weekly.process.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class OneSevenEightCompetition {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        for (int i=0; i < len; i++)
        {
            int count = 0;
            for (int j=0; j < len; j++){
                if (j != i && nums[j] < nums[i]) count++;
            }
            result[i] = count;
        }
        return result;
    }

    @Test
    public void test02(){
        System.out.println(rankTeams(new String[]{"WXYZ","XYZW"}));
//        System.out.println(rankTeams(new String[]{"M","M","M","M"}));
    }

    public String rankTeams(String[] votes) {
        int len = votes.length;
        Map<Character,Integer[]> map = new HashMap<>();
        char[] characters = votes[0].toCharArray();
        for (int j=0;  j<characters.length; j++)
        {
            Integer[] sorts = new Integer[characters.length];
            Character character = characters[j];
            sorts[j] = 1;
            for (int i=1; i < len; i++){
                int index = votes[i].indexOf(character);
                if (Objects.isNull(sorts[index])) sorts[index] = 1;
                else sorts[index] += 1;
            }
            map.put(character,sorts);
        }
        List<Map.Entry<Character,Integer[]>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Character,Integer[]>>() {
            @Override
            public int compare(Map.Entry<Character,Integer[]> o1, Map.Entry<Character,Integer[]> o2) {
                Integer[] sort1 = o1.getValue();
                Integer[] sort2 = o2.getValue();
                for (int i=0; i<characters.length; i++){
                    if (Objects.nonNull(sort1[i])&&Objects.nonNull(sort2[i])&&sort1[i].intValue()!=sort2[i].intValue()){
                        return sort2[i].compareTo(sort1[i]);
                    }else if (Objects.isNull(sort1[i])&&Objects.isNull(sort2[i])){
                        continue;
                    }
                    else if (Objects.isNull(sort1[i])&&Objects.nonNull(sort2[i])){
                        return 1;
                    }else if (Objects.isNull(sort2[i])&&Objects.nonNull(sort1[i])){
                        return -1;
                    }else{
                        continue;
                    }
                }
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Character,Integer[]> entry : entryList){
            stringBuilder.append(entry.getKey());
        }
        return stringBuilder.toString();
    }

    @Test
    public void test03(){
        ListNode head = new ListNode(4); ListNode node1 = new ListNode(2); ListNode node2 = new ListNode(8);
        head.next = node1; node1.next = node2;
        TreeNode root = new TreeNode(1);
        TreeNode tree1 = new TreeNode(4);
        TreeNode tree2 = new TreeNode(4);
        root.left = tree1; root.right = tree2;
        TreeNode tree3 = new TreeNode(2);
        tree1.right = tree3;
        TreeNode tree4 = new TreeNode(1);
        tree3.left = tree4;
        TreeNode tree5 = new TreeNode(2);
        tree2.left = tree5;
        TreeNode tree6 = new TreeNode(6);
        TreeNode tree7 = new TreeNode(8);
        tree5.left = tree6;
        tree5.right = tree7;
        TreeNode tree8 = new TreeNode(1);
        TreeNode tree9 = new TreeNode(3);
        tree7.left = tree8;
        tree7.right = tree9;
        Assert.assertTrue(isSubPath(head,root));
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head==null) return true;
        if (root==null) return false;
        return isSub(head,root) || isSubPath(head, root.left) || isSubPath(head, root.right);//遍历所有
    }

    /**
     * 遍历一次是否通过
     * @param head
     * @param node
     * @return
     */
    private boolean isSub(ListNode head, TreeNode node){
        if (head == null) return true;
        if (node == null) return false;
        if (head.val != node.val) return false;
        return isSub(head.next,node.left) || isSub(head.next, node.right);
    }

    @Test
    public void test04(){
        Assert.assertTrue(minCost(new int[][]{{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}})==3);
    }

    /**
     * BFS遍历
     * @param grid
     * @return
     */
    public int minCost(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int dst[][] = new int[rows][columns];
        int d[][] = {{},{0,1},{0,-1},{1,0},{-1,0}};
        for (int i = 0; i < rows; i++){
            Arrays.fill(dst[i], -1);
        }
        Queue<int[]> queue = new LinkedList<>(); //定义Queue
        queue.offer(new int[]{0,0,0});//行，列，当前cost 添加初始值
        while (!queue.isEmpty()){//判断queue是否为空
            int x = queue.size(); // 记录每次遍历的大小
            for (int i=0; i < x; i++){
                int q[] = queue.poll();
                if (q[0] == rows-1 && q[1] == columns-1){
                    continue;
                }
                int val = grid[q[0]][q[1]];
                for (int j=1; j <=4; j++){
                    int row = q[0] + d[j][0];
                    int column = q[1] + d[j][1];
                    if (row >= 0 && column >=0 && row < rows && column < columns){
                        int add = (j==val?0:1);
                        if (dst[row][column]==-1 || dst[row][column] > q[2] + add){
                            dst[row][column] = q[2] + add;
                            queue.offer(new int[]{row,column,dst[row][column]});
                        }
                    }
                }
            }
        }
        return Math.max(0, dst[rows - 1][columns - 1]);
    }
}
