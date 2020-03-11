package com.lzq.study.lettcode.interview;

import org.junit.Test;

public class One {

    @Test
    public void test01()
    {
        System.out.println(removeDuplicates("azxxzy"));
        System.out.println(removeDuplicates("abbaca"));
    }

    public int firstBadVersion(int n) {
        if (n == 1) { // 边界判断
            return 1;
        }
        int low = 1;
        int high = n;
        while (low <= high){
            int mid = low + ((high - low)>>1); // mid设置要注意int + int相加超过int的范围
            if (isBadVersion(mid)){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }
    private boolean isBadVersion(int n){
        return n==1;
    }

    public String removeDuplicates(String S) {
        int len = S.length();
        if (len<2){
            return S;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (; i<len-1; i++){
            if (S.charAt(i)==S.charAt(i+1)){
                i++;
            }else{
                stringBuilder.append(S.charAt(i));
            }
        }
        if (i==len-1){
            stringBuilder.append(S.charAt(i));
        }
        if (stringBuilder.length() == len){
            return S;
        }
        return removeDuplicates(stringBuilder.toString());
    }
}
