package com.lzq.study.lettcode.weekly.oneeight;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by liuzhengqiu on 2020/4/24.
 */
public class OneEightFive {

    @Test
    public void test01(){
        System.out.println(reformat("a0b1c2"));
        System.out.println(reformat("leetcode"));
        System.out.println(reformat("1229857369"));
        System.out.println(reformat("covid2019"));
        System.out.println(reformat("ab123"));
    }
    public String reformat(String s) {
        List<Character> characterList = new ArrayList<>();
        List<Character> integerList = new ArrayList<>();
        for (char character : s.toCharArray()){
            if (character >= 'a' && character <= 'z'){
                characterList.add(character);
            }else {
                integerList.add(character);
            }
        }
        int charLen = characterList.size();
        int intLen = integerList.size();
        if (Math.abs(charLen - intLen) > 1) return "";
        StringBuilder result = new StringBuilder();
        int i=0;
        if (charLen > intLen){
            for (; i<intLen; i++){
                result.append(characterList.get(i));
                result.append(integerList.get(i));
            }
            result.append(characterList.get(i));
        }else if(charLen == intLen){
            for (; i<charLen; i++){
                result.append(integerList.get(i));
                result.append(characterList.get(i));
            }
        }else{
            for (; i<charLen; i++){
                result.append(integerList.get(i));
                result.append(characterList.get(i));
            }
            result.append(integerList.get(i));
        }
        return result.toString();
    }

    @Test
    public void test02(){
        List<List<String>> orders = new ArrayList<>();
        orders.add(Arrays.asList("David","3","Ceviche"));
        orders.add(Arrays.asList("Corina","10","Beef Burrito"));
        orders.add(Arrays.asList("David","3","Fried Chicken"));
        orders.add(Arrays.asList("Carla","5","Water"));
        orders.add(Arrays.asList("Carla","5","Ceviche"));
        orders.add(Arrays.asList("Rous","3","Ceviche"));
        System.out.println(displayTable(orders));
    }
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> foodItem = new TreeSet<>();
        Map<Integer,Map<String,Integer>> tables = new TreeMap<>();
        for (List<String> list : orders){
            foodItem.add(list.get(2));
            if (tables.containsKey(Integer.valueOf(list.get(1)))){
                Map<String,Integer> map = tables.get(Integer.valueOf(list.get(1)));
                map.put(list.get(2),map.getOrDefault(list.get(2),0)+1);
            }else {
                Map<String,Integer> map = new HashMap<>();
                map.put(list.get(2),1);
                tables.put(Integer.valueOf(list.get(1)),map);
            }
        }

        List<String> title = new ArrayList<>();
        title.add("Table");
        title.addAll(foodItem);
        List<List<String>> result = new ArrayList<>();
        result.add(title);
        for (Map.Entry<Integer,Map<String,Integer>> entry : tables.entrySet()){
            List<String> list = new ArrayList<>();
            list.add(entry.getKey().toString());
            Map<String,Integer> map = entry.getValue();
            for (String food : foodItem){
                list.add(map.getOrDefault(food,0).toString());
            }
            result.add(list);
        }
        return result;
    }

    @Test
    public void test03(){
        Assert.assertTrue(minNumberOfFrogs("ccccccccccrrccccccrcccccccccccrcccccccccrcccccccccccrcccccrcccrrcccccccccccccrocrrcccccccccrccrocccccrccccrrcccccccrrrcrrcrccrcoccroccrccccccccorocrocccrrrrcrccrcrcrcrccrcroccccrccccroorcacrkcccrrroacccrrrraocccrrcrrccorooccrocacckcrcrrrrrrkrrccrcoacrcorcrooccacorcrccccoocroacroraoaarcoorrcrcccccocrrcoccarrorccccrcraoocrrrcoaoroccooccororrrccrcrocrrcorooocorarccoccocrrrocaccrooaaarrcrarooaarrarrororrcrcckracaccorarorocacrrarorrraoacrcokcarcoccoorcrrkaocorcrcrcrooorrcrroorkkaaarkraroraraarooccrkcrcraocooaoocraoorrrccoaraocoorrcokrararrkaakaooroorcororcaorckrrooooakcarokokcoarcccroaakkrrororacrkraooacrkaraoacaraorrorrakaokrokraccaockrookrokoororoooorroaoaokccraoraraokakrookkroakkaookkooraaocakrkokoraoarrakakkakaroaaocakkarkoocokokkrcorkkoorrkraoorkokkarkakokkkracocoaaaaakaraaooraokarrakkorokkoakokakakkcracarcaoaaoaoorcaakkraooaoakkrrroaoaoaarkkarkarkrooaookkroaaarkooakarakkooaokkoorkroaaaokoarkorraoraorcokokaakkaakrkaaokaaaroarkokokkokkkoakaaookkcakkrakooaooroaaaaooaooorkakrkkakkkkaokkooaakorkaroaorkkokaakaaaaaocrrkakrooaaroroakrakrkrakaoaaakokkaaoakrkkoakocaookkakooorkakoaaaaakkokakkorakaaaaoaarkokorkakokakckckookkraooaakokrrakkrkookkaaoakaaaokkaokkaaoakarkakaakkakorkaakkakkkakaaoaakkkaoaokkkakkkoaroookakaokaakkkkkkakoaooakcokkkrrokkkkaoakckakokkocaokaakakaaakakaakakkkkrakoaokkaakkkkkokkkkkkkkrkakkokkroaakkakaoakkoakkkkkkakakakkkaakkkkakkkrkoak")==229);
    }
    public int minNumberOfFrogs(String croakOfFrogs) {
        Map<Character,Integer> map = new HashMap<>();
        int cNumber = 0;
        int max = 0;
        for (Character character : croakOfFrogs.toCharArray()){
            map.put(character,map.getOrDefault(character,0)+1);
            if (!check(map)) return -1;
            if (character.charValue()=='c'){
                cNumber++;
            }
            if (character.charValue()=='k'){
                max = Integer.max(max, cNumber);
                cNumber--;
            }
        }
        int number = map.get('c');
        for (Map.Entry<Character,Integer> entry : map.entrySet()){
            if (number != entry.getValue().intValue()){
                return -1;
            }
        }
        return max;
    }
    private boolean check(Map<Character,Integer> map){
        int cNumber = map.getOrDefault('c',0);
        int rNumber = map.getOrDefault('r',0);
        int oNumber = map.getOrDefault('o',0);
        int aNumber = map.getOrDefault('a',0);
        int kNumber = map.getOrDefault('k',0);
        return cNumber >= rNumber && rNumber >= oNumber && oNumber >= aNumber && aNumber >= kNumber;
    }

    @Test
    public void test04(){

    }

}
