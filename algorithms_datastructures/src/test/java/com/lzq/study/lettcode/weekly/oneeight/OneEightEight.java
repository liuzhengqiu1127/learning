package com.lzq.study.lettcode.weekly.oneeight;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhengqiu on 2020/5/10.
 */
public class OneEightEight {

    @Test
    public void test01(){
        System.out.println(buildArray(new int[]{1,3},3));
        System.out.println(buildArray(new int[]{1,2,3},3));
        System.out.println(buildArray(new int[]{1,2},4));
        System.out.println(buildArray(new int[]{2,3,4},4));
    }

    public List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        int j = 0;
        int len = target.length;
        for (int i=1; i<=n; i++){
            if (j == len){
                break;
            }
            if (target[j] == i){
                result.add("Push");
                j++;
            }else{
                result.add("Push");
                result.add("Pop");
            }
        }
        return result;
    }

    @Test
    public void test02(){
        Assert.assertTrue(countTriplets(new int[]{2,3,1,6,7})==4);
        Assert.assertTrue(countTriplets(new int[]{1,1,1,1,1})==10);
        Assert.assertTrue(countTriplets(new int[]{2,3})==0);
        Assert.assertTrue(countTriplets(new int[]{1,3,5,7,9})==3);
        Assert.assertTrue(countTriplets(new int[]{7,11,12,9,5,2,7,17,22})==8);
    }

    public int countTriplets(int[] arr) {
        if (arr.length<2) return 0;
        int len = arr.length;
        int result = 0;
        for (int i=0; i<len-1; i++){
            for (int j=i+1; j<len; j++){
                for (int k=j; k<len; k++){
                    if (check(i,j,k,arr)){
                        result++;
                    }
                }
            }
        }
        return result;
    }
    private boolean check(int i, int j, int k, int[] arr){
        int a = arr[i], b = arr[j];
        for (int m=i+1; m<j; m++){
            a ^= arr[m];
        }
        for (int m=j+1; m<=k; m++){
            b ^= arr[m];
        }
        if (a==b) return true;
        return false;
    }

    @Test
    public void test03(){

    }
    
}
