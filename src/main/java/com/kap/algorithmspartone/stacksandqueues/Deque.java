package com.kap.algorithmspartone.stacksandqueues;

import java.util.*;

/**
 * @author Konstantinos Antoniou
 */
public class Deque<Item> {

    private Node head;
    private Node tail;

    private int size;

    public Deque() {
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public void addFirst(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }

        Node newHead = new Node(item);

        if(isEmpty()) {
            head = newHead;
            tail = head;
        } else {
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
        size++;
    }

    @SuppressWarnings("unchecked")
    public void addLast(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }

        Node newTail = new Node(item);

        if(isEmpty()) {
            tail = newTail;
            head = tail;
        } else {
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
        size++;
    }

    @SuppressWarnings("unchecked")
    public Item removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = (Item) head.item;
        head = head.next;

        if(isEmpty()) {
            tail = head;
        } else {
            head.prev = null;
        }

        size--;
        return item;
    }

    @SuppressWarnings("unchecked")
    public Item removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = (Item) tail.item;
        Node oldTail = tail;
        oldTail.prev = null;

        tail = tail.prev;

        if(tail == null) {
            head = tail;
        }

        size--;
        return item;
    }

    static class Node<Item> {
        Item item;
        Node next;
        Node prev;

        public Node(final Item item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }

}
