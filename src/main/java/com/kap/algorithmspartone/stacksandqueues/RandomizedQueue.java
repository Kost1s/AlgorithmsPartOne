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

        int index = getRandomIndex(size);
        Item item = randomQueue[index];
        randomQueue[index] = randomQueue[size - 1];
        randomQueue[size - 1] = null;
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

        return randomQueue[getRandomIndex(size)];
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

    private int getRandomIndex(int arraySize) {
        return StdRandom.uniform(arraySize);
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] iteratorArray;
        private int iteratorArraySize;

        private RandomizedQueueIterator() {
            iteratorArraySize = size;
            iteratorArray = arrayCopy(size);
        }

        public boolean hasNext() {
            return iteratorArraySize > 0;
        }

        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            int index = getRandomIndex(iteratorArraySize);
            Item item = iteratorArray[index];
            iteratorArray[index] = iteratorArray[iteratorArraySize - 1];
            iteratorArray[iteratorArraySize - 1] = null;
            iteratorArraySize--;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
