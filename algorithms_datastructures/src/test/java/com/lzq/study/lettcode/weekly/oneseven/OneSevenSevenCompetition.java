package com.lzq.study.lettcode.weekly.oneseven;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneSevenSevenCompetition {

    @Test
    public void test01(){
        Assert.assertTrue(daysBetweenDates("2019-06-29","2019-06-30")==1);
        Assert.assertTrue(daysBetweenDates("2020-01-15","2019-12-31")==15);
    }

    public int daysBetweenDates(String date1, String date2) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long oneDate = sdf.parse(date1).getTime();
            long otherDate = sdf.parse(date2).getTime();
            if (oneDate == otherDate) return 0;
            else if(oneDate > otherDate) {
                return (int)((oneDate-otherDate)/(1000*60*60*24));
            }else {
                return (int)((otherDate-oneDate)/(1000*60*60*24));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Test
    public void test02(){
        Assert.assertTrue(validateBinaryTreeNodes(4,new int[]{1,-1,3,-1},new int[]{2,-1,-1,-1})==true);
        Assert.assertTrue(validateBinaryTreeNodes(4,new int[]{1,-1,3,-1},new int[]{2,3,-1,-1})==false);
        Assert.assertTrue(validateBinaryTreeNodes(2,new int[]{1,0},new int[]{-1,-1})==false);
        Assert.assertTrue(validateBinaryTreeNodes(6,new int[]{1,-1,-1,4,-1,-1},new int[]{2,-1,-1,5,-1,-1})==false);
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        List<Integer> record = new ArrayList<>();
        record.add(0);
        for (int i=0; i < n; i++){
            int leftNode = leftChild[i];
            int rightNode = rightChild[i];
            if (leftNode != -1) {
                if (record.contains(leftNode) || !record.contains(i)) return false;
                else record.add(leftNode);
            }
            if (rightNode != -1) {
                if (record.contains(rightNode) || !record.contains(i)) return false;
                else record.add(rightNode);
            }
        }
        return true;
    }

    @Test
    public void test03(){
        System.out.println(closestDivisors(8)[1]);
        System.out.println(closestDivisors(123)[1]);
        System.out.println(closestDivisors(999)[1]);
    }
    public int[] closestDivisors(int num) {
        int[] one = getOneClosestDivisors(num + 1);
        int[] two = getOneClosestDivisors(num + 2);
        if (one[2] > two[2]){
            return new int[]{two[0],two[1]};
        }else {
            return new int[]{one[0],one[1]};
        }
    }
    private int[] getOneClosestDivisors(int number){
        int[] result = new int[3];
        int disparity = Integer.MAX_VALUE;
        for (int i = 1; i <= Math.sqrt(number); i++){
            if (number % i == 0){
                int a = i;
                int b = number / i;
                if(Math.abs(a - b) < disparity){
                    disparity = Math.abs(a - b);
                    result[0] = a;
                    result[1] = b;
                    result[2] = disparity;
                }
                if (Math.abs(a - b) > disparity){
                    break;
                }
            }
        }
        return result;
    }

    @Test
    public void test04(){
        Assert.assertTrue(largestMultipleOfThree(new int[]{9,8,6,8,6}).equals("966"));
    }

    public String largestMultipleOfThree(int[] digits) {
        Arrays.sort(digits);
        int sum = 0;
        List<Integer> mod1List = new ArrayList<>();
        List<Integer> mod2List = new ArrayList<>();
        for (int i = 0; i < digits.length; i++){
            if (digits[i] % 3 == 1) mod1List.add(digits[i]);
            if (digits[i] % 3 == 2) mod2List.add(digits[i]);
            sum += digits[i];
        }
        if (sum == 0) return "0";
        StringBuilder result = new StringBuilder();


        if (sum % 3 == 0){
            for (int i = digits.length-1; i >= 0; i--){
                result.append(digits[i]);
            }
            return result.toString();
        }

        boolean[] flag = new boolean[]{false,false};
        if (sum % 3 == 1){
            if (mod1List.size() >= 1){
                int number = mod1List.get(0);
                if (sum - number == 0) return "";
                for (int i = digits.length-1; i >= 0; i--){
                    if (number==digits[i]&&flag[0]==false){
                        flag[0] = true;
                        continue;
                    }
                    result.append(digits[i]);
                }
            }else if (mod2List.size() >= 2){
                int[] number = new int[]{mod2List.get(0),mod2List.get(1)};
                if ((sum - (number[0]+number[1])) == 0) return "";
                int j = 0;
                for (int i = digits.length-1; i >= 0; i--){
                    if (number[j]==digits[i]&&flag[j]==false){
                        flag[j] = true;
                        j = 1;
                        continue;
                    }
                    result.append(digits[i]);
                }
            }else {
                return "";
            }
        }

        if (sum % 3 == 2){
            if (mod2List.size() >= 1){
                int number = mod2List.get(0);
                if (sum - number == 0) return "";
                for (int i = digits.length-1; i >= 0; i--){
                    if (number==digits[i]&&flag[0]==false){
                        flag[0] = true;
                        continue;
                    }
                    result.append(digits[i]);
                }
            }else if (mod1List.size() >= 2){
                int j = 0;
                int[] number = new int[]{mod1List.get(0),mod1List.get(1)};
                if (sum - (number[0] + number[1]) == 0 ) return "";
                for (int i = digits.length-1; i >= 0; i--){
                    if (number[j]==digits[i]&&flag[j]==false){
                        flag[j] = true;
                        j = 1;
                        continue;
                    }
                    result.append(digits[i]);
                }
            }
        }

        return result.toString();
    }

}
