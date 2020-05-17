package com.lzq.study.lettcode.weekly.oneeight;

import org.junit.Test;

import java.util.*;

/**
 * Created by liuzhengqiu on 2020/5/17.
 */
public class OneEightNine {
    @Test
    public void test01(){

    }
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int result = 0;
        int len = startTime.length;
        for (int i=0; i < len; i++){
            if (queryTime >= startTime[i] && queryTime <= endTime[i]) result++;
        }
        return result;
    }

    @Test
    public void test02(){
        System.out.println(arrangeWords("Leetcode is cool"));
        System.out.println(arrangeWords("Keep calm and code on"));
        System.out.println(arrangeWords("To be or not to be"));
    }
    public String arrangeWords(String text) {
        String[] arrStr = text.split(" ");
        Map<Integer,String> map = new TreeMap<>();
        for (String str : arrStr){
            Integer len = str.length();
            if (map.containsKey(len)){
                String update = map.get(len) + " " + str.toLowerCase();
                map.put(len, update);
            }else {
                map.put(len,str.toLowerCase());
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer,String> entry : map.entrySet()){
            stringBuilder.append(entry.getValue());
            stringBuilder.append(" ");
        }
        String result = stringBuilder.toString();
        String first = (result.charAt(0) + "").toUpperCase();
        return first + result.substring(1,result.length()-1);
    }

    @Test
    public void test03(){
        List<List<String>> favoriteCompanies = new ArrayList<>();
    }
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> result = new ArrayList<>();
        int length = favoriteCompanies.size();
        for (int i=0; i<length; i++){
            int iLen = favoriteCompanies.get(i).size();
            boolean flag = true;
            for (int j=0; j<length; j++){
                if (j == i) continue;
                int jLen = favoriteCompanies.get(j).size();
                if (jLen > iLen && !isOk(favoriteCompanies.get(i),favoriteCompanies.get(j))){
                    flag = false;
                    break;
                }
            }
            if (flag) result.add(i);
        }
        return result;
    }
    private boolean isOk(List<String> iCompanies, List<String> jCompanies){
        return iCompanies.stream().filter(company -> !jCompanies.contains(company)).findAny().isPresent();
    }

    @Test
    public void test04(){

    }
    public int numPoints(int[][] points, int r) {
        int ans = 1;
        for (int i=0; i < points.length; i++){
            for (int j = i+1; j < points.length; j++){
                if (dist(new Point(1.0*points[i][0], 1.0*points[i][1]),new Point(1.0*points[j][0], 1.0*points[j][1])) > 2.0 * r) continue;
                Point center = getCircleCenter(new Point(1.0*points[i][0], 1.0*points[i][1]),new Point(1.0*points[j][0], 1.0*points[j][1]), r);
                int cnt = 0;
                for (int k=0; k < points.length; k++){
                    if (dist(center,new Point(1.0*points[k][0],1.0*points[k][1])) < 1.0*r + eps) cnt++;
                    ans = Math.max(ans,cnt);
                }
            }
        }
        return ans;
    }
    private class Point{
        double x, y;
        Point(){}
        Point(double x,double y){
            this.x = x;
            this.y = y;
        }
    }
    private static final double eps = 1e-10;
    private double dist(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.x - p2.x,2)+Math.pow(p1.y-p2.y,2));
    }
    private Point getCircleCenter(Point p1, Point p2, double r){ //求圆心这个是关键点
        Point mid = new Point((p1.x + p2.x)/2, (p1.y + p2.y)/2);
        double angle = Math.atan2(p1.x-p2.x, p2.y-p1.y);
        double d = Math.sqrt(r*r-Math.pow(dist(p1, mid),2));
        return new Point(mid.x + d*Math.cos(angle), mid.y + d*Math.sin(angle));
    }
}
