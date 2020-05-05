package com.lzq.study.lettcode.weekly.oneseven;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class OneSevenNineCompetition {

    @Test
    public void test01(){

    }
    public String generateTheString(int n) {
        char one = 'a';
        char two = 'b';
        if (n % 2 != 0){
            StringBuilder result = new StringBuilder();
            for (int i=0; i<n; i++){
                result.append(one);
            }
            return result.toString();
        }else{
            StringBuilder result = new StringBuilder();
            for (int i=0; i<(n-1); i++){
                result.append(one);
            }
            result.append(two);
            return result.toString();
        }
    }

    @Test
    public void test02(){
        Assert.assertTrue(numTimesAllBlue(new int[]{2,1,3,5,4})==3);
        Assert.assertTrue(numTimesAllBlue(new int[]{3,2,4,1,5})==2);
        Assert.assertTrue(numTimesAllBlue(new int[]{4,1,2,3})==1);
        Assert.assertTrue(numTimesAllBlue(new int[]{2,1,4,3,6,5})==3);
        Assert.assertTrue(numTimesAllBlue(new int[]{1,2,3,4,5,6})==6);
    }
    public int numTimesAllBlue(int[] light) {
        int allLightNumber = light.length;
        boolean[] isOn = new boolean[allLightNumber+1];
        int maxNumber = 0;
        int result = 0;
        for (int i=0; i < allLightNumber; i++){
            int number = light[i];
            isOn[number] = true;
            if (number > maxNumber) maxNumber = number;
            if (checkBeforeOn(isOn, maxNumber)){
                result++;
            }
        }
        return result;
    }
    private boolean checkBeforeOn(boolean[] isOn, int maxNumber){
        for (int i=1; i<maxNumber; i++){
            if (isOn[i]==false) return false;
        }
        return true;
    }

    @Test
    public void test03(){
        Assert.assertTrue(numOfMinutes(1,0,new int[]{-1},new int[]{0})==0);
        Assert.assertTrue(numOfMinutes(4,2,new int[]{3,3,-1,2},new int[]{0,0,162,914})==1076);
        Assert.assertTrue(numOfMinutes(6,2,new int[]{2,2,-1,2,2,2},new int[]{0,0,1,0,0,0})==1);
        Assert.assertTrue(numOfMinutes(7,6,new int[]{1,2,3,4,5,6,-1},new int[]{0,6,5,4,3,2,1})==21);
        Assert.assertTrue(numOfMinutes(15,0,new int[]{-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6},new int[]{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0})==3);
    }
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if (n == 1) return informTime[0];
        allMinutes = new ArrayList<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i=0; i<manager.length; i++){
            List<Integer> list = map.getOrDefault(manager[i],new ArrayList<>());
            list.add(i);
            map.put(manager[i],list);
        }
        List<Integer> childIds = map.get(headID);
        getMinutes(childIds,map,informTime,informTime[headID]);
        Collections.sort(allMinutes);
        return allMinutes.get(allMinutes.size()-1);
    }
    private List<Integer> allMinutes = null;
    private void getMinutes(List<Integer> childIds, Map<Integer,List<Integer>> map, int[] informTime, int sum)
    {
        for (Integer childId : childIds){
            if (map.containsKey(childId)){
                getMinutes(map.get(childId),map,informTime,sum+informTime[childId]);
            }else{
                allMinutes.add(sum);
            }
        }
    }

    @Test
    public void test04(){
        System.out.println(frogPosition(1,new int[][]{},1,1));
//        System.out.println(frogPosition(7,new int[][]{{2,1},{3,1},{7,1},{4,2},{2,6},{3,5}},2,4));
    }
    public double frogPosition(int n, int[][] edges, int t, int target) {
        if (n==1&&target==1) return 1;
        Map<Integer,List<Integer>> map = new HashMap<>();
        int rows = edges.length;
        for (int i=0; i<rows; i++){
            List<Integer> close1List = map.getOrDefault(edges[i][0],new ArrayList<>());
            close1List.add(edges[i][1]);
            map.put(edges[i][0],close1List);
            List<Integer> close2List = map.getOrDefault(edges[i][1],new ArrayList<>());
            close2List.add(edges[i][0]);
            map.put(edges[i][1],close2List);
        }
        doubleList = new ArrayList<>();
        visited = new ArrayList<>();
        visited.add(1);
        frog(1,map,t,target,1.0);
        return doubleList.stream().mapToDouble(Double::valueOf).sum();
    }
    private List<Double> doubleList = null;
    private List<Integer> visited = null;
    public void frog(int start, Map<Integer,List<Integer>> map, int t, int target, double probability){
        if (t < 0){
            return;
        }

        List<Integer> nextList = map.get(start);
        nextList = nextList.stream()
                .filter(integer -> !visited.contains(integer))
                .collect(Collectors.toList());

        if (start==target&&(nextList.isEmpty()||t==0)){
            doubleList.add(probability);
            return;
        }

        if (start==target&&t>0&&!nextList.isEmpty()){
            return;
        }

        if (start!=target&&nextList.isEmpty()){
            return;
        }

        for (Integer next : nextList){
            visited.add(next);
            frog(next,map,t-1,target,probability*(1.0/nextList.size()));
        }
    }
}
