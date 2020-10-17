package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by liuzhengqiu on 2020/10/17.
 */
public class ThirtySeven {

    public double trimMean(int[] arr) {
        int length = arr.length;
        int number = length * 5 / 100;
        if (number == 0) {
            return Arrays.stream(arr).sum()*1.0/length;
        }
        Arrays.sort(arr);
        for (int i=0; i < number; i++){
            arr[i] = 0;
            arr[length-1-i] = 0;
        }
        return Arrays.stream(arr).sum()*1.0/(length-2*number);
    }

    public int[] bestCoordinate(int[][] towers, int radius) {
        int[] result = new int[2];
        int rows = towers.length;
        int max = 0;
        for (int i = 0; i < rows; i++){
            int value = towers[i][2];
            for (int j=0; j<rows; j++){
                if (j == i) continue;
                int distance = (towers[i][0] - towers[j][0])*(towers[i][0] - towers[j][0])
                        + (towers[i][1] - towers[j][1])*(towers[i][1] - towers[j][1]);
                if (radius >= Math.sqrt(distance)){
                    value += (int)((towers[j][2]/(1+Math.sqrt(distance))));
                }
            }
            if (value > max){
                max = value;
                result[0] = towers[i][0];
                result[1] = towers[i][1];
            }
            if (value == max){
                if (towers[i][0] < result[0] || (towers[i][0] == result[0] && towers[i][1] < result[1])){
                    result[0] = towers[i][0];
                    result[1] = towers[i][1];
                }
            }
        }
        return result;
    }

    @Test
    public void test(){
        int[][] towers = new int[][]{{1,2,5},{2,1,7},{3,1,9}};
        System.out.println(bestCoordinate(towers,2));
    }
}
