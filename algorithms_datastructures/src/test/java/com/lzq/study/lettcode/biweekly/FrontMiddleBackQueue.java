package com.lzq.study.lettcode.biweekly;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuzhengqiu on 2020/11/28.
 */
public class FrontMiddleBackQueue {
    private List<Integer> result;

    public FrontMiddleBackQueue() {
        result = new LinkedList<>();
    }

    public void pushFront(int val) {
        result.add(0,val);
    }

    public void pushMiddle(int val) {
        int len = result.size();
        int mid = len >>> 1;
        result.add(mid,val);
    }

    public void pushBack(int val) {
        result.add(val);
    }

    public int popFront() {
        if (result.isEmpty()) return -1;
        return result.remove(0);
    }

    public int popMiddle() {
        if (result.isEmpty()) return -1;
        int len = result.size();
        int mid = (len%2==0)?((len>>>1)-1):(len>>>1);
        return result.remove(mid);
    }

    public int popBack() {
        if (result.isEmpty()) return -1;
        return result.remove(result.size()-1);
    }
}
