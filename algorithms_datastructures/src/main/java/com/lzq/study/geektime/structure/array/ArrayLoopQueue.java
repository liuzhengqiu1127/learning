package com.lzq.study.geektime.structure.array;

public class ArrayLoopQueue {
    private String[] items;
    private int n;
    private int size;
    private int head;
    private int tail;
    public ArrayLoopQueue(int n){
        this.n = n;
        items = new String[n];
        this.size = 0;
    }

    public boolean push(String item){
        if (size == n) return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        size++;
        return true;
    }

    public String pop(){
        if (size == 0) return null;
        String ret = items[head];
        head = (head + 1) % n;
        size--;
        return ret;
    }
}
