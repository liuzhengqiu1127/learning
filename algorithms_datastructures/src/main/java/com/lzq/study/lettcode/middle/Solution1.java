package com.lzq.study.lettcode.middle;


import com.lzq.study.lettcode.common.ListNode;

import javax.sound.midi.Soundbank;

/**
 * Created by liuzhengqiu on 2019/9/25.
 */
public class Solution1 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) return head;
        ListNode rHead = reverse(head);
        if (n == 1){
            return reverse(rHead.next);
        }
        int count = 1;
        ListNode resultHead = rHead;
        ListNode prev = null;
        while (rHead != null){
            if (count == n){
                prev.next = rHead.next;
                break;
            }
            prev = rHead;
            rHead = rHead.next;
            count++;
        }
        return reverse(resultHead);
    }
    private ListNode reverse(ListNode head){
        ListNode node = null;
        ListNode prev = null;
        while (head != null){
            node = head;
            head = head.next;
            node.next = prev;
            prev = node;
        }
        return node;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head;
        if (l1.val > l2.val){
            head = l2;
            l2 = l2.next;
        }else {
            head = l1;
            l1 = l1.next;
        }

        ListNode tmp = head;
        while( l1 != null && l2 != null){
            if (l1.val > l2.val){
                tmp.next = l2;
                l2 = l2.next;
            }else {
                tmp.next = l1;
                l1 = l1.next;
            }
            tmp = tmp.next;
        }

        if (l1 != null) tmp.next = l1;
        if (l2 != null) tmp.next = l2;
        return head;

    }

    /**
     * 交换节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
    public ListNode swapPairs2(ListNode head) {
        ListNode prev = new ListNode(0);
        prev.next = head;
        ListNode temp = prev;
        while (temp.next != null && temp.next.next != null){
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return prev.next;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == 1) return Integer.MIN_VALUE;
        int result = 0;
        long absDividend = Math.abs((long)dividend);
        long absDivisor = Math.abs((long)divisor);
        while (true){
            if (Math.abs(absDividend) < absDivisor){
                break;
            }
            absDividend = absDividend - absDivisor;
            result++;
        }
        boolean condition = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        if (condition) return result;
        else return 0-result;
    }
    public int divide1(int dividend, int divisor){
        boolean sign = (dividend > 0) ^ (divisor > 0);
        long as_dividend = Math.abs((long) dividend);
        long as_divisor = Math.abs((long) divisor);
        long count = 0;
        while (as_dividend >= as_divisor){
            count += 1;
            as_divisor <<= 1;
        }
        long result = 0;
        while (count > 0){
            count -= 1;
            as_divisor >>= 1;
            if (as_divisor <= as_dividend){
                result += (long) 1 << count;
                as_dividend -= as_divisor;
            }
        }
        if (sign) result = - result;
        if (result > Integer.MAX_VALUE  || result < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        else return (int)result;
    }
}
