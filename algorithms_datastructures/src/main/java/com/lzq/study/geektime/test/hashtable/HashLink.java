package com.lzq.study.geektime.test.hashtable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 参考HashMap 如何使用链表法解决哈希冲突的问题
 * @param <K>
 * @param <V>
 */
public class HashLink<K,V> {
    transient HashLink.Node<K,V>[] table;

    public HashLink(){
        table = new Node[16];
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public V get(Object key){
        int hash = hash(key);
        int n = 16;
        HashLink.Node<K,V> e = table[(n - 1) & hash];
        if (e == null) return  null;
        do{
            if (e.hash == hash && (e.key == key || (key != null && key.equals(e.key))))
                return e.value;
        }while ((e = e.next) != null); // 也是遍历单链表解决哈希冲突
        return null;
    }

    public V put(K key, V value){
        int hash = hash(key);
        int n=16, i;
        HashLink.Node<K,V> p = table[i = (n - 1) & hash];
        if (p == null){ // 如果没有hash冲突
            table[i] = newNode(hash,key,value,null);
            return null;
        }else { // 如果存在hash冲突
            if (p.hash == hash && (p.key == key || (key != null && key.equals(p.key)))) // hash 和 key 都相等
            {
                return p.value;
            }else {
                p.next = newNode(hash,key,value,null); // 单链表解决哈希冲突
                return value;
            }
        }
    }


    HashLink.Node<K,V> newNode(int hash, K key, V value, HashLink.Node<K,V> next) {
        return new HashLink.Node<>(hash, key, value, next);
    }


    static class Node<K,V>{
        final int hash;
        final K key;
        V value;
        HashLink.Node<K,V> next; // 链表法解决

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        HashLink<String,String> hashLink = new HashLink<>();
        hashLink.put("liuzhengqiu","23");
        System.out.println(hashLink.get("liuzhengqiu"));
        System.out.println(hashLink.get("liuzhengqiu1"));
    }

}
