package com.kap.algorithmspartone.stacksandqueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Konstantinos Antoniou
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;

    private int size;

    /**
     * Constructs an empty deque
     */
    public Deque() {
        // Intentionally Blank
    }

    /**
     * Checks if the deque is empty
     *
     * @return true if deque is empty and false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * @return size of the deque
     */
    public int size() {
        return size;
    }

    /**
     * Adds an item to the front of the deque
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> newHead = new Node<Item>(item);

        if (isEmpty()) {
            head = newHead;
            tail = head;
        } else {
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
        size++;
    }

    /**
     * Adds an item at the end of the deque
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> newTail = new Node<Item>(item);

        if (isEmpty()) {
            tail = newTail;
            head = tail;
        } else {
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
        size++;
    }

    /**
     * @return the item from the front of the deque and also removes it from the deque
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = head.item;
        head = head.next;

        if (isEmpty()) {
            tail = head;
        } else {
            head.prev = null;
        }

        size--;
        return item;
    }

    /**
     * @return the item from the end of the deque and also removes it from the deque
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = tail.item;
        tail = tail.prev;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        size--;
        return item;
    }

    /**
     * @return an iterator over the deque items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = head;

        /**
         * Checks whether there is a next element to return or not
         *
         * @return true if there is next element or false otherwise
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * @return the next element in the deque during an iteration of the elements if it exists. otherwise throws
         * a NoSuchElementException.
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }

        /**
         * this method is not supported for this exercise
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        /**
         * Constructs a node element in order to be put in the linked-list which is used to implement the deque data
         * structure.
         *
         * @param item to be put in the linked-list node element
         */
        public Node(final Item item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }

}
