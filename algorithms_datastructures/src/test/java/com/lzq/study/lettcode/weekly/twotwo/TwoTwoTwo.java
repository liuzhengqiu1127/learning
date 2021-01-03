package com.lzq.study.lettcode.weekly.twotwo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by liuzhengqiu on 2021/1/3.
 */
public class TwoTwoTwo {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes,(boxType1,boxType2)->{
            return boxType2[1] - boxType1[1];
        });
        int result = 0;
        for (int i=0; i<boxTypes.length; i++){
            int number = boxTypes[i][0];
            int size = boxTypes[i][1];
            if (truckSize >= number){
                truckSize -= number;
                result += number*size;
            }else{
                result += truckSize*size;
                break;
            }
        }
        return result;
    }

    @Test
    public void test01(){
        Assert.assertTrue(maximumUnits(new int[][]{{1,3},{2,2},{3,1}},4)==8);
        Assert.assertTrue(maximumUnits(new int[][]{{5,10},{2,5},{4,7},{3,9}},10)==91);
    }

    public int countPairs(int[] deliciousness) {
        int result = 0;
        int res = 1000000007;
        double[] tmp = new double[21];
        for (int i=0; i<=20; i++){
            tmp[i] = Math.pow(2,i);
        }
        Arrays.sort(deliciousness);
        for (int i = 0; i < deliciousness.length; i++){
            for (int j=0;j<=20;j++){
                result += getNumberCount(deliciousness,i+1,tmp[j]-deliciousness[i]);
                result %= res;
            }
        }
        return result;
    }
    private int getNumberCount(int[] deliciousness,int start,double number){
        int left = start;
        int right = deliciousness.length-1;
        int count = 0;
        while (left <= right){
            int mid = (left+right)>>1;
            if (deliciousness[mid] < number) left = mid+1;
            else if (deliciousness[mid] > number) right = mid - 1;
            else {
                count++;
                for (int index=mid+1;index<deliciousness.length;index++){
                    if (deliciousness[index]==number) count++;
                    else break;
                }
                for (int index=mid-1;index>=start;index--){
                    if (deliciousness[index]==number) count++;
                    else break;
                }
                break;
            }
        }
        return count;
    }

    @Test
    public void test02(){
        Assert.assertTrue(countPairs(new int[]{1,3,5,7,9})==4);
        Assert.assertTrue(countPairs(new int[]{1,1,1,3,3,3,7})==15);
    }

}
