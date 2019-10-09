package com.lzq.study.geektime.structure.trie;

import java.util.LinkedList;
import java.util.Queue;

public class TrieTest {

    private TrieNode root = new TrieNode('/');

    public void match(char[] text) { // text 是主串
        int n = text.length;
        TrieNode p = root;
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - 'a';
            while (p.children[idx] == null && p != root) {
                p = p.fail; // 失败指针发挥作用的地方
            }
            p = p.children[idx];
            if (p == null) p = root; // 如果没有匹配的，从 root 开始重新匹配
            TrieNode tmp = p;
            while (tmp != root) { // 打印出可以匹配的模式串
                if (tmp.isEndingChar == true) {
                    int pos = i-tmp.length+1;
                    System.out.println(" 匹配起始下标 " + pos + "; 长度 " + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }


    public void buildFailurePointer(){
        Queue<TrieNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()){
            TrieNode p = queue.remove();
            for (int i=0;i<26;++i){
                TrieNode pc = p.children[i];
                if (pc == null) continue;
                if (p == root){
                    pc.fail = root;
                }else {
                    TrieNode q = p.fail;
                    while (q != null){
                        TrieNode qc = q.children[pc.data-'a'];
                        if (qc != null){
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null){
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }
    /**
     * 往Trie中插入一个字符串
     * @param text
     */
    public void insert(char[] text){
        TrieNode p = root;
        for (int i = 0; i < text.length; ++i){
            int index = text[i] - 'a';
            if (p.children[index] == null){
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
        p.length = text.length;
    }
    /**
     * 在Trie树中查找一个字符串
     * @param pattern
     * @return
     */
    public boolean find(char[] pattern){
        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i){
            int index = pattern[i] - 'a';
            if (p.children[index] == null){
                return false;
            }
            p = p.children[index];
        }
        if (p.isEndingChar == false) return false;//不能完全匹配，只是前缀
        else return true;
    }

    public static void main(String[] args) {
        TrieTest trieTest = new TrieTest();
        trieTest.insert("abc".toCharArray());
        trieTest.insert("bcde".toCharArray());
        trieTest.insert("abd".toCharArray());
        trieTest.insert("dfsfd".toCharArray());
        trieTest.insert("bcffe".toCharArray());
        System.out.println(trieTest.find("bcde".toCharArray()));
        System.out.println(trieTest.find("dfsfe".toCharArray()));
        trieTest.buildFailurePointer();
        trieTest.match("efafadfadfabcdlfaaabdueyfuadbcffeldfhalf".toCharArray());

    }
}
