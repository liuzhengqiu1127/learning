package com.lzq.study.lettcode.weekly.twozero;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class TwoZeroTwo {

    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int number : arr){
            if (number % 2 != 0){
                count++;
            }else{
                count = 0;
            }
            if (count >= 3) return true;
        }
        return false;
    }

    public int minOperations(int n) {
        int sum = 0;
        for (int number=1; number<=n; number+=2){
            sum += (n-number);
        }
        return sum;
    }

    /**
     * 题目意思转换: 两个位置的距离 >= 1, <= (max - min)/(m-1)
     * 求 位置距离个数>=m，最大距离
     * @param position
     * @param m
     * @return
     */
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int hi = (position[position.length-1] - position[0]) / (m-1);
        int lo = 1;
        int ans = 1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if (check(position, mid, m)){
                ans = mid;
                lo = mid + 1;
            }else {
                hi = mid - 1;
            }
        }
        return ans;
    }
    boolean check(int[] position, int distance, int m){
        int count = 1;
        int i = 0;
        for (int j = 1; j < position.length; j++){
            if (position[j] - position[i] >= distance){
                i = j;
                count++;
                if (count >= m) return true;
            }
        }
        return false;
    }

    /**            23  -> 22 -> 21 -> 20 -> 19
     * BFS: 24 ->  12  -> (11, 6, 4) -> (10, (5,3,2)) -> (9, 1) -> 0
     *             8   -> 7
     *
     * @param n
     * @return
     */
    public int minDays(int n) {
        if (n == 1) return 1;
        LinkedList<Integer> linkedList = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        linkedList.offer(n);
        visited.add(n);
        int ans = 0;

        outer:
        while (!linkedList.isEmpty()){
            int qSize = linkedList.size();
            for (int i = 0; i < qSize; i++){
                int remainder = linkedList.poll();
                if (remainder == 0) break outer;
                if (remainder % 3 == 0){
                    int t = remainder/3;
                    if (!visited.contains(t)){
                        linkedList.offer(t);
                        visited.add(t);
                    }
                }
                if (remainder % 2 == 0){
                    int t = remainder/2;
                    if (!visited.contains(t)){
                        linkedList.offer(t);
                        visited.add(t);
                    }
                }
                int t = remainder - 1;
                if (!visited.contains(t)){
                    linkedList.offer(t);
                    visited.add(t);
                }
            }
            ans++;
        }
        return ans;
    }


    @Test
    public void test(){
        System.out.println(minDays(24));
    }
}
