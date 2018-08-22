package com.structure.linkedlist;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList();

        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
        }
        System.out.println(list.toString());

        list.remove(1);
        System.out.println(list.toString());

        list.removeFirst();
        System.out.println(list.toString());
    }
}
