package com.lzq.study.lettcode.biweekly;

import org.junit.Test;

import java.util.*;

public class ThirtyFive {

    public int sumOddLengthSubarrays(int[] arr) {
        int length = arr.length;
        int count = 1;
        int sum = 0;
        while (count <= length){
            sum += getSum(arr,count);
            count += 2;
        }
        return sum;
    }
    private int getSum(int[] arr, int count){
        int sum = 0;
        for (int index=0; index <= (arr.length-count); index++){
            for (int j=index; j < (index+count); j++){
                sum += arr[j];
            }
        }
        return sum;
    }

    public int maxSumRangeQuery2(int[] nums, int[][] requests){
        int p = 1000000007;
        int n = nums.length;
        long ans = 0;
        int[] diff = new int[n + 1];
        Arrays.sort(nums);
        /**
         * 统计频率，关键实现代码，差分法
         */
        for (int i = 0; i < requests.length; i++){
            diff[requests[i][0]]++;
            diff[requests[i][1] + 1]--;
        }
        for (int i = 0; i < n; i++){
            diff[i+1] += diff[i];
        }
        Arrays.sort(diff);

        for (int i=n; i >= 1 && diff[i] > 0; i--){
            ans +=  (long) diff[i] * nums[i-1];
            ans %= p;
        }
        return (int) ans;
    }

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        Map<Integer,Integer> repeatMap = new HashMap<>();
        for (int[] request : requests){
            int start = request[0];
            int end = request[1];
            for (int index=start;index<=end;index++){ // 这个地方会超过最大时间限制
                repeatMap.put(index,repeatMap.getOrDefault(index,0)+1);
            }
        }
        Arrays.sort(nums);
        Map<Integer,Integer> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<Integer,Integer>> entryList = new ArrayList<>(repeatMap.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        Iterator<Map.Entry<Integer,Integer>> iterable = entryList.iterator();
        Map.Entry<Integer,Integer> tempEntry;
        while (iterable.hasNext()){
            tempEntry = iterable.next();
            sortedMap.put(tempEntry.getKey(),tempEntry.getValue());
        }
        long sum = 0;
        int index = nums.length - 1;
        for (Map.Entry<Integer,Integer> entry : sortedMap.entrySet()){
            sum += entry.getValue()*nums[index];
            index--;
        }
        return (int) (sum%1000000007);
    }

    @Test
    public void test(){
        int[] nums = {22230,66149,11949,25437,54944,33382,29973,41147,80679,55466,73368,57815,41278,68116,51023,88259,45351,62270,21061,97569,19934,6733,92198,61187,55995,95507,62758,74402,14322,36174,37028,67252,48460,5544,68843,2611,25727,82936,6842,15445,56880,27090,76413,28755,74224,62428,85579,64382,41586,95911,28176,27566,86760,10651,17411,39861,92391,14850,65976,59216,37922,69699,25707,79889,43195,9354,23000,28315,8440,23122,95479,38521,84228,78333,22637,31002,44039,83922,80231,10140,89580,43188};
        int[][] requests = {{79,80},{43,78},{73,81},{13,14},{9,44},{34,79},{18,49},{50,74},{46,77},{21,64},{32,80},{59,81},{9,52},{25,40},{39,66},{63,72},{28,46},{21,29},{30,52},{43,62},{69,80},{20,34},{31,66},{72,76},{3,58},{34,72},{78,81},{61,70},{46,56},{47,48},{56,73},{61,65},{50,77},{30,42},{66,66},{19,81},{26,69},{28,39},{71,80},{32,78},{44,61},{10,16},{51,61},{16,17},{10,47},{30,54},{12,14},{65,78},{63,77},{68,76},{23,59},{11,80},{31,42},{66,68},{33,47},{42,65},{29,30},{54,73},{38,60},{5,50},{4,59},{66,81},{6,79},{51,66},{14,59},{24,76},{6,61},{61,64},{39,46},{49,63},{67,74},{55,78},{77,80},{54,67},{66,69},{0,48},{9,27},{45,69},{15,57},{15,27},{52,64},{70,76}};
        System.out.println(maxSumRangeQuery2(nums,requests));
    }

}
