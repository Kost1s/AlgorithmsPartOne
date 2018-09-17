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
        int initSize = 1;
        randomQueue = (Item[]) new Object[initSize];
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

        Item item = getAndRemoveRandomItem(randomQueue, size);
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

    private Item getAndRemoveRandomItem(Item[] array, int arraySize) {
        int index = getRandomIndex(arraySize);
        Item item = array[index];
        array[index] = array[arraySize - 1];
        array[arraySize - 1] = null;
        return item;
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

            Item item = getAndRemoveRandomItem(iteratorArray, iteratorArraySize);
            iteratorArraySize--;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
