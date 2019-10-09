package com.lzq.study.geektime.test.queue;

public class ArrayQueue {
    private String[] items;
    private int n;
    private int head;
    private int tail;

    public ArrayQueue(int n){
        this.n = n;
        this.items = new String[n];
        this.head = this.tail = 0;
    }

    public boolean push(String data){
        if (tail == n) return false;
        items[tail] = data;
        this.tail ++;
        return true;
    }

    public String pop(){
        if (head == tail) return null;
        String str = items[head];
        ++this.head;
        return str;
    }
}
