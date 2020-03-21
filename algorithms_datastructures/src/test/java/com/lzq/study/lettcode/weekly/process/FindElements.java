package com.lzq.study.lettcode.weekly.process;

import java.util.HashSet;

/**
 * Created by liuzhengqiu on 2019/12/1.
 */
public class FindElements {
    HashSet<Integer> hashSet;
    public FindElements(TreeNode root) {
        if (root==null) return;
        hashSet = new HashSet<>();
        root.val = 0;
        hashSet.add(root.val);
        solution(root.left,root,true);
        solution(root.right,root,false);
    }
    public void solution(TreeNode root,TreeNode pre,boolean dist){
        if (root == null) return;
        root.val = 2 * pre.val + (dist ? 1 : 2);
        hashSet.add(root.val);
        solution(root.left, root, true);
        solution(root.right, root, false);
    }

    public boolean find(int target) {
        return hashSet.contains(target);
    }
}
