package com.lzq.study.geektime.structure.linkstruct;

public class LinkMain {

    public static void print(ListNode head){
        while (head != null){
            System.out.println(head.value);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode listNode2 = new ListNode(20);
        ListNode listNode3 = new ListNode(31);
        ListNode listNode4 = new ListNode(41);
        ListNode listNode5 = new ListNode(11);
        head.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode3;

        LinkOperation operation = new LinkOperation();
        System.out.println(operation.checkLinkRing(head));

    }

}
