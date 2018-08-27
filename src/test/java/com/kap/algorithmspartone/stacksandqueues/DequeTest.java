package com.kap.algorithmspartone.stacksandqueues;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Konstantinos Antoniou
 */
public class DequeTest {

    @Test
    public void testAddFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(5);
        assertEquals(3, deque.size());
    }

    @Test
    public void testAddLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(5);
        assertEquals(3, deque.size());
    }

    @Test
    public void testRemoveFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(5);
        assertEquals(5, (int) deque.removeFirst());
        assertEquals(3, (int) deque.removeFirst());
        assertEquals(2, (int) deque.removeFirst());
        assertEquals(0, deque.size());
    }

    @Test
    public void testRemoveLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(5);
        assertEquals(5, (int) deque.removeLast());
        assertEquals(3, (int) deque.removeLast());
        assertEquals(2, (int) deque.removeLast());
        assertEquals(0, deque.size());
    }

}
