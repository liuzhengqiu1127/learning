package com.lzq.study.lettcode.weekly.twotwo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.PriorityQueue;

/**
 * Created by liuzhengqiu on 2020/12/29.
 */
public class TwoTwoOne {
    public boolean halvesAreAlike(String s) {
        int length = s.length();
        int half = (length >> 1);
        String before = s.substring(0,half).toLowerCase();
        String after = s.substring(half,length).toLowerCase();
        int bLen = before.length(), aLen=after.length();
        String target = "aeiou";
        for (Character character : target.toCharArray()){
            before = before.replaceAll(""+character,"");
            after = after.replaceAll(""+character,"");
        }
        return (bLen-before.length()) == (aLen-after.length());
    }

    @Test
    public void test01(){
        Assert.assertTrue(halvesAreAlike("book"));
        Assert.assertTrue(!halvesAreAlike("testbook"));
        Assert.assertTrue(!halvesAreAlike("MerryChristmas"));
        Assert.assertTrue(halvesAreAlike("AbCdEfGh"));
    }

    /**
     * 添加优先级队列
     * 放入的时候：苹果的数量，过期时间
     * 出来的时候：1，时间是否过期；2，苹果是否吃完
     * @param apples
     * @param days
     * @return
     */
    public int eatenApples(int[] apples, int[] days){
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] < o2[1] ? -1 : 1); //这个是关键点
        //优先级队列根据过期时间排序，先过期的放在前面
        int eatNumber = 0;
        for (int i = 0; i < apples.length || queue.size() > 0; i++){
            while (!queue.isEmpty()){
                int[] apple = queue.peek();
                if (apple[1] <= i){//如果过期要去掉
                    queue.poll();
                }else {
                    break;
                }
            }
            if (i < apples.length && apples[i] > 0){
                queue.add(new int[]{apples[i],days[i]+i}); //添加苹果数和过期时间
            }
            int[] ap = queue.peek();
            if (Objects.nonNull(ap) && ap[0] > 0){
                eatNumber++;
                ap[0] -= 1;
                if (ap[0] == 0){ // 如果吃完也要去掉
                    queue.poll();
                }
            }
        }
        return eatNumber;
    }

    @Test
    public void test02(){
        Assert.assertTrue(eatenApples(new int[]{1,2,3,5,2},new int[]{3,2,1,4,2})==7);
        Assert.assertTrue(eatenApples(new int[]{3,0,0,0,0,2},new int[]{3,0,0,0,0,2})==5);
    }

    public int[] findBall(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] ans = new int[col];
        for (int i = 0; i < col; i++) {
            ans[i] = out(grid, row, col, i, 0);
        }
        return ans;
    }

    private int out(int[][] grid, int row, int col, int x, int y){
        if (y == row) return x;
        if (x == 0 && grid[y][x] == -1) return -1;
        if (x == col-1 && grid[y][x] == 1) return -1;
        if (grid[y][x] == 1 && grid[y][x+1]==-1) return -1;
        if (grid[y][x]==-1&&grid[y][x-1]==1) return  -1;
        return out(grid,row,col,x+grid[y][x],y+1);
    }

    @Test
    public void test03(){
        int[] result = findBall(new int[][]{{1,1,1,-1,-1},{1,1,1,-1,-1},{-1,-1,-1,1,1},{1,1,1,1,-1},{-1,-1,-1,-1,-1}});
        Assert.assertTrue(result[0]==1&&result[1]==-1&&result[2]==-1&&result[3]==-1&&result[4]==-1);
    }

}
