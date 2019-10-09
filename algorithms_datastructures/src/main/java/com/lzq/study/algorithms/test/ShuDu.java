package com.lzq.study.algorithms.test;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ShuDu {
    /** 存储数字的数组 */
    private static int[][] n = new int[9][9];
    private static boolean[][] flag = new boolean[9][9];//标识哪些地方可以修改数据
    private static Map<String, List<Integer>> filterMaps = Maps.newHashMap();

    /** 生成随机数字的源数组，随机数字从该数组中产生 */
    private static int[] num = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    public static int[][] generateShuDu(){
        // 生成数字
        for (int i = 0; i < 9; i++) {
            // 尝试填充的数字次数
            int time = 0;
            // 填充数字
            boolean tag = false;
            for (int j = 0; j < 9; j++) {
                if (flag[i][j] == true && !tag){
                    continue;
                }
                if (flag[i][j] == true && tag){
                    if (j > 0) {
                        j -= 2;
                    } else {// 是第一列，则倒退到上一行的最后一列
                        i--;
                        j = 7; // 不能是8 因为还要执行一次j++
                    }
                    continue;
                }
                // 产生数字
                n[i][j] = generateNum(time);
                // 如果返回值为0，则代表卡住，退回处理
                // 退回处理的原则是：如果不是第一列，则先倒退到前一列，否则倒退到前一行的最后一列
                if (n[i][j] == 0) {
                    if (i != 0 || j != 0) filterMaps.remove(""+i+j); //上一层数据变化时，这层数据需要清空
                    // 不是第一列，则倒退一列
                    if (j > 0) {
                        j -= 2;
                    } else {// 是第一列，则倒退到上一行的最后一列
                        i--;
                        j = 7;
                    }
                    tag = true;
                    time = 0;
                    continue;
                }
                // 填充成功
                if (isCorret(i, j)) {
                    // 初始化time，为下一次填充做准备
                    String key = "" + i + j;
                    if (!filterMaps.containsKey(key)){ //如果防止成功把数据添加到Map中
                        filterMaps.put(key, Lists.newArrayList(n[i][j]));
                    }else{
                        filterMaps.get(key).add(n[i][j]);
                    }
                    time = 0;
                } else { // 继续填充
                    // 次数增加1
                    time++;
                    // 继续填充当前格
                    j--;
                }
                tag = false;
            }
        }
        return n;
    }

    /**
     * 是否满足行、列和3X3区域不重复的要求
     *
     * @param row
     *            行号
     * @param col
     *            列号
     * @return true代表符合要求
     */
    private static boolean isCorret(int row, int col) {
        return (checkRow(row) & checkLine(col) & checkNine(row, col) & isContain(row,col));
    }

    /**
     * 需要判断本数据是否已经存在过，如果存在过，则不能重复放置
     * @param row
     * @param col
     * @return
     */
    private static boolean isContain(int row, int col){
        String key = "" + row + col;
        return !filterMaps.containsKey(key) || !filterMaps.get(key).contains(n[row][col]);
    }

    /**
     * 检查行是否符合要求
     *
     * @param row
     *            检查的行号
     * @return true代表符合要求
     */
    private static boolean checkRow(int row) {
        for (int j = 0; j < 8; j++) {
            if (n[row][j] == 0) {
                continue;
            }
            for (int k = j + 1; k < 9; k++) {
                if (n[row][j] == n[row][k]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检查列是否符合要求
     *
     * @param col
     *            检查的列号
     * @return true代表符合要求
     */
    private static boolean checkLine(int col) {
        for (int j = 0; j < 8; j++) {
            if (n[j][col] == 0) {
                continue;
            }
            for (int k = j + 1; k < 9; k++) {
                if (n[j][col] == n[k][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检查3X3区域是否符合要求
     *
     * @param row
     *            检查的行号
     * @param col
     *            检查的列号
     * @return true代表符合要求
     */
    private static boolean checkNine(int row, int col) {
        // 获得左上角的坐标
        int j = row / 3 * 3;
        int k = col / 3 * 3;
        // 循环比较
        for (int i = 0; i < 8; i++) {
            if (n[j + i / 3][k + i % 3] == 0) {
                continue;
            }
            for (int m = i + 1; m < 9; m++) {
                if (n[j + i / 3][k + i % 3] == n[j + m / 3][k + m % 3]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 产生1-9之间的随机数字 规则：生成的随机数字放置在数组8-time下标的位置，随着time的增加，已经尝试过的数字将不会在取到
     * 说明：即第一次次是从所有数字中随机，第二次时从前八个数字中随机，依次类推， 这样既保证随机，也不会再重复取已经不符合要求的数字，提高程序的效率
     * 这个规则是本算法的核心
     *
     * @param time
     *            填充的次数，0代表第一次填充
     * @return
     */
    private static Random r=new Random();
    private static int generateNum(int time) {
        // 第一次尝试时，初始化随机数字源数组
        if (time == 0) {
            for (int i = 0; i < 9; i++) {
                num[i] = i + 1;
            }
        }
        // 第10次填充，表明该位置已经卡住，则返回0，由主程序处理退回
        if (time == 9) {
            return 0;
        }
        // 不是第一次填充
        // 生成随机数字，该数字是数组的下标，取数组num中该下标对应的数字为随机数字
//		int ranNum = (int) (Math.random() * (9 - time));//j2se
        int ranNum=r.nextInt(9 - time);//j2me [0,9-time)
        // 把数字放置在数组倒数第time个位置，
        int temp = num[8 - time];
        num[8 - time] = num[ranNum];
        num[ranNum] = temp;
        // 返回数字
        return num[8 - time];
    }

    public static void main(String[] args) {
        Map<String,Integer> map = Maps.newHashMap();
        map.put("0:0",3);map.put("0:2",8);map.put("0:3",1);
        map.put("0:5",5);map.put("0:7",9);map.put("1:5",4);
        map.put("2:1",7);map.put("2:2",2);map.put("2:8",1);
        map.put("3:2",3);map.put("4:0",2);map.put("4:2",7);
        map.put("4:6",4);map.put("4:8",8);map.put("5:6",2);
        map.put("6:0",9);map.put("6:6",8);map.put("6:7",7);
        map.put("7:3",8);map.put("8:1",8);map.put("8:3",7);
        map.put("8:5",6);map.put("8:6",5);map.put("8:8",3);
//        map.put("8:4",1);map.put("8:8",7);
        init(map);
        int[][] shuDu=generateShuDu();
        print(shuDu);
    }

    private static void init(Map<String,Integer> map){
        filterMaps.clear();
        Set<Map.Entry<String,Integer>> entrySet = map.entrySet();
        for (Map.Entry<String,Integer> entry : entrySet){
            int row = Integer.valueOf(entry.getKey().split(":")[0]);
            int col = Integer.valueOf(entry.getKey().split(":")[1]);
            n[row][col] = entry.getValue();
            flag[row][col] = true;
        }
    }

    private static void print(int[][] shuDu){
        // 输出结果
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(shuDu[i][j] + "   ");
            }
            System.out.println();
        }
    }
}

