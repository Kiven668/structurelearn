package com.structure.stack;

import com.structure.linkedlist.LinkedList;

public class LinkListStack<E> implements Stack<E> {
    private LinkedList<E> list;

    public LinkListStack() {
        new LinkedList<E>();
    }

    public int getSize() {
        return list.getSize();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E e) {
        list.addFirst(e);
    }

    public E pop() {
        return list.removeFirst();
    }

    public E peek() {
        return list.getFirst();
    }
}
