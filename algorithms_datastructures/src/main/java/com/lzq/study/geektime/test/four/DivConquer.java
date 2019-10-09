package com.lzq.study.geektime.test.four;

/**
 * 分治算法 ： 求数组的逆序度
 */
public class DivConquer {
    int num = 0;

    public int count(int[] a, int n){
        num = 0;
        mergeSortCounting(a,0,n-1);
        return num;
    }

    private void mergeSortCounting(int[] a, int p, int r){
        if (p >= r) return;
        int q = (p+r)/2;
        mergeSortCounting(a,p,q);
        mergeSortCounting(a,q+1,r);
        merge(a,p,q,r);
    }

    /**
     * 所有过程都跟合并排序一样
     * @param a
     * @param p
     * @param q
     * @param r
     */
    private void merge(int[] a, int p, int q, int r){ // 所有的过程都跟合并排序一样，只是在两数合并的时候，统计一下逆序对个数
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r-p+1];
        while ( i<=q && j <= r ){
            if (a[i] <= a[j]){
                tmp[k++] = a[i++];
            }else {
                num += (q-i+1); // 唯一不同，在于每次进行统计
                tmp[k++] = a[j++];
            }
        }
        while (i<=q) tmp[k++] = a[i++];
        while (j<=r) tmp[k++] = a[j++];
        for (i = 0; i <= r-p; ++i) a[p+i] = tmp[i];
    }

    public static void main(String[] args) {
        DivConquer divConquer = new DivConquer();
        int[] arr = new int[]{1,5,6,2,3,4};
        System.out.println(divConquer.count(arr,arr.length));
    }
}
