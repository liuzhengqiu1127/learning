package com.lzq.study.geektime.algorithms.sort;

public class Exercise {

    public static String sort(String string){
        char[] chars = string.toCharArray();
        LinkNode node = new LinkNode(chars[0]);
        LinkNode first = node;
        LinkNode head = node;
        LinkNode tail = null;
        for (int i=1;i<chars.length;i++){
            LinkNode linkNode = new LinkNode(chars[i]);
            node.last = linkNode;
            linkNode.pre = node;
            node = node.last;
            if (i==chars.length-1) tail = node;
        }

        boolean flag1 = false;
        boolean flag2 = false;

        while (head!=tail){
            if ((""+head.ch).matches("[A-Z]")){
                flag1 = true;
            }else {
                flag1 = false;
                head = head.last;
            }
            if ((""+tail.ch).matches("[a-z]")){
                flag2 = true;
            }else {
                flag2 = false;
                tail = tail.pre;
            }
            if (flag1&&flag2){
                char tmp = head.ch;
                head.ch = tail.ch;
                tail.ch = tmp;
                head = head.last;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (first!=null){
            stringBuilder.append(first.ch);
            first = first.last;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(sort("aDsRssDDgfR"));
    }


    static class LinkNode{
        private char ch;
        private LinkNode pre;
        private LinkNode last;

        public LinkNode(char ch){
            this.ch = ch;
        }
    }

}
