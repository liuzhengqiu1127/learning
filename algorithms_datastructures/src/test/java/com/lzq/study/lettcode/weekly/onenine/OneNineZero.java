package com.lzq.study.lettcode.weekly.onenine;

import com.lzq.study.lettcode.weekly.process.TreeNode;
import org.junit.Test;

public class OneNineZero {

    @Test
    public void test01(){

    }
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] splits = sentence.split(" ");
        int count = 1;
        for (String split : splits){
            if (split.indexOf(searchWord)==0){
                return count;
            }
            count++;
        }
        return -1;
    }

    @Test
    public void test02(){
        String s = "tryhard";
        int k = 4;
        System.out.println(maxVowels(s,k));
    }

    /**
     * 双指针 最左边 最右边
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        int n = s.length();
        int count = 0, result = 0;
        int i = 0;
        for (int j = 0; j < n; j++){

            if ("aeiou".indexOf(String.valueOf(s.charAt(j))) != -1) count++;

            if (j > k - 1){
                if ("aeiou".indexOf(String.valueOf(s.charAt(i))) != -1) count--;
                i++;
            }

            result = Math.max(result, count);
        }
        return result;
    }


    @Test
    public void test03(){

    }
    int ans = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        if (root == null) return 0;
        help(root,0);
        return ans;
    }
    private void help(TreeNode node, int temp){
        temp ^= 1 << node.val;
        if (node.right==null && node.left==null){
            if (temp == 0 || (temp&(temp-1))==0 ){
                ans++;
            }
        }
        if (node.left != null) help(node.left, temp);
        if (node.right != null) help(node.right, temp);
    }

    @Test
    public void test04(){

    }
}
