package com.lzq.study.lettcode.weekly.twoone;

import org.junit.Test;

/**
 * Created by liuzhengqiu on 2020/11/22.
 */
public class TwoOneSix {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder wordBuilder1 = new StringBuilder();
        StringBuilder wordBuilder2 = new StringBuilder();
        for (String word : word1) wordBuilder1.append(word);
        for (String word : word2) wordBuilder2.append(word);
        return wordBuilder1.toString().equals(wordBuilder2.toString());
    }

    public String getSmallestString(int n, int k) {
        char[] ch = new char[n];
        int minValue = n + 25;
        for (int i=n-1; i>=0; i--){
            if (k >= minValue){
                ch[i] = 'z';
                k -= 26;
                minValue--;
            }else{
                ch[i] = (char)('a'+(k-i-1));
                i--;
                while (i>=0){
                    ch[i] = 'a';
                    i--;
                }
                break;
            }
        }
        return new String(ch);
    }

    /**
     * 删除第i项之后:
     *      oddSum = oddSum(i项之前) + evenSum(i项之后)
     *      evenSum = evenSum(i项之前) + oddSum(i项之后)
     * @param nums
     * @return
     */
    public int waysToMakeFair(int[] nums) {
        int len = nums.length;
        int[] preOddSum = new int[len + 1];
        int[] preEvenSum = new int[len + 1];
        preEvenSum[0] = 0;
        preOddSum[0] = 0;

        /**
         * 计算每删除i之前的数据项求和
         * i为奇数项时 preOddSum[i] = preOddSum[i-1]; preEvenSum[i] = preEvenSum[i-1] + nums[i-1];
         */
        for (int i = 1; i <= len; i++){
            if (i % 2 == 1){
                preOddSum[i] = preOddSum[i-1] + nums[i-1];
                preEvenSum[i] = preEvenSum[i-1];
            }else {
                preOddSum[i] = preOddSum[i-1];
                preEvenSum[i] = preEvenSum[i-1] + nums[i-1];
            }
        }

        /**
         * 1,2,3,4,5,6,7,8
         * 1,2,4,5,6,7,8
         * i = 2 时对应为3
         *  oddSum = preOdd[3] + preEven[8] - preEven[3]
         *  evenSum = preEven[3] + preOdd[8] - preOdd[3] - nums[2]
         */
        int ans = 0;
        for (int i = 0; i < len; i++){
            int oddSum, evenSum ;
            if (i % 2 == 0){
                oddSum = preOddSum[i] + preEvenSum[len] - preEvenSum[i];
                evenSum = preEvenSum[i] + preOddSum[len] - preOddSum[i] - nums[i];
            }else{
                oddSum = preOddSum[i] + preEvenSum[len] - preEvenSum[i] - nums[i];
                evenSum = preEvenSum[i] + preOddSum[len] - preOddSum[i];
            }
            if (oddSum == evenSum) ans++;
        }
        return ans;
    }


    @Test
    public void test(){
        System.out.println(getSmallestString(5,73));
    }
}
