package com.lzq.study.lettcode.weekly.onenine;

import org.junit.Test;

import java.util.*;

public class OneNineFour {

    public int xorOperation(int n, int start) {
        int result = 0;
        for (int i=0; i < n; i++){
            result ^= (start + 2 * i);
        }
        return result;
    }

    public String[] getFolderNames(String[] names) {
        String[] result = new String[names.length];
        int i = 0;
        Map<String,Integer> tempMap = new HashMap<>();
        for (String name : names){
            if (tempMap.containsKey(name)){
                Integer j = tempMap.get(name);
                j++;
                tempMap.put(name, j);
                while (tempMap.containsKey(name + "("+j+")")){
                    j++;
                }
                String last = name + "("+j+")";
                result[i] = last;
                tempMap.put(last, 0);
            }else {
                tempMap.put(name, 0);
                result[i] = name;
            }
            i++;
        }
        return result;
    }

    public int[] avoidFlood(int[] rains) {
        Map<Integer,List<Integer>> records = new HashMap<>();
        for (int index = 0; index < rains.length; index++){
            if (records.containsKey(rains[index])){
                records.get(rains[index]).add(index);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(index);
                records.put(rains[index],list);
            }
        }

        int[] result = new int[rains.length];
        List<Integer> hasFloodList = new LinkedList<>();
        int i  = 0;
        for (int rain : rains){
            if (rain == 0){
                if (hasFloodList.isEmpty()){
                    result[i] = 1;
                }else{
                    Integer delRain = getFirstAppear(hasFloodList,records,i);
                    if (delRain == -2){
                        result[i] = 1;
                    }else{
                        result[i] = delRain;
                        hasFloodList.remove(delRain);
                    }
                }
            }else{
                if (hasFloodList.contains(rain)){
                    return new int[0];
                }else{
                    result[i] = -1;
                    hasFloodList.add(rain);
                }
            }
            i++;
        }
        return result;
    }
    private int getFirstAppear(List<Integer> hasFloodList,Map<Integer,List<Integer>> records, int index){
        int minIndex = Integer.MAX_VALUE;
        int result = -2;
        for (Integer integer : hasFloodList){
            Integer first = records.get(integer).stream().filter(temp -> temp.intValue() > index).findFirst().orElse(null);
            if (first == null) continue;
            if (first < minIndex){
                minIndex = first;
                result = integer;
            }
        }
        return result;
    }

    @Test
    public void test(){
        int[] test = avoidFlood(new int[]{1,0,2,0});
        for (int t : test) System.out.println(t);
    }
}
