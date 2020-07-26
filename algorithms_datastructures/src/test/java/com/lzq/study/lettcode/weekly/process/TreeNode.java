package com.lzq.study.lettcode.weekly.process;

/**
 * Created by liuzhengqiu on 2019/12/1.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x){
        this.val = x;
    }
    public TreeNode(TreeNode left, TreeNode right, int val){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
