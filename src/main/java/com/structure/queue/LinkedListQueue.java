package com.structure.queue;

public class LinkedListQueue<E> implements Queue<E> {
    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
        size = 0;
    }

    public LinkedListQueue() { this(null, null); }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Node ret = head;
        head = head.next;
        ret.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return ret.e;
    }

    public void enqueue(E e) {
        if (tail == null) {
            head = tail = new Node(e);
        } else {
            tail = tail.next = new Node(e);
        }
        size++;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur.e + "->");
            cur = cur.next;
        }
        res.append("NULL tail");

        return  res.toString();
    }

    private class Node {
        E e;
        Node next;

        Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        Node(E e) { this(e, null); }

        Node() { this(null, null);  }
    }
}
