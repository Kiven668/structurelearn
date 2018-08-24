package com.structure.tree;

public class BinarySearchTree<E extends  Comparable<E>> {
    private Node root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) return false;

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth));
            res.append("null\n");
            return;
        }

        res.append(generateDepthString(depth));
        res.append(node.e);
        res.append("\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }

        return res.toString();
    }

    public void preOrder() {
        preOrder(TraverseType.PREORDER, root);
    }

    private void preOrder(TraverseType type, Node node) {
        if (node == null) return;

        if (type.equals(TraverseType.PREORDER))
            System.out.println(node.e);

        preOrder(type, node.left);

        if (type.equals(TraverseType.INORDER))
            System.out.println(node.e);

        preOrder(type, node.right);

        if (type.equals(TraverseType.LASTORDER))
            System.out.println(node.e);
    }

//    private void add(Node node, E e) {
//        if (e.compareTo(root.e) == 0) {
//            return;
//        } else if (e.compareTo(root.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//            return;
//        } else if (e.compareTo(root.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//
//        if (e.compareTo(root.e) < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
//    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        E e;
        Node left, right;

        private Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private enum TraverseType {
        PREORDER, INORDER, LASTORDER
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 4, 6, 8};

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < arr.length; i++) {
            tree.add(arr[i]);
        }
        tree.preOrder();

        System.out.println(tree);
    }
}
