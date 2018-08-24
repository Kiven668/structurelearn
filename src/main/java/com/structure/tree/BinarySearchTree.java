package com.structure.tree;

import com.structure.stack.LinkListStack;

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

    /**
     * @Description: preorder traversal with recursion
     * @Param: []
     * @return: void
     */
    public void preOrder() {
        order(TraverseType.PREORDER, root);
    }

    /** 
     * @Description: preorder traversal without recursion 
     * @Param: [] 
     * @return: void
     */ 
    public void preOrderNS() {
        if (root == null) return;

        LinkListStack<Node> stack = new LinkListStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * @Description: inorder traversal with recursion
     * @Param: []
     * @return: void
     */
    public void inOrder() {
        order(TraverseType.INORDER, root);
    }

    /**
     * @Description: inorder traversal without recursion
     * @Param: []
     * @return: void
     */
    public void inOrderNR() {
        if (root == null) return;

        LinkListStack<Node> stack = new LinkListStack<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            if (!stack.isEmpty()) {
                cur = stack.pop();
                System.out.println(cur.e);
                cur = cur.right;
            }
        }
    }

    private void order(TraverseType type, Node node) {
        if (node == null) return;

        if (type.equals(TraverseType.PREORDER))
            System.out.println(node.e);

        order(type, node.left);

        if (type.equals(TraverseType.INORDER))
            System.out.println(node.e);

        order(type, node.right);

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

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private enum TraverseType {
        PREORDER, INORDER, LASTORDER
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 2, 5, 6, 9};

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < arr.length; i++) {
            tree.add(arr[i]);
        }
        tree.preOrderNS();
        System.out.println("---------------");

        tree.inOrderNR();
    }
}
