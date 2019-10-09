package com.lzq.study.geektime.test.stack;

import org.apache.commons.lang3.StringUtils;

public class ArrayStack {
    private String[] items;
    private int n;
    private int count;

    public ArrayStack(int n){
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    public boolean push(String data){
        if (this.count >= n) return false;
        this.items[count] = data;
        this.count++;
        return true;
    }

    public String pop(){
        if (this.count == 0) return StringUtils.EMPTY;
        String popStr = this.items[count-1];
        this.count--;
        return popStr;
    }
}
