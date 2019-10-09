package com.lzq.study.geektime.test.stack;

public class LinkStack {

    private Node head;

    public boolean isEmpty(){
        if (head==null) return true;
        return false;
    }

    public void push(String data){
        Node node = new Node(data);
        if (head == null) {
            head = node;
            return;
        }
        Node p = head;
        while (p.next != null) p = p.next;
        p.next = node;
    }

    public String pop(){
        if (head == null) return null;
        Node p = head;
        Node prev = null;
        while (p.next != null){
            prev = p;
            p = p.next;
        }
        String data = p.data;
        if (prev == null) head = null;
        else {
            prev.next = null;
        }
        return data;
    }

    public class Node{
        private String data;
        private Node next;
        public Node(String data){
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LinkStack linkStack = new LinkStack();
        linkStack.push("123");
        linkStack.push("234");
        linkStack.push("245");
        System.out.println(linkStack.pop() + "," + linkStack.pop());
    }
}
