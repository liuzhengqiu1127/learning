package com.lzq.study.lettcode.weekly.oneeight;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OneEightTwo {

    public int findLucky(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0; i<arr.length; i++){
            int count = map.getOrDefault(arr[i],0) + 1;
            map.put(arr[i],count);
        }
        int result = -1;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            if (entry.getKey().intValue()==entry.getValue().intValue())
            {
                result = Math.max(entry.getKey().intValue(),result);
            }
        }
        return result;
    }

    public int numTeams(int[] rating) {
        int len = rating.length;
        if (len < 3) return 0;
        int sum = 0;
        for (int i=0; i<len-2; i++){
            int number1 = rating[i];
            for (int j=i+1; j<len-1; j++){
                int number2 = rating[j];
                if (number1==number2) continue;
                for (int k=j+1; k<len; k++){
                    int number3 = rating[k];
                    if ((number1 > number2 && number2 > number3) || (number1 < number2 && number2 < number3)){
                        sum++;
                    }
                }
            }
        }
        return sum;
    }

    private Map<String,String> map;

    public void UndergroundSystem() {
        map = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        map.put(id+"In"+t,stationName);
    }

    public void checkOut(int id, String stationName, int t) {
        map.put(id+"Out"+t,stationName);
    }

    public double getAverageTime(String startStation, String endStation) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String,String> entry : map.entrySet()){
            String value = entry.getValue();
            if (value.contains(startStation)||value.contains(endStation)){
                list.add(entry.getKey());
            }
        }
        List<String> checkInLst = list.stream().filter(s -> s.contains("In")).collect(Collectors.toList());
        List<String> checkOutLst = list.stream().filter(s -> s.contains("Out")).collect(Collectors.toList());
        double sum = 0;
        int count = 0;
        for (String checkIn : checkInLst){
            String id = checkIn.split("In")[0];
            int start = Integer.valueOf(checkIn.split("In")[1]);
            String checkOut = checkOutLst.stream().filter(str -> str.contains(id+"Out")).findAny().orElse(null);
            if (checkOut!=null){
                int end = Integer.valueOf(checkOut.split("Out")[1]);
                sum += (end-start);
                count++;
            }
        }
        return sum/count;
    }

    /**
     *
     * @param n
     * @param s1
     * @param s2
     * @param evil
     * @return
     */
    public int findGoodStrings(int n, String s1, String s2, String evil)
    {
        return 0;
    }

    @Test
    public void test(){
        System.out.println("abd".compareTo("abc"));
    }

}
