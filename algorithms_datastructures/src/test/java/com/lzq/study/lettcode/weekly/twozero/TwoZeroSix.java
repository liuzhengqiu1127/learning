package com.lzq.study.lettcode.weekly.twozero;

import org.junit.Test;

import java.util.*;

public class TwoZeroSix {
    public int numSpecial(int[][] mat) {
        int rows = mat.length;
        int columns = mat[0].length;
        int result = 0;
        int count = 0;
        int tmpRow = 0, tmpColumn = 0;
        for (int row=0;row<rows;row++){
            for (int column=0;column<columns;column++){
                if (mat[row][column] == 1) {
                    tmpRow = row;
                    tmpColumn = column;
                    count++;
                }
                if (count > 1) break;
            }
            if (count == 1){
                for (int tmp=0;tmp<rows;tmp++){
                    if (mat[tmp][tmpColumn]==1&&tmp!=tmpRow){
                        count++;
                        break;
                    }
                }
            }
            if (count == 1){
                result++;
            }
            count = 0;
        }
        return result;
    }
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[] pairArr = new int[n];
        for (int i = 0; i < n/2; i++){
            pairArr[pairs[i][0]] = pairs[i][1];
            pairArr[pairs[i][1]] = pairs[i][0];
        }
        int unhappy = 0;
        for (int index = 0; index < n; index++){
            int[] friends = preferences[index];//所有朋友
            int mostFri = pairArr[index];//配对朋友
            boolean flag = false;
            for (int friend : friends){
                if (friend != mostFri){//如果不是配对朋友

                    int otherMostFri = pairArr[friend]; // friend对index亲密层度大于配对朋友
                    int[] otherFriends = preferences[friend];
                    for (int otherFriend : otherFriends){
                        if (otherFriend == index){
                            flag = true;
                            break;
                        }
                        if (otherFriend == otherMostFri){
                            break;
                        }
                    }
                    if (flag == true){
                        unhappy++;
                        break;
                    }

                }else{
                    break;
                }
            }
        }
        return unhappy;
    }

    public int minCostConnectPoints(int[][] points) {
        Queue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1.split(",")[0])-Integer.parseInt(o2.split(",")[0]);
            }
        });
        List<Integer> visited = new ArrayList<>();
        for (int i=0; i<points.length;i++) visited.add(i);
        int res = 0;
        pq.add("0,0");
        while (!visited.isEmpty()){
            String temp = pq.poll();
            Integer dis = Integer.parseInt(temp.split(",")[0]);
            Integer now = Integer.parseInt(temp.split(",")[1]);
            if (!visited.contains(now)){
                continue;
            }
            visited.remove(now);
            res += dis;
            for (int i : visited){
                pq.add(getDistance(points[now],points[i])+","+i);
            }
        }
        return res;
    }
    private int getDistance(int[] point1, int[] point2){
        return Math.abs(point1[0]-point2[0]) + Math.abs(point1[1] - point2[1]);
    }

    @Test
    public void test(){
        System.out.println(minCostConnectPoints(new int[][]{{2,-3},{-17,-8},{13,8},{-17,-15}}));
    }
}
