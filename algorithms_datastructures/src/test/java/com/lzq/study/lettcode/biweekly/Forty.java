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

    /**
     *  index = i时， delLeft[i] 表示左边最少删除多少个
     *  delLeft[i] = min ( delLeft[i], delLeft[j] + i - j - 1)
     *  delRight[i] = min ( delRight[i], delRight[j] + j - i - 1)
     * @param nums
     * @return
     */
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] delLeft = new int[n];
        int[] delRight = new int[n];
        for (int i = 0; i < n; i++){
            delLeft[i] = i;
            delRight[i] = n - i - 1;
        }

        for (int i = 0; i < n; i++){
            for (int j = i-1; j>=0; j--){
                if (nums[j] < nums[i]){ // 左边
                    delLeft[i] = Integer.min(delLeft[i],delLeft[j]+i-j-1);
                }
            }
        }
        for (int i = n - 1; i >= 0; i--){
            for (int j = i + 1; j < n; j++){
                if (nums[j] < nums[i]){
                    delRight[i] = Integer.min(delRight[i], delRight[j]+j-i-1);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++){
            ans = Integer.min(ans, delLeft[i] + delRight[i]);
        }
        return ans;
    }


    @Test
    public void test(){
        System.out.println(minimumMountainRemovals(new int[]{4,3,2,1,1,2,3,1}));
    }

}
