package org.example;

import java.util.Iterator;
import java.util.LinkedList;

public class ll {
    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        Iterator<String> itr = ll.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
