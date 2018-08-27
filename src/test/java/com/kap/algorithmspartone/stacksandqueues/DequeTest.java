package com.kap.algorithmspartone.stacksandqueues;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

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
        assertTrue(deque.size() == 3);
    }



}
