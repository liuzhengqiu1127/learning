package com.lzq.study.geektime.test.string;

/**
 * 字符串匹配 BF算法
 */
public class BruteForce {

    public static int match(char[] text, char[] sub)
    {
        int mLen = text.length;
        int sLen = sub.length;
        if (sLen > mLen) return -1;
        int count = mLen - sLen + 1;
        for (int i = 0; i < count; i++){
            boolean flag = true;
            char[] temp = new char[sLen];
            for (int j = i; j < i + sLen; j++){
                temp[j-i] = text[j];
            }
            for (int k = 0; k < sLen; k++){
                if (temp[k] != sub[k]){
                    flag = false;
                    break;
                }
            }
            if (flag){
                return i;
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        System.out.println(match("qwertyssfshsfshshss".toCharArray(),"ssfshs".toCharArray()));
    }

}
