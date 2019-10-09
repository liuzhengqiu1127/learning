package com.lzq.study.geektime.structure.binarytree;

public class BinaryTree {
    private static Node tree;

    public Node find(int data){
        Node p = tree;
        while ( p != null){
            if (data < p.data) p = p.left;
            else if (data > p.data) p = p.right;
            else return p;
        }
        return null;
    }

    public void insert(int data){
        if (tree == null){
            tree = new Node(data);
            return ;
        }
        Node p = tree;
        while ( p != null){
            if (data > p.data){
                if (p.right == null){
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }else {
                if ( p.left == null){
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    public void delete(int data){
        Node p = tree;
        Node pp = null;
        while (p != null && p.data != data){
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        if (p == null) return;
        if (p.left!=null && p.right !=null){
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null){
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            pp = minPP;
            p = minP;
        }

        Node child;
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child;
        else if (pp.left == p) pp.left = child;
        else pp.right = child;

    }

    public void print(Node node){
        if (node.left!=null) print(node.left);
        System.out.println(node.data);
        if (node.right!=null) print(node.right);
    }

    public int height(Node node){
        if (node==null||(node.right==null&&node.left==null)) return 0;
        int leftH = height(node.left);
        int rightH = height(node.right);
        return Integer.max(leftH,rightH) + 1;
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(6);
        binaryTree.insert(4);
        binaryTree.insert(8);
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(9);
        System.out.println(binaryTree.height(tree));
    }
}
