package com.lzq.study.geektime.test.link;

public class CircleLinkOperate {
    private SinglyNode head;
    private SinglyNode tail;
    private int size;

    public CircleLinkOperate(){
        head = tail = null;
        size = 0;
    }

    public void addHead(SinglyNode node){
        if (size == 0){
            node.next = node;
            head = tail = node;
            return;
        }
        tail.next = node;
        node.next = head;
        head = node;
        size++;
    }

    public void delHead(){
        if (size == 0) return;
        if (size == 1){
            size--;
            head = tail = null;
            return;
        }
        head = head.next;
        tail.next = head;
        size--;
    }

    public void addTail(SinglyNode node){
        if (size == 0){
            node.next = node;
            head = tail = node;
            return;
        }
        node.next = head;
        tail.next = node;
        tail = node;
        size++;
    }
}
