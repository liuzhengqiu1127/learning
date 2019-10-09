package com.lzq.study.geektime.structure.array;

public class ArrayQueue {
    private String[] items;
    private int n;
    private int head;
    private int tail;

    public ArrayQueue(int n){
        this.items = new String[n];
        this.n = n;
    }

    public boolean push(String item){
        if (tail == n) {
            if (head == 0) return false;
            else {
                for (int i = head; i < tail; ++i){
                    items[i-head] = items[i];
                }
                head = 0;
                tail -= head;
            }
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    public String pop(){
        if (head == tail) return null;
        String item = items[head];
        ++head;
        return item;
    }
}
