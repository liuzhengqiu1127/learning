package com.lzq.study.lettcode.weekly.oneeight;

public class CustomStack {
    private int[] stackArray;
    private int maxSize;
    private int index;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.stackArray = new int[maxSize];
        this.index = -1;
    }

    public void push(int x) {
        if (this.index>=this.maxSize-1){
            return;
        }
        index++;
        stackArray[index] = x;
    }

    public int pop() {
        if (this.index<0){
            return -1;
        }
        int popData =  stackArray[index];
        this.index--;
        return popData;
    }

    public void increment(int k, int val) {
        if (k<=0) return;
        int limitIndex = (this.index >= (k-1)) ? (k-1) : (this.index);
        for (int i=0; i<=limitIndex; i++){
            stackArray[i] += val;
        }
    }

    public static void main(String[] args) {
        CustomStack obj = new CustomStack(3);
        obj.push(1);
        obj.push(2);
        int param_2 = obj.pop();
        System.out.println(param_2);
        obj.increment(2,1);
        System.out.println(obj.pop());
    }
}
