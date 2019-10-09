package com.lzq.study.geektime.test.hashtable;

/**
 * 解决LRU 最近最少使用问题
 * 使用散列表和链表组合
 */
public class LRUQuestion<K,V> {
    private int size;//缓冲大小
    transient Node<K,V>[] table;
    private static int defaultValue = 16;
    private int count; // 当前大小

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public LRUQuestion(int size){
        this.size = size;
        this.count = 0;
        table = new Node[16];
    }

    public void put(K key, V value){ // 添加数据
        int hash = hash(key);
        Node<K,V> node = table[(defaultValue-1)&hash];
        if (node!=null){ // 已经在散列表中 需要把其移动到双链表的尾部
            if (node.next==null) return ; // 尾部时
            if (node.prev==null){ // 头部时
                node.next.prev = null;
            }else {// 中间时
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            Node<K,V> p = node;
            while (p.next!=null){
                p = p.next;
            }
            p.next = node;
            node.prev = p;
            node.next = null;
        }else {
            Node<K,V> nodeHead = null;
            Node<K,V> nodeTail = null;
            for (int i = 0; i < defaultValue; i++){
                if (table[i] != null){
                    nodeHead = table[i];
                    nodeTail = table[i];
                    break;
                }
            }
            if (count==size){ // 缓冲满
                while (nodeHead.prev!=null){
                    nodeHead = nodeHead.prev;
                }
                nodeHead.next.prev = null;
                nodeHead.next = null;
                count--;
            }
            node = newNode(key,value);
            if (nodeTail == null){
                nodeTail = node;
                table[(defaultValue-1)&hash] = node;
                count++;
                return;
            }
            while (nodeTail.next!=null){
                nodeTail = nodeTail.next;
            }
            nodeTail.next = node;
            node.prev = nodeTail;
            node.next = null;
            table[(defaultValue-1)&hash] = node;
            count++;
        }
    }

    public boolean find(K key){
        int hash = hash(key);
        Node<K,V> node = table[(defaultValue-1)&hash];
        if (node == null) return false;
        else {
            if (node.next==null) return true; // 尾部时
            if (node.prev==null){ // 头部时
                node.next.prev = null;
            }else {// 中间时
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            Node<K,V> p = node;
            while (p.next!=null){
                p = p.next;
            }
            p.next = node;
            node.prev = p;
            node.next = null;
            return true;
        }
    }

    Node<K,V> newNode(K key, V value) {
        return new Node<>(key, value);
    }


    static class Node<K,V>{
        final K key;
        V value;
        Node<K,V> hnext;
        Node<K,V> prev;
        Node<K,V> next;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.hnext = null;
            this.prev = null;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LRUQuestion<String,String> lruQuestion = new LRUQuestion<>(10);
        lruQuestion.put("test001","test001");
        lruQuestion.put("test002","test002");
        lruQuestion.put("test003","test002");
        lruQuestion.put("test004","test002");
        lruQuestion.put("test005","test002");
        lruQuestion.put("test006","test002");
        lruQuestion.put("test007","test002");
        lruQuestion.put("test008","test002");
        lruQuestion.put("test009","test002");
        lruQuestion.put("test010","test002");
        System.out.println(lruQuestion.find("test001"));
        System.out.println(lruQuestion.find("test003"));
        System.out.println(lruQuestion.find("test011"));
    }
}
