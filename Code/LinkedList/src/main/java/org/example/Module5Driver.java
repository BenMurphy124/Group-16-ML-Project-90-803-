package org.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 17-683 Data Structures for Application Programmers.
 * Module 5 LinkedList
 * @author Terry Lee
 */
public class Module5Driver {

    /**
     * Simple test program to demo LinkedList methods.
     * @param args arguments
     */
    public static void main(String[] args) {
        List<Integer> numbers = new LinkedList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        Iterator<Integer> itr = numbers.iterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        System.out.println();

        SinglyLinkedList<String> linkedList = new SinglyLinkedList<String>();
        linkedList.addLast("data");
        linkedList.addLast("strutures");
        linkedList.addLast("rock");
        linkedList.addLast("the");
        linkedList.addLast("world");
        linkedList.addFirst("way");
        linkedList.insertAfter("way", "to");
        linkedList.insertAfter("hello", "so");
        linkedList.insertBefore("data", "go");
        linkedList.remove("the");



        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

}
