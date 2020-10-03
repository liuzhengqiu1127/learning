package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.*;

public class ThirtySix {
    private Integer big;
    private Integer medium;
    private Integer small;

    public void ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    public boolean addCar(int carType) {
        if (carType == 1 && this.big >0 ){
            this.big--;
            return true;
        }
        if (carType == 2 && this.medium > 0){
            this.medium--;
            return true;
        }
        if (carType == 3 && this.small > 0){
            this.small--;
            return true;
        }
        return false;
    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String,List<String>> recordMap = new HashMap<>();
        int length = keyName.length;
        for (int index = 0; index < length; index++){
            String name = keyName[index];
            String time = keyTime[index];
            if (recordMap.containsKey(name)){
                recordMap.get(name).add(time);
            }else {
                List<String> list = new ArrayList<>();
                list.add(time);
                recordMap.put(name, list);
            }
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String,List<String>> entry : recordMap.entrySet()){
            if (entry.getValue().size() >= 3 && checkOk(entry.getValue())) {
                result.add(entry.getKey());
            }
        }
        Collections.sort(result);
        return result;
    }
    private boolean checkOk(List<String> timeList){
        Collections.sort(timeList);
        int length = timeList.size();
        for (int index=0; index <= length-3; index++){
            String startTime = timeList.get(index);
            String endTime = timeList.get(index+2);
            long juli = (Integer.parseInt(endTime.split(":")[0])-Integer.parseInt(startTime.split(":")[0])) * 60 +
                    (Integer.parseInt(endTime.split(":")[1])-Integer.parseInt(startTime.split(":")[1]));
            if (juli <= 60) return true;
        }
        return false;
    }

    @Test
    public void test(){

    }
}
