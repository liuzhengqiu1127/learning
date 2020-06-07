package com.lzq.study.lettcode.weekly.onenine;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class OneNineTwo {

    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        for (int i=0;i<n;i++){
            result[i*2] = nums[i];
            result[i*2+1] = nums[n+i];
        }
        return result;
    }

    public int[] getStrongest(int[] arr, int k) {
        int[] result = new int[k];
        int length = arr.length;
        Arrays.sort(arr);
        int betweenNumber = arr[(length-1)/2];
        int startIndex = 0;
        int endIndex = 0;
        int count = 0;
        while (count < k){
            int first = arr[startIndex];
            int last = arr[length-1-endIndex];
            boolean condition1 = Math.abs(first-betweenNumber) > Math.abs(last - betweenNumber);
            boolean condition2 = Math.abs(first-betweenNumber) == Math.abs(last - betweenNumber) && first > last;
            if (condition1 || condition2){
                result[count] = first;
                startIndex++;
            }else {
                result[count] = last;
                endIndex++;
            }
            count++;
        }
        return result;
    }

    private String homepageUrl;
    private Stack<String> forwardStack;
    private Stack<String> backStack;
    public void BrowserHistory(String homepage) {
        this.homepageUrl = homepage;
        forwardStack = new Stack<>();
        backStack = new Stack<>();
    }
    public void visit(String url) {
        backStack.push(homepageUrl);
        this.homepageUrl = url;
        forwardStack.clear();
    }
    public String back(int steps) {
        for (int i = 0; i < steps; i++){
            if (backStack.isEmpty()) break;
            this.forwardStack.push(this.homepageUrl);
            this.homepageUrl = backStack.pop();
        }
        return this.homepageUrl;
    }
    public String forward(int steps) {
        for (int i=0; i < steps; i++){
            if (forwardStack.isEmpty()) break;
            this.backStack.push(this.homepageUrl);
            this.homepageUrl = forwardStack.pop();
        }
        return this.homepageUrl;
    }

    

    @Test
    public void test(){
        int[] test = shuffle(new int[]{1,2,3,4,4,3,2,1},4);
        for (int i=0;i<test.length;i++) System.out.println(test[i]);
    }

}
