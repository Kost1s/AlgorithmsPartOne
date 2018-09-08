package com.kap.algorithmspartone.stacksandqueues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Konstantinos Antoniou
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] randomQueue;
    private int size = 0;

    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[size];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == randomQueue.length) {
            resize(2 * randomQueue.length);
        }

        randomQueue[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = getRandomIndex();
        Item item = randomQueue[index];
        randomQueue[index] = null;
        size--;

        if ((size > 0) && (size == (randomQueue.length / 4))) {
            resize(randomQueue.length / 2);
        }

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return randomQueue[getRandomIndex()];
    }

    private void resize(int capacity) {
        randomQueue = arrayCopy(capacity);
    }

    private Item[] arrayCopy(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            copy[i] = randomQueue[i];
        }

        return copy;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        public boolean hasNext() {
            return !isEmpty();
        }

        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return sample();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private int getRandomIndex() {
        return StdRandom.uniform(0, size-1);
    }

}
