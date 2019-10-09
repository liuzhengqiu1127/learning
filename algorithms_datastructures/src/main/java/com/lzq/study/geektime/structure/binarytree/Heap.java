package com.lzq.study.geektime.structure.binarytree;

public class Heap {
    private int[] a;
    private int n;
    private int count;

    public Heap(int capacity)
    {
        this.a = new int[capacity + 1];
        this.n = capacity;
        this.count = 0;
    }

    public void insert(int data)
    {
        if (count >= n) return;
        count++;
        this.a[count] = data;
        int i = count;
        while (i/2>0&&a[i/2]<a[i]){
            int tmp = a[i];
            a[i] = a[i/2];
            a[i/2] = tmp;
            i = i / 2;
        }
    }

    public void removeMax(){
        if (count == 0) return ;
        this.a[1] = a[count];
        --count;
        heapify(a,count,1);
    }

    private void heapify(int[] a, int n, int i){
        while (true){
            int maxPos = i;
            if (2 * i <= n && a[2*i] > a[i]) maxPos = 2 * i;
            if (2 * i + 1 <= n && a[2*i+1] > a[i]) maxPos = 2*i + 1;
            if (maxPos == i) break;
            int tmp = a[i];
            a[i] = a[maxPos];
            a[maxPos] = tmp;
            i = maxPos;
        }
    }
}
