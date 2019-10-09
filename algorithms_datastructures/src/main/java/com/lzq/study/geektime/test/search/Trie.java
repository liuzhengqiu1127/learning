package com.lzq.study.geektime.test.search;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Trie {
    private AcNode root = new AcNode('/'); // 存储无意义字符

    public void buildFailurePointer(){
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()){
            AcNode p = queue.remove();
            for (int i = 0; i < 28; ++i){
                AcNode pc = p.children[i];
                if (pc == null) continue;
                if (p == root){
                    pc.fail = root;
                }else {
                    AcNode q = p.fail;
                    while (q != null){
                        AcNode qc = q.children[getIdx(pc.data)];
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

    public List<Integer> match(char[] text){ // text是主串
        List<Integer> matchIndexes = new ArrayList<>();
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; ++i){
            int idx = getIdx(text[i]);
            while (p.children[idx] == null && p != root){
                p = p.fail;
            }
            p = p.children[idx];
            if (p == null) p = root;
            AcNode tmp = p;
            while (tmp != root){
                if (tmp.isEndingChar == true){
                    int pos = i - tmp.length + 1;
                    matchIndexes.add(pos);
                }
                tmp = tmp.fail;
            }
        }
        return matchIndexes;
    }

    /**
     * 往Trie中插入一个字符串
     * @param text
     */
    public void insert(char[] text){
        AcNode p = root;
        for (int i = 0; i < text.length; ++i){
            int index = getIdx(text[i]);
            if (p.children[index] == null){
                AcNode newNode = new AcNode(text[i]);
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
        AcNode p = root;
        for (int i = 0; i < pattern.length; ++i){
            int index = getIdx(pattern[i]);
            if (p.children[index] == null){
                return false;
            }
            p = p.children[index];
        }
        if (p.isEndingChar == false) return false;//不能完全匹配，只是前缀
        else return true;
    }

    public int getIdx(char text){
        if (text == '<') return 26;
        else if (text == '>') return 27;
        else return text - 'a';
    }

    public class AcNode {
        public char data;
        public AcNode[] children = new AcNode[28];//字符集只包含 a-z > < 这28个字符
        public boolean isEndingChar = false; // 结尾字符为true
        public int length = -1; // 当 isEndingChar = true时，记录模式串长度
        public AcNode fail; // 失败指针
        public AcNode(char data) {
            this.data = data;
        }
    }
}
