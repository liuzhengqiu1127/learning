package com.lzq.study.geektime.structure.linkstruct;

public class LinkStack {
    private ListNode head;
    private int n;
    private int count;

    public LinkStack(int n){
        this.n = n;
        this.count = 0;
        head = null;
    }

    public boolean push(int value){
        if (count == n) return false;
        if (head == null){
            head = new ListNode(value);
        }
        else {
            ListNode node = new ListNode(value);
            node.next = head;
            this.head = node;
        }
        count++;
        return true;
    }

    public int pop(){
        if (count == 0) return 0;
        int pop = this.head.value;
        this.head = this.head.next;
        count--;
        return pop;
    }
}
