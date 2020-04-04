package com.lzq.study.lettcode.biweekly;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TwentyThree {

    public int countLargestGroup(int n) {
        if (n < 10) return n;
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        for (int i=1; i<=n; i++){
            int key = getSum(i);
            int value = map.getOrDefault(key,0) + 1;
            if (max < value){
                max = value;
            }
            map.put(key,value);
        }
        int finalMax = max;
        return (int) map.entrySet().stream().filter(m->m.getValue().intValue()== finalMax).count();
    }
    private int getSum(int number){
        int sum = 0;
        while (number > 0){
            sum += (number%10);
            number = number/10;
        }
        return sum;
    }
    @Test
    public void test(){
        Assert.assertTrue(countLargestGroup(15)==6);
    }

    public boolean canConstruct(String s, int k) {
        if (s.length() < k) {
            return false;
        } else if (s.length() == k) {
            return true;
        }else {
            List<Character> characterList = new ArrayList<>();
            for (Character character : s.toCharArray()){
                if (characterList.contains(character)){
                    characterList.remove(character);
                }else {
                    characterList.add(character);
                }
            }
            if (characterList.size() <= k) return true;
            else return false;
        }
    }
    @Test
    public void test01(){
        Assert.assertTrue(canConstruct("annabelle",2));
        Assert.assertTrue(!canConstruct("leetcode",3));
        Assert.assertTrue(canConstruct("true",4));
        Assert.assertTrue(canConstruct("yzyzyzyzyzyzyzy",2));
    }

    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        int x_min = x_center - radius;
        int x_max = x_center + radius;
        int y_min = y_center - radius;
        int y_max = y_center + radius;
        if (x1 > x_max) return false;
        if (x2 < x_min) return false;
        if (y1 > y_max) return false;
        if (y2 < y_min) return false;
        if ( x1 >= x_center && y1 >= y_center && (x1-x_center)*(x1-x_center)+(y1-y_center)*(y1-y_center) > radius*radius ){
            return false;
        }
        if (x1 >= x_center && y2 <= y_center && (x1-x_center)*(x1-x_center)+(y2-y_center)*(y2-y_center)> radius*radius ){
            return false;
        }
        if (x2 <= x_center && y1 >= y_center && (x2-x_center)*(x2-x_center)+(y1-y_center)*(y1-y_center) > radius*radius){
            return false;
        }
        if (x2 <= x_center && y2 <= y_center && (x2-x_center)*(x2-x_center)+(y2-y_center)*(y2-y_center) > radius*radius){
            return false;
        }
        return true;
    }

    @Test
    public void test02(){
        Assert.assertTrue(checkOverlap(1415,807,-784,-733,623,-533,1005));
    }

    private int invalid = Integer.MIN_VALUE;
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        if (satisfaction[satisfaction.length-1]<=0) return 0;
        judge(satisfaction,0,0,satisfaction.length-1,0);
        List<Integer> result = new ArrayList<>();
        for (int i=0; i<satisfaction.length; i++){
            if (satisfaction[i] == invalid) continue;
            result.add(satisfaction[i]);
        }
        int sum = 0;
        int k = 1;
        for (Integer integer : result){
            sum += integer * k;
            k++;
        }
        return sum;
    }
    private void judge(int[] satisfaction,int beginIndex, int firstIndex, int lastIndex, int sum){
        if (sum <= 0){
            for (int i=beginIndex; i<firstIndex; i++){
                satisfaction[i] = invalid;
            }
            sum = 0;
            beginIndex = firstIndex;
            lastIndex = satisfaction.length-1;
        }
        if (satisfaction[firstIndex] >= 0) return;
        if (firstIndex >= lastIndex) return;
        judge(satisfaction,beginIndex, firstIndex+1,lastIndex-1,sum+satisfaction[firstIndex]+satisfaction[lastIndex]);
    }
    @Test
    public void test03(){
//        Assert.assertTrue(maxSatisfaction(new int[]{-1,-8,0,5,-9})==14);
//        Assert.assertTrue(maxSatisfaction(new int[]{4,3,2})==20);
//        Assert.assertTrue(maxSatisfaction(new int[]{-1,-4,-5})==0);
        Assert.assertTrue(maxSatisfaction(new int[]{10,7,-7,-10,9,5,5,-5,-10,8,-8})==286);
    }
}
