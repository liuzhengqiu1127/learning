package com.lzq.study.geektime.test.queue;

public class CircleQueue {
    private String[] items;
    private int n;
    private int head;
    private int tail;

    public CircleQueue(int capacity){
        items = new String[capacity];
        this.n = capacity;
    }

    public boolean enqueue(String item){
        if ((tail + 1) % n == head) return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    public String dequeue(){
        if (head == tail) return null;
        String ret = items[head];
        head = (head + 1)%n;
        return ret;
    }


}
