package com.lzq.study.lettcode.oneeight;

import com.lzq.study.lettcode.process.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class OneEightZero {
    @Test
    public void test01(){
        System.out.println(luckyNumbers(new int[][]{{3,7,8},{9,11,13},{15,16,17}}));
        System.out.println(luckyNumbers(new int[][]{{1,10,4,2},{9,3,8,7},{15,16,17,12}}));
    }
    public List<Integer> luckyNumbers (int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0; i < rows; i++){
            int min = matrix[i][0];
            int k = 0;
            for (int j=0; j < columns; j++){
                if (matrix[i][j] < min){
                    min = matrix[i][j];
                    k = j;
                }
            }
            map.put(i,k);
        }
        List<Integer> result = new ArrayList<>();
        for (int j=0; j < columns; j++){
            int max = matrix[0][j];
            int k = 0;
            for (int i = 0; i < rows; i++){
                if (matrix[i][j] > max){
                    max = matrix[i][j];
                    k = i;
                }
            }
            if (map.containsKey(k)&&map.get(k).intValue()==j){
                result.add(max);
            }
        }
        return result;
    }
    @Test
    public void test02(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.right = node2;
        node2.right = node3;
        node3.right = node4;
        TreeNode treeNode = balanceBST(node1);
        print(treeNode);
    }
    private void print(TreeNode treeNode){
        if (treeNode==null) return;
        System.out.println(treeNode.val);
        print(treeNode.left);
        print(treeNode.right);
    }
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> buffer = new ArrayList<>();
        search(root,buffer);
        return build(buffer,0,buffer.size()-1);
    }
    private void search(TreeNode root, List<Integer> buffer)
    {
        if (root == null) return;
        search(root.left,buffer);//左
        buffer.add(root.val);//中
        search(root.right,buffer);//右
    }
    //平衡二叉树构造
    private TreeNode build(List<Integer> buffer, int i, int j){
        if (i > j) return null;
        if (i == j) return new TreeNode(buffer.get(i));
        int m = i + (j-i)/2;
        TreeNode root = new TreeNode(buffer.get(m));
        root.left = build(buffer,i,m-1);
        root.right = build(buffer,m+1,j);
        return root;
    }


    @Test
    public void test03(){
        int[] speed = {2,10,3,1,5,8};
        int[] efficiency = {5,4,3,9,7,2};
        Assert.assertTrue(maxPerformance(6,speed,efficiency,2)==60);
    }
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k){
        int[][] items = new int[n][2];
        for (int i=0; i<n; i++){
            items[i][0] = speed[i];
            items[i][1] = efficiency[i];
        }
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });//按照效率从大到小排序，获取每个效率前面最大的K个speed之和
        PriorityQueue<Integer> queue = new PriorityQueue<>(); //queue中存放
        long res = 0, sum = 0;
        for (int i=0; i < n; i++){
            queue.add(items[i][0]);
            sum += items[i][0];
            if (queue.size() > k){
                sum -= queue.poll();
            }
            res = Math.max(res, sum * items[i][1]);
        }
        return (int) (res % ((int)1e9 + 7));
    }
}
