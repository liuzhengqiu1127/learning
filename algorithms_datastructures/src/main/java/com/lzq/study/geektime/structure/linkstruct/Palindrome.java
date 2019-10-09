package com.lzq.study.geektime.structure.linkstruct;

public class Palindrome {

    /**
     *  分为奇数和偶数两种情况
     *  奇数：slow 正好到正中间这个节点 所以需要 slow = slow -> next
     *  偶数：
     * @param head
     * @return
     */

    public boolean isPalindrome(ListNode head){
        if (head == null || head.next == null){
            return true;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        if (fast != null){
            slow = slow.next;
        }
        while (slow != null){
            if (slow.value != prev.value){
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }
        return true;
    }
}
