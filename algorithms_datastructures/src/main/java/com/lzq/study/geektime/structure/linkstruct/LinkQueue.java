package com.lzq.study.geektime.structure.linkstruct;

public class LinkQueue {
    private ListNode head = null;
    private ListNode tail = null;

    public void push(int value){
        if (tail == null) {
            ListNode newNode = new ListNode(value, null);
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
    }

    public int pop(){
        if (head == null) return 0;
        int value = head.value;
        head = head.next;
        if (head == null){
            tail = null;
        }
        return value;
    }
}
