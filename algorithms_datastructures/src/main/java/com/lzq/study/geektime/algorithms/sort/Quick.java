package com.lzq.study.geektime.algorithms.sort;

/**
 * 快速排序
 */
public class Quick {

    public static int findK(int[] arr, int k) {
        return findK(arr, 0, arr.length - 1, k);
    }

    private static int findK(int[] arr, int p, int r, int k) {
        int pivot = partition(arr, p, r);
        if (pivot == k - 1) return arr[pivot];
        else if (pivot < k - 1) {
            return findK(arr, pivot + 1, r, k);
        } else {
            return findK(arr, p, pivot - 1, k);
        }
    }

    private static int partition(int[] arr, int p, int r) {
        int value = arr[r];
        int i = p;
        for (int j = i; j < r; j++) {
            if (arr[j] > value) {
                if (i != j) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
                i++;
            }
        }
        int tmp = arr[i];
        arr[i] = arr[r];
        arr[r] = tmp;
        return i;
    }


    public static void sort(int[] arr, int n) {
        sort(arr, 0, n - 1);
    }

    private static void sort(int[] arr, int p, int r) {
        if (p >= r) return;
        int pivot = separate(arr, p, r);
        sort(arr, p, pivot - 1);
        sort(arr, pivot + 1, r);
    }

    private static int separate(int[] arr, int p, int r) {
        int value = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (arr[j] < value) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }
        int tmp = arr[i];
        arr[i] = arr[r];
        arr[r] = tmp;
        return i;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 0, 4, 1, 5, 6, 8};
        System.out.println(findK(arr, 3));
    }

}
