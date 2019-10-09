package com.lzq.study.geektime.structure.trie;

public class TrieNode {
    char data;
    TrieNode[] children = new TrieNode[26];//字符集只包含a-z
    boolean isEndingChar = false;//结尾字符为true
    public int length = -1; // 当isEndingChar=true时，记录模式串长度
    public TrieNode fail;//失败指针
    public TrieNode(char data){
        this.data = data;
    }
}
