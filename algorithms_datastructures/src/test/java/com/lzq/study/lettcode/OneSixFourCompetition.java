package com.lzq.study.lettcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class OneSixFourCompetition {

    @Test
    public void test001(){
        int[][] points = {{1,1},{3,4},{-1,0}};
        Assert.assertTrue(minTimeToVisitAllPoints(points) == 7);
        int[][] points2 = {{3,2},{-2,2}};
        Assert.assertTrue(minTimeToVisitAllPoints(points2) == 5);
    }

    public int minTimeToVisitAllPoints(int[][] points) {
        int startX = points[0][0];
        int startY = points[0][1];
        int allStep = 0;
        for (int i = 1 ; i < points.length; i++){
            int endX = points[i][0];
            int endY = points[i][1];
            allStep += getTime(startX,startY,endX,endY);
            startX = endX;
            startY = endY;
        }
        return allStep;
    }
    private int getTime(int startX, int startY, int endX, int endY)
    {
        int step = 0;
        while (startX<endX&&startY<endY){
            step++;
            startX++;
            startY++;
        }
        while (startX<endX&&startY>endY){
            step++;
            startX++;
            startY--;
        }
        while (startX>endX&&startY<endY){
            step++;
            startX--;
            startY++;
        }
        while (startX>endX&&startY>endY){
            step++;
            startX--;
            startY--;
        }
        while (startX < endX){
            step++;
            startX++;
        }
        while (startX > endX){
            step++;
            startX--;
        }
        while (startY < endY){
            step++;
            startY++;
        }
        while (startY > endY){
            step++;
            startY--;
        }
        return step;
    }

    @Test
    public void test002(){
        int[][] grid = {{1,0},{0,1}};
        Assert.assertTrue(countServers(grid)==0);
        int[][] grid1 = {{1,0},{1,1}};
        Assert.assertTrue(countServers(grid1)==3);
        int[][] grid2 = {{1,1,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,1}};
        Assert.assertTrue(countServers(grid2)==4);
    }

    public int countServers(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int allNumber = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (isOk(i,j,grid)){
                    allNumber++;
                }
            }
        }
        return allNumber;
    }
    private boolean isOk(int row, int column, int[][] grid){
        if (grid[row][column]==0) return false;
        for (int i=0; i < grid.length; i++){
            if (grid[i][column]==1&&row!=i){
                return true;
            }
        }
        for (int j=0; j < grid[0].length; j++){
            if (grid[row][j] == 1 && j != column){
                return true;
            }
        }
        return false;
    }
    @Test
    public void test003(){
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        System.out.println(suggestedProducts(products,"mouse"));
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        int size = 3;
        List<List<String>> result = new ArrayList<>();
        for (int i=0; i < searchWord.length(); i++){
            List<String> everyResult = new ArrayList<>();
            for (String product : products){
                if (product.startsWith(searchWord.substring(0,i+1))){
                    everyResult.add(product);
                }
                if (size == 0){
                    break;
                }
                if (everyResult.size() >= size) break;
            }
            size = everyResult.size();
            result.add(everyResult);
        }
        return result;
    }

}
