package com.structure.linkedlist;

public class LinkedList<E> {
    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void add(int index, E e) {
        if (index  < 0 || index  > size) {
            throw new IllegalArgumentException("Index out of bound");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = new Node(e, pre.next);
        size++;
    }

    public E get(int index) {
        if (index  < 0 || index  >= size) {
            throw new IllegalArgumentException("Index out of bound");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while(cur != null) {
            if (e.equals(cur.e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index  < 0 || index  >= size) {
            throw new IllegalArgumentException("Index out of bound");
        }

        Node pre = dummyHead;

        for (int i = 0; i < index; i++) {
            pre = dummyHead.next;
        }
        Node delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;
        size--;

        return delNode.e;
    }

    public E removeFirst() {
        return  remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList: ");

        Node cur = dummyHead.next;
        while(cur != null) {
            res.append(cur.e + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return  res.toString();
    }

    private class Node {
        E e;
        Node next;

        Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        Node(E e) {
            this(e, null);
        }

        Node() {
            this(null, null);
        }
    }
}
