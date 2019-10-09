package com.lzq.study.geektime.structure.linkstruct;

public class LinkOperation {

    /**
     * 单链表反转
     * @param head
     */
    public ListNode reversal(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode node = null;
        while (head != null){
            node = head;//记录前一个节点
            head = head.next;
            node.next = prev;
            prev = node;
        }
        return node;
    }

    /**
     * 删除倒数第n个节点
     * 不考虑 节点个数 < n 的情况
     * @param node
     * @param n
     */
    public ListNode deleteNode(ListNode node,int n){
        ListNode head = node;//记录头节点
        int total = 0;
        while (node != null){
            total++;
            node = node.next;
        }
        if (total < n) return head;

        ListNode reversalHead = reversal(head);
        ListNode reversalH = reversalHead;//记录反转头节点
        ListNode prev = null;
        int number = 1;
        while (reversalH != null){
            if (number == n){
                if (total == 1) {
                    return null;
                }
                else if (number == 1) {
                    return reversal(reversalH.next);
                }
                else {
                    prev.next = reversalH.next;
                }
            }
            prev = reversalH;
            reversalH = reversalH.next;
            number++;
        }
        return reversal(reversalHead);
    }

    /**
     * 两条有序链表合并
     * @param first
     * @param second
     * @return
     */
    public ListNode mergeLink(ListNode first,ListNode second){
        if (first==null&&second!=null) return second;
        if (first!=null&&second==null) return first;
        if (first==null&&second==null) return null;
        ListNode head = second;
        ListNode prev = null;
        while (first!=null){ // 整个思路就是遍历第一条链表，然后把第一条链表的元素不停的插入到第二条链表中
            int value = first.value;
            boolean flag = false;
            ListNode node = new ListNode(value);
            while (second!=null){
                if (value < second.value && head == second){ // 元素是最小的元素时需要插入到首节点
                    node.next = second;
                    head = node;
                    flag = true;
                    break;
                }
                if (value < second.value ){ // 元素是中间元素时 需要插入到中间节点
                    prev.next = node;
                    node.next = second;
                    flag = true;
                    break;
                }
                prev = second;
                second = second.next;
            }
            if (!flag){ // 元素时最大元素时 需要插入到末尾节点
                prev.next = node;
            }

            second = head;
            first = first.next;
        }

        return head;
    }

    /**
     * 求链表中间结点
     * @param head
     * @return
     */
    public ListNode getMidLink(ListNode head){
        if (head==null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next; // 快节点 每次都走慢节点的两倍就可以
            slow = slow.next; //慢节点
        }
        return new ListNode(slow.value);
    }

    /**
     * 检测链表中环路
     * @param head
     * @return
     */
    public boolean checkLinkRing(ListNode head){
        while(head!=null&&head.value!=0){
            head.value = 0;
            head = head.next;
        }

        while (head!=null){
            if (head.value==1) return true;
            head.value = 1;
            head = head.next;
        }
        return false;
    }

    /**
     * 单链表反转
     * @param list
     * @return
     */
    public static ListNode reverse(ListNode list){
        ListNode curr = list, pre = null;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    //检测环路
    public static boolean checkCircle(ListNode list){
        if (list == null) return false;
        ListNode fast = list.next;
        ListNode slow = list;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) return true;
        }
        return false;
    }

    //有序链表合并
    public static ListNode mergeSortedLists(ListNode la, ListNode lb){
        if (la == null) return lb;
        if (lb == null) return la;

        ListNode p = la;
        ListNode q = lb;
        ListNode head;
        if (p.value < q.value){ // 获取头部指针
            head = p;
            p = p.next;
        }else {
            head = q;
            q = q.next;
        }
        ListNode r = head;

        while (p != null && q != null){
            if (p.value < q.value){
                r.next = p;
                p = p .next;
            }else {
                r.next = q;
                q = q.next;
            }
            r = r.next;
        }

        if (p != null){
            r.next = p;
        }else {
            r.next = q;
        }
        return head;
    }

    //删除倒数第K个节点
    public static ListNode deleteLastKth(ListNode list, int k){
        ListNode fast = list;
        int i = 1;
        while (fast != null && i < k){
            fast = fast.next;
            ++i;
        }

        if (fast == null) return list; // fast指向第k个元素

        ListNode slow = list; // slow = len - k
        ListNode prev = null;
        while (fast.next != null){
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null){ //正好删除第一个节点
            list = list.next;
        }else {
            prev.next = slow.next;
        }
        return list;
    }

}
