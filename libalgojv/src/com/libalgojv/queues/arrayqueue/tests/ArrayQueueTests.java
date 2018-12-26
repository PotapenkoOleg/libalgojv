/*
 * ArrayQueueTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.queues.arrayqueue.tests;

import com.libalgojv.common.interfaces.Queue;
import com.libalgojv.queues.arrayqueue.ArrayQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTests {
    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new ArrayQueue<>();
    }

    @AfterEach
    void tearDown() {
        queue = null;
    }

    @Test
    void enqueue() {
        Integer expected = 42;
        queue.enqueue(expected);
        queue.enqueue(expected - 1);
        queue.enqueue(expected - 2);
        Integer actual = queue.dequeue();
        assertEquals(expected, actual);
    }

    @Test
    void dequeue() {
        Integer actual = queue.dequeue();
        assertNull(actual);

        Integer expected = 42;
        queue.enqueue(expected);
        actual = queue.dequeue();
        assertEquals(expected, actual);

        queue.enqueue(expected);
        queue.enqueue(expected - 1);
        actual = queue.dequeue();
        assertEquals(expected, actual);
    }

    @Test
    void isEmpty() {
        boolean actual = queue.isEmpty();
        assertTrue(actual);

        queue.enqueue(42);
        actual = queue.isEmpty();
        assertFalse(actual);
    }

    @Test
    void size() {
        queue.enqueue(42);
        queue.enqueue(42);
        queue.enqueue(42);
        queue.dequeue();

        int expected = 2;
        int actual = queue.getSize();

        assertEquals(expected, actual);
    }

    @Test
    void toStringTest() {
        final int item = 42;
        queue.enqueue(item);
        queue.enqueue(item + 1);
        queue.enqueue(item + 2);
        String actual = queue.toString();
        String expected = "42\n43\n44\n";
        assertEquals(expected, actual);
    }

    @Test
    void iterator() {
        final Integer expected = 42;
        queue.enqueue(expected);
        queue.enqueue(expected + 1);
        Iterator<Integer> iterator = queue.iterator();

        assertTrue(iterator.hasNext());
        Integer actual = iterator.next();
        assertEquals(java.util.Optional.of(expected), java.util.Optional.of(actual));

        assertTrue(iterator.hasNext());
        actual = iterator.next();
        assertEquals(java.util.Optional.of(expected + 1), java.util.Optional.of(actual));

        assertFalse(iterator.hasNext());
    }

    @Test
    void forEach() {
        StringBuilder stringBuilder = new StringBuilder();
        final int item = 42;
        queue.enqueue(item);
        queue.enqueue(item + 1);
        queue.enqueue(item + 2);
        queue.forEach(stringBuilder::append);
        String actual = stringBuilder.toString();
        String expected = "424344";
        assertEquals(expected, actual);
    }

    @Test
    void dynamicSizeIncrease() {
        if (!(queue instanceof ArrayQueue)) {
            fail("Invalid queue type");
        }
        ArrayQueue currentQueue = (ArrayQueue) queue;
        int expected = 2;
        int actual = currentQueue.getCapacity();
        assertEquals(expected, actual);
        queue.enqueue(42);
        queue.enqueue(42);
        queue.enqueue(42);
        expected = 4;
        actual = currentQueue.getCapacity();
        assertEquals(expected, actual);
        queue.enqueue(44);
        queue.enqueue(44);
        expected = 8;
        actual = currentQueue.getCapacity();
        assertEquals(expected, actual);
    }

    @Test
    void dynamicSizeDecrease() {
        if (!(queue instanceof ArrayQueue)) {
            fail("Invalid queue type");
        }
        ArrayQueue currentQueue = (ArrayQueue) queue;
        int expected = 2;
        int actual = currentQueue.getCapacity();
        assertEquals(expected, actual);
        for (int i = 0; i < 8; i++) {
            queue.enqueue(42);
        }
        expected = 8;
        actual = currentQueue.getCapacity();
        assertEquals(expected, actual);
        for (int i = 0; i < 6; i++) {
            queue.dequeue();
        }
        expected = 4;
        actual = currentQueue.getCapacity();
        assertEquals(expected, actual);
        queue.dequeue();
        queue.dequeue();
        expected = 2;
        actual = currentQueue.getCapacity();
        assertEquals(expected, actual);
    }
}