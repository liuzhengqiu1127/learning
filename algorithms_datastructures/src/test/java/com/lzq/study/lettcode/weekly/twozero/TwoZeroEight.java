package com.lzq.study.lettcode.weekly.twozero;

import org.junit.Test;

import java.util.*;

public class TwoZeroEight {

    public int minOperations(String[] logs) {
        int home = 0;
        for (String log : logs){
            if (log.equalsIgnoreCase("./")){
                continue;
            }else if (log.equalsIgnoreCase("../")){
                home = (home > 0 ? home-1 : home);
            }else{
                home++;
            }
        }
        return home;
    }

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        if (4 * boardingCost - runningCost <= 0) return -1;
        List<Integer> record = new ArrayList<>();
        int remain = 0;
        int profit = 0;
        for (int customer : customers){
            int current = remain + customer;
            if (current >= 4){
                profit += (4*boardingCost-runningCost);
                remain = current - 4;
            }else{
                profit += (current*boardingCost - runningCost);
                remain = 0;
            }
            record.add(profit);
        }
        while (remain > 0){
            if (remain >= 4){
                profit += (4*boardingCost - runningCost);
                remain = remain - 4;
            }else {
                profit += (remain*boardingCost - runningCost);
                remain = 0;
            }
            record.add(profit);
        }
        int max = 0;
        int count = 0;
        int result = 0;
        for (int red : record){
            count++;
            if (red > max){
                result = count;
                max = red;
            }
        }
        return result == 0 ? - 1 : result;
    }

    @Test
    public void test(){
        int[] customers = {24,0,32,1,39,33,36,40,22,40,5,43,31,27,4,0,11,17,5,10,11,19,35,50,20,18,49,4,46,8,50,0,4,19,12,19,37,10,0,27,45,1,23,46,48,26,12,34,19,25,49,1,36,14,43,31,7,14,46,0,1,23,40};
        System.out.println(minOperationsMaxProfit(customers,22,53));
    }

    String kingName;
    Map<String, List<String>> mp = new HashMap();
    Set <String> set = new HashSet();
    public void ThroneInheritance(String kingName) {
        this.kingName = kingName;
        mp.put(kingName, new LinkedList());
    }

    public void birth(String parentName, String childName) {
        mp.get(parentName).add(childName);
        mp.put(childName, new LinkedList());
    }

    public void death(String name) {
        set.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> res = new LinkedList();
        dfs(res, kingName);
        return res;
    }

    public void dfs(List<String> res, String name) {
        if(!set.contains(name)) {
            res.add(name);
        }
        for(String subName : mp.get(name)) {
            dfs(res, subName);
        }
    }

}
