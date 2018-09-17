package com.kap.algorithmspartone.stacksandqueues;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Konstantinos Antoniou
 */
public class RandomizedQueueTest {

    @Test
    public void testQueueSizeIsZero() {
        RandomizedQueue queue = new RandomizedQueue();
        assertEquals(0, queue.size());
    }

    @Test
    public void testQueueIsEmpty() {
        RandomizedQueue queue = new RandomizedQueue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueueSizeIsOne() {
        RandomizedQueue queue = new RandomizedQueue();
        queue.enqueue(4);
        assertEquals(1, queue.size());
    }

    @Test
    public void testQueueSizeIsTwo() {
        RandomizedQueue queue = new RandomizedQueue();
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.dequeue();
        assertEquals(2, queue.size());
    }

    @Test
    public void testQueueIsEmptyAfterOperations() {
        RandomizedQueue queue = new RandomizedQueue();
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueueRemovedElementIsRandom() {
        RandomizedQueue queue = new RandomizedQueue();
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        Object test = queue.dequeue();
        System.out.println(test);
    }

    @Test
    public void testQueueSampledElementIsRandom() {
        RandomizedQueue queue = new RandomizedQueue();
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        Object test = queue.sample();
        System.out.println(test);
    }

    @Test
    public void testQueueIteratorReturnedElementsSequenceIsRandom() {
        RandomizedQueue queue = new RandomizedQueue();
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        for(Object item : queue) {
            System.out.println(item);
        }
    }

}
