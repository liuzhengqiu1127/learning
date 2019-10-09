package com.lzq.study.geektime.test.tree;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 二叉查找树
 */
public class BinarySearchTree {
    private Node root;

    /**
     * 前序遍历 中 左 右
     */
    public void preOrder(Node head)
    {
        if (head != null) System.out.println(head.data);
        if (head.left!=null) preOrder(head.left);
        if (head.right!=null) preOrder(head.right);
    }

    /**
     * 中序遍历 左 中 右
     * @param head
     */
    public void midOrder(Node head)
    {
        if (head.left!=null) midOrder(head.left);
        System.out.println(head.data);
        if (head.right!=null) midOrder(head.right);
    }

    /**
     * 后续遍历  左 右 中
     * @param head
     */
    public void postOrder(Node head)
    {
        if(head.left!=null) postOrder(head.left);
        if (head.right!=null) postOrder(head.right);
        System.out.println(head.data);
    }

    /**
     * 查找某个节点的前驱节点
     * @param node
     * @return
     */
    public Node findParent(Node node){
        Node p = root;
        if (p==null || p==node) return null;
        int data = node.data;
        Node pp = null;
        while (p!=null){
            pp = p;
            if (p.data == data){
                break;
            }else if (p.data > data){
                p = p.left;
            }else {
                p = p.right;
            }
        }
        return pp;
    }

    /**
     * 找到某个节点的后继节点
     * @param node
     */
    public List<Node> findChild(Node node){
         List<Node> nodes = Lists.newArrayList();
         Node p = node;
         if (p.left!=null) nodes.add(p.left);
         if (p.right!=null) nodes.add(p.right);
         return nodes;
    }

    /**
     * 查找
     * @param data
     * @return
     */
    public Node find(int data){
        Node p = root;
        if (p == null) return null;
        while (p!=null){
            int nd = p.data;
            if (nd == data) return p;
            else if (nd < data){
                p = p.right;
            }else {
                p = p.left;
            }
        }
        return null;
    }

    /**
     * 插入
     * @param data
     */
    public void insert(int data){
        Node p = root;
        if (p == null) {
            p = new Node(data);
            return;
        }
        while (p!=null){
            int nd = p.data;
            if (data > nd){
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }else {
                    p = p.right;
                }
            }else {
                if (p.left == null){
                    p.left = new Node(data);
                    return;
                }else {
                    p = p.left;
                }
            }
        }
    }

    /**
     * 删除节点
     * @param data
     */
    public void delete(int data){
        Node p = root;
        Node pp = null;//找到要删除的节点和父节点
        while (p != null && p.data != data){
            pp = p;
            if (data > p.data) p = p.right;
            else p = p.left;
        }
        if (p==null) return; // 说明没有找到

        if(p.left != null && p.right != null){ //如果删除的节点有两个孩子，则替换其右孩子节点的最小值，并重新确认删除节点
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null){
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }

        Node child; // 找到要删除节点的孩子
        if (p.left!=null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) root = child; // 让父节点指向孩子就删除了节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;

    }

    static class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data){
            this.data = data;
        }
    }
}
