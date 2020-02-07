package com.lzq.study.lettcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by liuzhengqiu on 2020/2/7.
 */
public class OneSevenFourCompetition {

    @Test
    public void test01(){

    }
    public int[] kWeakestRows(int[][] mat, int k) {
        int rows = mat.length;
        int columns = mat[0].length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < rows; i++){
            int sum = 0;
            for (int j=0; j<columns; j++){
                if (mat[i][j]==0) break;
                sum += mat[i][j];
            }
            map.put(i,sum);
        }
        List<Map.Entry<Integer,Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if (o1.getValue().compareTo(o2.getValue())==0) return o1.getKey().compareTo(o2.getKey());
                else return o1.getValue().compareTo(o2.getValue());
            }
        });
        int[] result = new int[k];
        int count = 0;
        for (Map.Entry<Integer,Integer> entry : entryList){
            if (count < k){
                result[count] = entry.getKey();
                count++;
            }else {
                break;
            }
        }
        return result;
    }

    @Test
    public void test02(){
        Assert.assertTrue(minSetSize(new int[]{1,9})==1);
    }
    public int minSetSize(int[] arr) {
        Arrays.sort(arr);
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length;i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        List<Map.Entry<Integer,Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int half = (arr.length%2==0)?(arr.length/2):(arr.length/2+1);
        int result = 0;
        int sum = 0;
        for (Map.Entry<Integer,Integer> entry : entryList){
            result++;
            sum += entry.getValue();
            if (sum >= half){
                break;
            }
        }
        return result;
    }
    @Test
    public void test03(){

    }
    

    @Test
    public void test04(){

    }
}
