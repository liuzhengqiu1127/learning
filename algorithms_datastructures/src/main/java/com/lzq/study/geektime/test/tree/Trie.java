package com.lzq.study.geektime.test.tree;

public class Trie {
    private Node root = new Node('/');

    /**
     * 用a-z 构建trie树
     * @param chars
     */
    public void insert(char[] chars){
        int len = chars.length;
        Node p = root;
        for (int i = 0; i < len; i++){
            int index = chars[i] - 'a';
            if (p.children[index]==null){
                p.children[index] = new Node(chars[i]);
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
        p.length = len;
    }

    class Node{
        char data;
        Node[] children = new Node[26];
        boolean isEndingChar = false;//结尾字符为true
        public int length = -1; // 当isEndingChar=true时，记录模式串长度
        public Node fail;//失败指针
        public Node(char data){
            this.data = data;
        }
    }
}
