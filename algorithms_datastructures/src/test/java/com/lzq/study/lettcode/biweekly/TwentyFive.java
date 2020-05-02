package com.lzq.study.lettcode.biweekly;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by liuzhengqiu on 2020/5/2.
 */
public class TwentyFive {
    @Test
    public void test01(){
        System.out.println(kidsWithCandies(new int[]{2,3,5,1,3},3));
        System.out.println(kidsWithCandies(new int[]{4,2,1,1,2},1));
        System.out.println(kidsWithCandies(new int[]{12,1,12},10));
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int candie : candies){
            max = Integer.max(max,candie);
        }
        List<Boolean> result = new ArrayList<>();
        for (int candie : candies){
            if (candie + extraCandies >= max){
                result.add(true);
            }else {
                result.add(false);
            }
        }
        return result;
    }

    @Test
    public void test02(){
        Assert.assertTrue(maxDiff(1101057)==8808050);
    }

    public int maxDiff(int num) {
        String numStr = String.valueOf(num);
        Character replace = null;
        for (Character ch : numStr.toCharArray()){
            if (ch.charValue() != '9'){
                replace = ch;
                break;
            }
        }
        int maxNumber = 0;
        if (Objects.isNull(replace)) maxNumber = num;
        else {
            String maxNumStr = numStr.replaceAll(""+replace,"9");
            maxNumber = Integer.valueOf(maxNumStr);
        }

        int minNumber = 0;
        replace = null;
        int i=0;
        for (; i<numStr.length(); i++){
            if (i==0&&numStr.charAt(i)!='1'){
                replace = numStr.charAt(0);
                break;
            }
            if (i!=0&&numStr.charAt(i)!='0'&&numStr.charAt(i)!='1'){
                replace = numStr.charAt(i);
                break;
            }
        }
        if (Objects.isNull(replace)) minNumber = num;
        else if (i==0){
            minNumber = Integer.valueOf(numStr.replaceAll(""+replace,"1"));
        }else {
            minNumber = Integer.valueOf(numStr.replaceAll(""+replace,"0"));
        }

        return maxNumber - minNumber;
    }

    @Test
    public void test03(){
        Assert.assertTrue(checkIfCanBreak("pamntyya","yopumzgd"));
    }

    public boolean checkIfCanBreak(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        int length = chars1.length;

        int lastFlag = 0;
        int i=0;
        for (; i<length; i++){
            if (chars1[i] - chars2[i] == 0) continue;
            lastFlag = (chars1[i] - chars2[i] > 0 ? 1 : 2);
            break;
        }
        for (;i<length; i++){
            int curFlag = 0;
            if (lastFlag == 1) curFlag = (chars1[i] - chars2[i] >= 0 ? 1 : 2);
            else curFlag = (chars1[i] - chars2[i] <= 0 ? 2 : 1);
            if (curFlag != lastFlag) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test04(){}
}
