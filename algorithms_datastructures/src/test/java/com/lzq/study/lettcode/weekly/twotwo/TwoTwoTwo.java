package com.lzq.study.lettcode.weekly.twotwo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhengqiu on 2021/1/3.
 */
public class TwoTwoTwo {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes,(boxType1,boxType2)->{
            return boxType2[1] - boxType1[1];
        });
        int result = 0;
        for (int i=0; i<boxTypes.length; i++){
            int number = boxTypes[i][0];
            int size = boxTypes[i][1];
            if (truckSize >= number){
                truckSize -= number;
                result += number*size;
            }else{
                result += truckSize*size;
                break;
            }
        }
        return result;
    }

    @Test
    public void test01(){
        Assert.assertTrue(maximumUnits(new int[][]{{1,3},{2,2},{3,1}},4)==8);
        Assert.assertTrue(maximumUnits(new int[][]{{5,10},{2,5},{4,7},{3,9}},10)==91);
    }

    /**
     * 关键点使用
     * @param deliciousness
     * @return
     */
    public int countPairs(int[] deliciousness) {
        long answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = deliciousness.length;
        for (int i = 0; i < length; i++) {
            Integer orDefault = map.getOrDefault(deliciousness[i], 0);
            // 统计数据
            map.put(deliciousness[i], ++orDefault);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            for (int i = 0; i <= 21; i++) {
                int sum = 1 << i;
                long l = sum - key;
                if (key == l) {//如果是自己，则是从个数中组合2个数据，因为后续要除2，所以计算组合的时候不除
                    answer += 1l * value * (value - 1);
                } else {
                    if (l >= 0 && map.containsKey((int) l)) {
                        answer += 1l * value * map.get((int) l);
                    }
                }
            }
        }
        //结果都算了2遍
        return (int) (answer / 2 % 1000000007);
    }

    @Test
    public void test02(){
        Assert.assertTrue(countPairs(new int[]{1,3,5,7,9})==4);
        Assert.assertTrue(countPairs(new int[]{1,1,1,3,3,3,7})==15);
    }

}
