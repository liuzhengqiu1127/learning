package com.lzq.study.geektime.test.link;

public class LinkOperation {
    private SinglyNode head;
    private DoubleNode head2;

    //单链表反转
    public SinglyNode reverse(SinglyNode head)
    {
        SinglyNode node = null;
        SinglyNode prev = null;
        while (head != null){
            node = head;
            head = head.next;
            node.next = prev;
            prev = node;
        }
        return node;
    }
    /**
     * 判断是否为循环链表
     * @param head
     * @return
     */
    public boolean isCircleLink(SinglyNode head) {
        SinglyNode fast = head.next;
        SinglyNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    /**
     * 合并两条有序链表
     * @param node1
     * @param node2
     */
    public SinglyNode mergeSortLink(SinglyNode node1, SinglyNode node2)
    {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        SinglyNode head = null;
        SinglyNode p = node1;
        SinglyNode q = node2;
        if (p.data > q.data){
            head = node2;
            q = q.next;
        }
        else {
            head = node1;
            p = p.next;
        }

        SinglyNode r = head;
        while (q!=null&&p!=null){
            if (p.data > q.data) {
                r.next = q;
                q = q.next;
            }else {
                r.next = p;
                p = p.next;
            }
            r = r.next;
        }

        if (q!=null) r.next = q;
        if (p!=null) r.next = p;
        return head;
    }


    public SinglyNode getMid(SinglyNode head)
    {
        SinglyNode fast = head;
        SinglyNode slow = head;
        while (fast!=null&&fast.next!=null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //双向链表增加
    public void insert2(int data){
        DoubleNode doubleNode = new DoubleNode(data);
        if (head2 == null) {
            head2 = doubleNode;
            return;
        }
        DoubleNode node = head2;
        if (node.data > data){
            doubleNode.last = node;
            node.prev = doubleNode;
            head2 = doubleNode;
        }
        boolean flag = false;
        DoubleNode prev = null;
        while (node != null){
            if (node.data > data){
                node.prev.last = doubleNode;
                doubleNode.prev = node.prev;
                doubleNode.last = node;
                node.prev = doubleNode;
                flag = true;
                break;
            }
            prev = node;
            node = node.last;
        }
        if (!flag){
            prev.last = doubleNode;
            doubleNode.prev = prev;
        }
    }

    //单链表增加
    public void insert(int data){
        SinglyNode singlyNode = new SinglyNode(data);
        if (head == null){
            head = singlyNode;
            return;
        }
        SinglyNode node = head;
        if (node.data > data){
            singlyNode.next = node;
            head = singlyNode;
            return;
        }
        SinglyNode prev = null;
        boolean flag = false;
        while (node != null){
            if (node.data > data){
                prev.next = singlyNode;
                singlyNode.next = node;
                flag = true;
                break;
            }
            prev = node;
            node = node.next;
        }

        if (!flag){
            prev.next = singlyNode;
        }
    }

    //单链表删除
    public void delete(int data)
    {
        SinglyNode node = head;
        if (node.data == data){
            head = node.next;
            return;
        }
        SinglyNode prev = null;
        while (node != null){
            if (node.data == data){
                prev.next = node.next;
                break;
            }
            prev = node;
            node = node.next;
        }
    }


}
