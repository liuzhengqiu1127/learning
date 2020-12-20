package com.lzq.study.lettcode.weekly.twotwo;

import org.junit.Test;

/**
 * Created by liuzhengqiu on 2020/12/20.
 */
public class TwoTwoZero {

    public String reformatNumber(String number) {
        number = number.replaceAll(" ","");
        number = number.replaceAll("-","");
        int length = number.length();
        StringBuilder result = new StringBuilder();
        int index = 0;
        while (length > 0){
            if (length == 2 || length == 3){
                result.append(number.substring(index));
                break;
            }
            if(length == 4){
                result.append(number.substring(index,index+2));
                result.append("-");
                result.append(number.substring(index+2,index+4));
                break;
            }
            result.append(number.substring(index,index+3));
            result.append("-");
            index += 3;
            length -= 3;
        }
        return result.toString();
    }

    @Test
    public void test(){
        System.out.println(reformatNumber("--17-5 229 35-39475 "));
    }
}
