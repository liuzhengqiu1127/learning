package com.lzq.study.geektime.test.four;

/**
 * 回溯算法
 */
public class Recall {

    int[] result = new int[8];
    int total = 0;

    /**
     * 八皇后问题
     */
    public void cal8queens(int row){
        if (row == 8){
            printQueens(result);
            total++;
            return;
        }
        for (int column = 0; column < 8; ++column){
            if (isOk(row,column)){ // 满足条件
                result[row] = column; // 每一行都需要尝试8次
                cal8queens(row+1);
            }
        }
    }

    private boolean isOk(int row, int column){
        int leftup = column - 1, rightup = column + 1;
        for (int i = row - 1; i >= 0; --i){
            if (result[i] == column) return false; //前面所有行不能在其列
            if (leftup >= 0){
                if (result[i] == leftup) return false; // 左对角线
            }
            if (rightup <= 8){
                if (result[i] == rightup) return false; // 右对角线
            }
            --leftup;
            rightup++;
        }
        return true;
    }

    private void printQueens(int[] result){
        for (int row = 0; row < 8; ++row){
            for (int column = 0; column < 8; ++column){
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int maxW = Integer.MIN_VALUE;
    /**
     * 0 1 背包问题 n 个 装进 大小不超 w的背包，求最好装法
     * @param cw 表示当前已经装进去的物品的重量和，i 表示考察到哪个物品了；
     * @param  w 背包重量； items 表示每个物品的重量； n 表示物品个数
     * 假设背包可承受重量100，物品个数10，物品重量存储在数组a中， 那可以这样调用函数 f(0,0,a,10,100)
     * @return
     */
    public void f(int i, int cw, int[] items, int n, int w){
        if (cw == w || i == n){
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i+1,cw,items,n,w); // 不装进背包
        if (cw + items[i] <= w){
            f(i+1,cw + items[i],items,n,w); // 装进背包
        }
    }


    public static void main(String[] args) {
        Recall recall = new Recall();
        recall.cal8queens(0);
        System.out.println("总共有"+recall.total+"方法");

        int[] items = new int[]{1,3,5,7,12,14,19,21,27,31,34};
        recall.f(0,0,items,items.length,123);
        System.out.println(recall.maxW);
    }

}
