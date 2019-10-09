package com.lzq.study.algorithms.second;

public class MaxSumQuestion {

    public static void main(String[] args) {

    }

    private int maxSubSum1(int[] arr){
        int maxSum = 0;
        int length = arr.length;
        for (int i=0; i < length; i++){
            for (int j=i; j < length; j++){
                int thisSum = 0;
                for (int k=i;k<=j;k++){
                    thisSum += arr[k];
                }
                if (thisSum > maxSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    private int maxSubSum2(int[] arr){
        int maxSum = 0;
        int length = arr.length;
        for (int i=0; i < length; i++){
            int thisSum = 0;
            for (int j=i; j < length; j++){
                thisSum += arr[j];
                if (thisSum > maxSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    private int maxSubSum3(int[] arr,int left, int right)
    {
        if (left==right) return arr[left] >0 ? arr[left] : 0;
        int center = (left + right)/2;
        int leftMaxSum = maxSubSum3(arr,left,center);
        int rightMaxSum = maxSubSum3(arr,center+1,right);

        int maxLeftSum =0, leftSum = 0;
        for (int i=center;i>=left;i--){
            leftSum += arr[i];
            if (leftSum > maxLeftSum){
                maxLeftSum = leftSum;
            }
        }

        int maxRightSum = 0, rightSum = 0;
        for (int i = center+1; i <= right; i++){
            rightSum += arr[i];
            if (rightSum > maxRightSum){
                maxRightSum = rightSum;
            }
        }

        int maxSum = maxLeftSum + maxRightSum;

        return Math.max(maxSum,Math.max(leftMaxSum,rightMaxSum));
    }
}
