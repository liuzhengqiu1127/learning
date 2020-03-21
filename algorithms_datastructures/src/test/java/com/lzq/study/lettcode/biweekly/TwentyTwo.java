package com.lzq.study.lettcode.biweekly;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TwentyTwo {
    @Test
    public void test01(){
        Assert.assertTrue(findTheDistanceValue(new int[]{4,5,8},new int[]{10,9,1,8},2)==2);
        Assert.assertTrue(findTheDistanceValue(new int[]{1,4,2,3},new int[]{-4,-3,6,10,20,30},3)==2);
        Assert.assertTrue(findTheDistanceValue(new int[]{2,1,100,3},new int[]{-5,-2,10,-3,7},6)==1);
    }
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int sum = 0;
        for (int arr : arr1){
            boolean flag = false;
            for (int ary : arr2){
                if (Math.abs(arr-ary)<=d){
                    flag = true;
                    break;
                }
            }
            if (!flag){
                sum++;
            }
        }
        return sum;
    }

    @Test
    public void test02(){
        Assert.assertTrue(maxNumberOfFamilies(3,new int[][]{{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}})==4);
        Assert.assertTrue(maxNumberOfFamilies(2,new int[][]{{2,1},{1,8},{2,6}})==2);
        Assert.assertTrue(maxNumberOfFamilies(4,new int[][]{{4,3},{1,4},{4,6},{1,7}})==4);
    }
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int count = 0;
        Map<Integer,List<Integer>> listMap = new HashMap<>();
        for (int i = 0; i<reservedSeats.length; i++){
            if (listMap.containsKey(reservedSeats[i][0])){
                listMap.get(reservedSeats[i][0]).add(reservedSeats[i][1]);
            }else {
                List<Integer> integerList = new ArrayList<>();
                integerList.add(reservedSeats[i][1]);
                listMap.put(reservedSeats[i][0],integerList);
                count++;
            }
        }
        int sum = (n - count)*2;
        return sum + getAlineFamilies(listMap);
    }

    private int getAlineFamilies(Map<Integer,List<Integer>> map){
        int sum = 0;
        for (Map.Entry<Integer,List<Integer>> entry : map.entrySet()){
            List<Integer> reserved = entry.getValue();
            int[][] possible = new int[][]{{2,3,4,5},{4,5,6,7},{6,7,8,9}};
            int every = 0;
            for (int k=0; k<possible.length;k++){
                int m=0;
                for (;m<4;m++){
                    if (reserved.contains(possible[k][m])){
                        break;
                    }
                }
                if (m==4) {
                    every++;
                    k++;
                }
            }
            sum += every;
        }
        return sum;
    }

    @Test
    public void test03(){
        Assert.assertTrue(getKth(12,15,2)==13);
        Assert.assertTrue(getKth(1,1,1)==1);
        Assert.assertTrue(getKth(7,11,4)==7);
        Assert.assertTrue(getKth(10,20,5)==13);
        Assert.assertTrue(getKth(1,1000,777)==570);
    }
    public int getKth(int lo, int hi, int k) {
        int rows = (hi-lo)+1;
        int[][] numbers = new int[rows][2];
        int i = 0;
        for (int key=lo;key<=hi;key++){
            int weight = getWeight(key,0);
            numbers[i][0] = key;
            numbers[i][1] = weight;
            i++;
        }
        Arrays.sort(numbers, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1]==o2[1]){
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        return numbers[k-1][0];
    }
    private int getWeight(int number, int weight){
        if (number==1){
            return weight;
        }
        if (number%2==0) return getWeight(number/2,weight+1);
        else return getWeight(3*number+1,weight+1);
    }

    @Test
    public void test04(){

    }
}
