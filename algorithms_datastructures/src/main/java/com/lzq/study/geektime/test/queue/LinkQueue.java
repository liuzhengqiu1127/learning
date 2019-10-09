package com.lzq.study.geektime.test.queue;

public class LinkQueue {

    private Node head;
    private Node tail;

    public void push(String data){
        Node node = new Node(data);
        if (tail == null){
            head = tail = node;
            return;
        }
        tail.next = node;
        tail = tail.next;
    }

    public String pop(){
        if (head == null) return null;
        String data = head.data;
        head = head.next;
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
        LinkQueue linkQueue = new LinkQueue();
        linkQueue.push("123");
        linkQueue.push("234");
        System.out.println(linkQueue.pop());
    }
}
