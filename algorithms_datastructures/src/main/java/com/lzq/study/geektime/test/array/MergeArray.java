package com.lzq.study.geektime.test.array;

public class MergeArray {

    //两个有序数组的合并
    public int[] merge(int[] a, int[] b)
    {
        int aLen = a.length;
        int bLen = b.length;
        int[] result = new int[aLen + bLen];
        int i = 0, j = 0, k = 0;
        while (i < aLen && j < bLen){
            if (a[i] < b[j]){
                result[k++] = a[i++];
            }else {
                result[k++] = b[j++];
            }
        }
        if (i < aLen){
            while (i < aLen){
                result[k++] = a[i++];
            }
        }else {
            while (j < bLen){
                result[k++] = b[j++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,4,7,9,11,23,45,67,89};
        int[] b = new int[]{3,5,8,10,16,24,36,46,50,60};
        MergeArray mergeArray = new MergeArray();
        int[] intArr = mergeArray.merge(a,b);
        for (int s : intArr){
            System.out.print(s + ",");
        }
    }


}
