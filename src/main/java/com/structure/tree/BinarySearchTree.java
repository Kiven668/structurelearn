package com.structure.tree;

import com.structure.stack.LinkListStack;

import java.util.LinkedList;
import java.util.Queue;

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

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<Node>();

        queue.add(root);
        while(!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) return null;

        if (e.compareTo(node.e) < 0) {
            //如果比当前节点小，递归从左子树中删除
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            //如果比当前节点大，递归从右子树中删除
            node.right = remove(node.right, e);
            return node;
        } else {
            //如果相等，则进行删除操作
            //1.先找出右子树的最小节点
            //2.把右子树的最小节点从右子树删除
            //3.用右子树的最小节点替换删除的节点
            //!!!!因为removeMin中执行了size--，但实际上min这个节点并没有从树种删除，所以不需要再进行size--操作
            Node newNode = minimum(node.right);
            newNode.right = removeMin(node.right);
            newNode.left = node.left;
            //也可以使用左子树的最大子节点替换当前节点
//            Node newNode = maxmum(node.left);
//            newNode.left = removeMax(node.left);
//            newNode.right = node.right;

            node.left = node.right = null;
            return newNode;
        }
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

    public E minimum() {
        if (root == null) {
            return null;
        }
        Node cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.e;
        //recursion way
//        Node node = minimum(root);
//        return node == null ? null : node.e;
    }

    private Node minimum(Node node) {
        if (node == null || node.left == null) {
            return node;
        }

        return minimum(node.left);
    }



    public E maxmum() {
//        if (root == null) {
//            return null;
//        }
//        Node cur = root;
//        while (cur.right != null) {
//            cur = cur.right;
//        }
//        return cur.e;
        //recursion way
        Node node = maxmum(root);
        return node == null ? null : node.e;
    }

    private Node maxmum(Node node) {
        if (node == null || node.right == null) {
            return node;
        }

        return maxmum(node.right);
    }

    public E removeMin() {
        E e = minimum();

        root = removeMin(root);

        return e;
    }

    /**
     * @Description: 删除节点下的最小子节点
     * @Param: [node]
     * @return: com.structure.tree.BinarySearchTree<E>.Node
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);

        return node;
    }
    /**
     * @Description: 删除节点下的最大子节点
     * @Param: [node]
     * @return: com.structure.tree.BinarySearchTree<E>.Node
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMin(node.right);

        return node;
    }

    private enum TraverseType {
        PREORDER, INORDER, LASTORDER
    }

    public static void main(String[] args) {
        int[] arr = {40, 4, 15 ,23, 56, 78, 32, 49, 60, 68};

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < arr.length; i++) {
            tree.add(arr[i]);
        }
        tree.preOrderNS();
        System.out.println("---------------");

        tree.levelOrder();
        System.out.println("Minimum value : " + tree.minimum());
        System.out.println("Maxmum value : " + tree.maxmum());

        tree.remove(56);
        tree.preOrderNS();
    }
}
