package com.lzq.study.geektime.structure.array;

public class ArrayStack {
    private String[] items;
    private int n;
    private int count;

    public ArrayStack(int n){
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    public boolean push(String item)
    {
        if (n == count) return false;
        items[count] = item;
        ++count;
        return true;
    }

    public String pop()
    {
        if (count == 0) return null;
        String pop = items[count - 1];
        count--;
        return pop;
    }
}
