package com.kap.algorithmspartone.stacksandqueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Konstantinos Antoniou
 */
public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue randomizedQueue = new RandomizedQueue();

        int stringsNo = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randomizedQueue.enqueue(item);
        }

        for(int i = 0; i < stringsNo; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }

}
