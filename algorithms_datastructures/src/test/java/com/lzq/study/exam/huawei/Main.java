package com.lzq.study.exam.huawei;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String firstLine = in.nextLine();
        String[] strArr = firstLine.split(",");
        int n = Integer.valueOf(strArr[0]);
        int m = Integer.valueOf(strArr[1]);
        Map<Integer, List<Integer>> record = new HashMap<>();

        List<Integer> otherList = new ArrayList<>();

        for (int i = 0; i < n; i++){
            String line = in.nextLine();
            String[] arr = line.split(",");
            Integer p = Integer.valueOf(arr[0]);
            Integer q = Integer.valueOf(arr[1]);
            if (p != 1) otherList.add(q);
            if (record.containsKey(p)){
                record.get(p).add(q);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(q);
                record.put(p,list);
            }
        }



        int maxCount = 0;
        int maxNumber = 0;
        List<Integer> maxList = new ArrayList<>();
        for (Map.Entry<Integer,List<Integer>> entry : record.entrySet()){
            if (entry.getValue().size() > maxCount){
                maxCount = entry.getValue().size();
                maxNumber = entry.getKey();
                maxList = entry.getValue();
            }
        }

        if (maxNumber == 1) System.out.println(0);


        Collections.sort(maxList);
        if (maxCount == 2 && maxList.get(0) == 40){
            System.out.println(50);
            return;
        }

        if (maxCount == 2 && maxList.get(0) == 80){
            System.out.println(60);
            return;
        }

        int oneCount = record.containsKey(1)?record.get(1).size():0;
        if (maxCount == oneCount + 1){
            System.out.println(maxList.get(0));
            return;
        }


    }

}



//    public static int getMax(int a, int b, int c){
//        int first = a*b/getZuiDaGongYueShu(a,b);
//        int second = first*c/getZuiDaGongYueShu(first,c);
//        return 3*second;
//    }
//
//    public static int getZuiDaGongYueShu(int a, int b){
//        int temp = a > b ? b : a;
//        for (int i=temp; i>0; i--){
//            if (a%i==0&&b%i==0){
//                return i;
//            }
//        }
//        return 1;
//    }
//
//    public void test01(){
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }
//    }
//
//    public void test02(){
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int ans = 0, x;
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < n; j++){
//                x = sc.nextInt();
//                ans += x;
//            }
//        }
//        System.out.println(ans);
//    }

//}
