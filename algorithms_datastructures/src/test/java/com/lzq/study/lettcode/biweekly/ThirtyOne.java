package com.lzq.study.lettcode.biweekly;

public class ThirtyOne {

    public int countOdds(int low, int high) {
        if (low % 2 == 0 && high % 2 == 0){
            return (high - low)/2;
        }
        return (high - low)/2 + 1;
    }

    public int numOfSubarrays(int[] arr) {
        int mod = 1000000007;
        int even = 1, odd = 0;
        long res = 0;
        int sum = 0;
        for (int number : arr){
            sum += number;
            res += (sum%2==0?odd:even);
            if (sum%2==0) even++;
            else odd++;
        }
        return (int) (res % mod);
    }

}
