package com.lzq.study.lettcode.weekly.onenine;

import com.lzq.study.lettcode.weekly.process.TreeNode;
import org.junit.Test;

public class OneNineNine {

    public String restoreString(String s, int[] indices) {
        int len = indices.length;
        char[] result = new char[len];
        for (int i=0; i < len; i++){
            result[indices[i]] = s.charAt(i);
        }
        return String.valueOf(result);
    }

    public int minFlips(String target) {
        char[] targetChar = target.toCharArray();
        char flag = '0';
        int sum = 0;
        for (int i=0; i < targetChar.length; i++){
            if (flag == target.charAt(i)) {
                continue;
            } else{
                sum++;
                flag = (flag == '0' ? '1' : '0');
            }
        }
        return sum;
    }

    /**
     * 使用穷尽算法
     */
    int ans = 0;
    public int countPairs(TreeNode root, int distance) {
        if (root == null) return 0;
        dfs(root.left, root.right, 1, 1, distance);//先计算root为根时
        countPairs(root.left, distance);
        countPairs(root.right, distance);
        return ans;
    }
    void dfs(TreeNode l, TreeNode r, int ls, int rs, int d){
        if (l == null | r == null || ls > d || rs > d) return;
        if (isLeaf(l) && isLeaf(r)){
            if (ls + rs <= d){
                ans++;
            }
        }else if (isLeaf(l)){
            dfs(l, r.left, ls, rs+1, d);
            dfs(l, r.right, ls, rs+1, d);
        }else if (isLeaf(r)){
            dfs(l.left, r, ls+1, rs, d);
            dfs(l.right, r, ls+1, rs, d);
        }else{
            dfs(l.left, r.left, ls+1,rs+1, d);
            dfs(l.right, r.left, ls+1, rs+1, d);
            dfs(l.left, r.right, ls+1, rs+1, d);
            dfs(l.right, r.right, ls+1, rs+1, d);
        }
    }
    boolean isLeaf(TreeNode p){
        if (p == null) return false;
        return p.left == null && p.right == null;
    }



    @Test
    public void test(){
        System.out.println(minFlips("001011101"));
    }
}
