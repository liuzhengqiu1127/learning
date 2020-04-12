package com.lzq.study.lettcode.weekly.oneeight;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OneEightFour {
    @Test
    public void test01(){
        System.out.println(stringMatching(new String[]{"mass","as","hero","superhero"}));
        System.out.println(stringMatching(new String[]{"leetcode","et","code"}));
        System.out.println(stringMatching(new String[]{"blue","green","bu"}));
    }
    public List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();
        int len = words.length;
        for (int i=0; i<len; i++){
            int iLen = words[i].length();
            for (int j=0; j<len; j++){
                if (i==j) continue;
                int jLen = words[j].length();
                if (iLen >= jLen) continue;
                if (words[j].contains(words[i])){
                    result.add(words[i]);
                    break;
                }
            }
        }
        return result;
    }
    @Test
    public void test02(){
        int[] result = processQueries(new int[]{7,5,5,8,3},8);
        for (int re : result) System.out.println(re);
    }
    public int[] processQueries(int[] queries, int m) {
        int[] result = new int[queries.length];
        List<Integer> list = new LinkedList<>();
        for (int i=1; i<=m; i++){
            list.add(i);
        }
        for (int i=0; i<queries.length; i++){
            int number = getIndex(queries[i],list);
            result[i] = number;
        }
        return result;
    }
    private int getIndex(int query, List<Integer> list){
        int result = 0;
        int length = list.size();
        int i=0;
        for (; i<length; i++){
            if (query == list.get(i).intValue()){
                result = i;
                break;
            }
        }
        list.remove(i);
        list.add(0,query);
        return result;
    }
    @Test
    public void test03(){
        System.out.println(entityParser("&amp; is an HTML entity but &ambassador; is not."));
        System.out.println(entityParser("and I quote: &apos;...&apos;"));
        System.out.println(entityParser("Stay home! Practice on Leetcode :)"));
        System.out.println(entityParser("x &gt; y &amp;&amp; x &lt; y is always false"));
        System.out.println(entityParser("leetcode.com&frasl;problemset&frasl;all"));
    }
    public String entityParser(String text) {
        if (text.contains("&quot;")){
            text = text.replaceAll("&quot;","\"");
        }
        if (text.contains("&apos;")){
            text = text.replaceAll("&apos;","'");
        }
        if (text.contains("&amp;")){
            text = text.replaceAll("&amp;","&");
        }
        if (text.contains("&gt;")){
            text = text.replaceAll("&gt;",">");
        }
        if (text.contains("&lt;")){
            text = text.replaceAll("&lt;","<");
        }
        if (text.contains("&frasl;")){
            text = text.replaceAll("&frasl;","/");
        }
        return text;
    }
    @Test
    public void test04(){
//        Assert.assertTrue(numOfWays(7)==106494);
        System.out.println(numOfWays(5000));
    }
    public int numOfWays(int n) {
        int mod = 1000000007;
        long aba = 6;
        long abc = 6;
        for (int i=2; i<=n; i++){
            long next1 = (aba * 3 + abc * 2) % mod;
            long next2 = (aba * 2 + abc * 2) % mod;
            aba = next1;
            abc = next2;
        }
        return (int) (aba + abc) % mod;
    }
}
