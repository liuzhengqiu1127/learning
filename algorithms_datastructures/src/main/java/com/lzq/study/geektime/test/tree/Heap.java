package com.lzq.study.geektime.test.tree;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 堆代码
 *
 * 优先级队列：类似于堆的应用， 堆顶元素的优先级最高， 包括入队和出队
 *
 */
public class Heap {
    private int[] arr;
    private int n;
    private int count;

    public Heap(int capacity){
        arr = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public int pop(){
        int data = arr[1];
        removeMax();
        return data;
    }

    public void push(int data){
        insertBigHeap(data);
    }

    public void push2(int data){
        insertSmallHeap(data);
    }

    /**
     * 大顶堆
     * @param data
     */
    public void insertBigHeap(int data){
        if (count >= n) return;
        ++count;
        arr[count] = data;
        int i = count;
        while (i/2 > 0 && arr[i] > arr[i/2]){
            int tmp = arr[i];
            arr[i] = arr[i/2];
            arr[i/2] = tmp;
            i = i/2;
        }
    }

    public void removeMax(){
        if (count==0) return;
        arr[1] = arr[count];
        --count;
        heapify(arr,count,1);
    }

    public void heapify(int[] a,int n,int i){//自上往下堆化
        while (true){
            int maxPos = i;
            if (i*2 <= n && a[i] > a[i*2]) maxPos = i * 2;
            if (i*2 + 1 <= n && a[maxPos] > a[i*2+1]) maxPos = i * 2 +1;
            if (maxPos == i) break;
            int tmp = a[i];
            a[i] = a[maxPos];
            a[maxPos] = tmp;
            i = maxPos;
        }
    }

    /**
     * 小顶堆
     * @param data
     */
    public void insertSmallHeap(int data){
        if (count >= n) return;
        ++count;
        arr[count] = data;
        int i = count;
        while (i/2 > 0 && arr[i] < arr[i/2]){
            int tmp = arr[i];
            arr[i] = arr[i/2];
            arr[i/2] = tmp;
            i = i/2;
        }
    }

    private void buildHeap(int[] a, int n){
        for (int i=n/2;i>=1;--i){
           heapify(a,n,i);
        }
    }

    /**
     * 堆排序
     * @param a
     * @param n
     */
    public void sort(int[] a,int n){
        buildHeap(a,n); // 建好堆之后最大元素已经确认
        int k = n;
        while (k > 1){ // 1 和 n 交换后堆化，1和n-1交换后堆化
            int tmp = a[1];
            a[1] = a[k];
            a[k] = tmp;
            --k;
            heapify(a,k,1);
        }
    }

    /**
     * 对k个有序数组合并
     * 1，构建一个K大小的小顶堆
     * 2，每次获取堆顶元素，放入新的数组，删除堆顶元素
     * 3，从堆顶元素的数组中，再去拿一个过去，插入小顶堆
     * 4，重复2，直到取完所有元素
     * @param map
     */
    public void sortKArr(Map<Integer,List<Integer>> map){
        int size = map.size();
        Heap heap = new Heap(size);
        for (Map.Entry<Integer,List<Integer>> entry : map.entrySet()){
            heap.push(entry.getValue().get(0));
        }

    }

    /**
     * 求前top K 大的数据 PriorityQueue 默认也是小顶堆
     * @param data
     * @param k
     * @return
     */
    public int[] topk(int[] data, int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int i = 0; i < data.length; i++){
            if (queue.size() < k) {
                queue.offer(data[i]);
            }else {
                int value = queue.peek();//小顶堆
                if (data[i] > value){
                    queue.poll();
                    queue.offer(data[i]);
                }
            }
        }

        int[] result = new int[k];
        int index = 0;
        while (!queue.isEmpty()){
            result[index++] = queue.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] data = new int[]{23,22,3,45,76,35,67,46,13,24,68,45,89,80,90,30,50};
        int[] result = new Heap(1).topk(data,5);
        for (int i = 0; i < result.length; i++) System.out.print(result[i] + ",");

        System.out.println("=============");

        Heap heap = new Heap(20);
        heap.push(2);
        heap.push(9);
        heap.push(3);
        heap.push(20);
        heap.push(11);
        heap.push(23);
        System.out.println(heap.pop()+","+heap.pop());
    }


}
