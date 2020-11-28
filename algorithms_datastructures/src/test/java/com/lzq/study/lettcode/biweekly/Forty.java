package com.lzq.study.lettcode.biweekly;

import com.lzq.study.lettcode.common.ListNode;
import org.junit.Test;

/**
 * Created by liuzhengqiu on 2020/11/28.
 */
public class Forty {

    public int maxRepeating(String sequence, String word) {
        int sum = 0;
        StringBuilder temp = new StringBuilder();
        temp.append(word);
        while (sequence.contains(temp.toString())){
            temp.append(word);
            sum++;
        }
        return sum;
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int index = 0;
        ListNode head = list1;
        ListNode begin = list1;
        while (index < a){
            begin = list1;
            list1 = list1.next;
            index++;
        }
        while (index <= b){
            list1 = list1.next;
            index++;
        }
        begin.next = list2;
        while (list2.next != null){
            list2 = list2.next;
        }
        list2.next = list1;
        return head;
    }

    public int minimumMountainRemovals(int[] nums) {
        int deleteNumber = Integer.MAX_VALUE;

        for (int i=1; i < nums.length-1; i++){
            int deleteCount = 0;
            for (int j = 0; j < i; j++){
                
            }


        }

        return deleteNumber;
    }

    @Test
    public void test(){

    }

}
