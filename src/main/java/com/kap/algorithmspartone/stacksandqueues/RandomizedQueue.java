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

    /**
     * Constructs an empty queue of size 1.
     * It is initialized to 1 in order not to throw an array out of bounds exceptions during the very first insertion.
     */
    public RandomizedQueue() {
        int initSize = 1;
        randomQueue = (Item[]) new Object[initSize];
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty or false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the size of the queue
     */
    public int size() {
        return size;
    }

    /**
     * Adds an item to the end of the queue.
     *
     * @param item item to be added in the queue
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == randomQueue.length) {
            resize(2 * randomQueue.length);
        }

        randomQueue[size++] = item;
    }

    /**
     * @return and remove a random item from the queue
     */
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

    /**
     * @return a random item from the queue but it does not remove it
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return randomQueue[getRandomIndex(size)];
    }

    /**
     * @return an independent iterator over the queue items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] iteratorArray;
        private int iteratorArraySize;

        /**
         * Constructs a new queue iterator based on the size of the current size of the original queue.
         */
        private RandomizedQueueIterator() {
            iteratorArraySize = size;
            iteratorArray = arrayCopy(size);
        }

        /**
         * Checks whether there is a next item to return during an iteration.
         *
         * @return true if there is a next item or false otherwise.
         */
        @Override
        public boolean hasNext() {
            return iteratorArraySize > 0;
        }

        /**
         * @return the next item in the iteration sequence if there is a next one to return
         */
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = getAndRemoveRandomItem(iteratorArray, iteratorArraySize);
            iteratorArraySize--;

            return item;
        }

        /**
         * This method is not supported in the scope of this exercise.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Resizes a given array based on the capacity provided.
     *
     * @param capacity target length of the array to be resized.
     */
    private void resize(int capacity) {
        randomQueue = arrayCopy(capacity);
    }

    /**
     * Performs a copy of the array used to implement the queue using the capacity provided.
     *
     * @param capacity target length of the result array after the copy operation
     * @return an array produced from the current original array used to implement the queue structure resized to the
     * capacity specified
     */
    private Item[] arrayCopy(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            copy[i] = randomQueue[i];
        }

        return copy;
    }

    /**
     * @param arraySize size of the array from which a random index has to be drawn
     * @return a random index based on the array size specified
     */
    private int getRandomIndex(int arraySize) {
        return StdRandom.uniform(arraySize);
    }

    /**
     * Helper method to get and subsequently remove a random element from the array specified.
     *
     * @param array     array from which the random element has to be drawn
     * @param arraySize array size for the array specified
     * @return an random item from the array specified
     */
    private Item getAndRemoveRandomItem(Item[] array, int arraySize) {
        int index = getRandomIndex(arraySize);
        Item item = array[index];
        array[index] = array[arraySize - 1];
        array[arraySize - 1] = null;
        return item;
    }

}
