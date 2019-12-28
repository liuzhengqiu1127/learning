package com.lzq.study.lettcode;

import com.lzq.study.lettcode.common.ListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuzhengqiu on 2019/12/28.
 */
public class OneSixSevenCompetition {

    @Test
    public void test001(){
        int[] inputs = {1,0,0,1,0,0,1,1,1,0,0,0,0,0,0};
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i=1; i < inputs.length;i++){
            ListNode node = new ListNode(inputs[i]);
            temp.next = node;
            temp = node;
        }
        Assert.assertTrue(getDecimalValue(head)==18880);
    }

    public int getDecimalValue(ListNode head) {
        List<Integer> integerList = new LinkedList<>();
        while (head != null){
            integerList.add(head.val);
            head = head.next;
        }
        int len = integerList.size();
        int sum = 0;
        for (int i = 0; i < len; i++){
            sum += integerList.get(i) * Math.pow(2,(len-1-i));
        }
        return sum;
    }


    public List<Integer> sequentialDigits(int low, int high) {
       String base = new String("123456789");
       List<Integer> result = new ArrayList<>();
       int lowLen = String.valueOf(low).length();
       int highLen = String.valueOf(high).length();
       for (int j = 0; j <= 9; j++){
           for (int i = lowLen; i <= highLen; i++){
               if (i + j > 9) continue;
               String value = base.substring(j,j + i);
               if (Integer.valueOf(value) > high || Integer.valueOf(value) < low) continue;
               result.add(Integer.valueOf(value));
           }
       }
        Collections.sort(result);
        return result;
    }

}
