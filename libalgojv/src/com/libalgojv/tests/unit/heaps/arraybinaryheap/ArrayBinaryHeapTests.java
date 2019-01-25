/*
 * ArrayBinaryHeapTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.heaps.arraybinaryheap;

import com.libalgojv.common.enums.BinaryHeapType;
import com.libalgojv.common.interfaces.PriorityQueue;
import com.libalgojv.heaps.arraybinaryheap.ArrayBinaryHeap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBinaryHeapTests {
    private PriorityQueue<Character> priorityQueue = null;

    @BeforeEach
    void setUp() {
        priorityQueue = new ArrayBinaryHeap<>(BinaryHeapType.MAX);
    }

    @AfterEach
    void tearDown() {
        priorityQueue = null;
    }

    @Test
    void insert() {
        String testData = "TPRNHOAEIG";
        for (int i = 0; i < testData.length(); i++) {
            Character current = testData.charAt(i);
            priorityQueue.insert(current);
        }
        priorityQueue.insert('S');
        priorityQueue.delete();
        priorityQueue.delete();
        priorityQueue.insert('S');

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < testData.length(); i++) {
            stringBuilder.append(priorityQueue.delete());
        }
        // characters should appear in sorted order
        String expected = "SRPONIHGEA";
        String actual = stringBuilder.toString();
        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        assertNull(priorityQueue.delete());

        String testData = "GIEAOHNRPT";
        for (int i = 0; i < testData.length(); i++) {
            Character current = testData.charAt(i);
            priorityQueue.insert(current);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < testData.length(); i++) {
            stringBuilder.append(priorityQueue.delete());
        }
        // characters should appear in sorted order
        String expected = "TRPONIHGEA";
        String actual = stringBuilder.toString();
        assertEquals(expected, actual);
    }

    @Test
    void isEmpty() {
        assertTrue(priorityQueue.isEmpty());
        String testData = "GIEAOHNRPT";
        for (int i = 0; i < testData.length(); i++) {
            Character current = testData.charAt(i);
            priorityQueue.insert(current);
        }
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    void peek() {
        assertNull(priorityQueue.peek());

        String testData = "GIEAOHNRPT";
        for (int i = 0; i < testData.length(); i++) {
            Character current = testData.charAt(i);
            priorityQueue.insert(current);
        }
        Character expected = 'T';
        Character actual = priorityQueue.peek();
        assertEquals(expected, actual);
    }

    @Test
    void getSize() {
        int actual = priorityQueue.getSize();
        assertEquals(0, actual);

        String testData = "GIEAOHNRPT";
        for (int i = 0; i < testData.length(); i++) {
            Character current = testData.charAt(i);
            priorityQueue.insert(current);
        }
        int expected = 10;
        actual = priorityQueue.getSize();
        assertEquals(expected, actual);
    }

    @Test
    void clear() {
        assertTrue(priorityQueue.isEmpty());
        String testData = "GIEAOHNRPT";
        for (int i = 0; i < testData.length(); i++) {
            Character current = testData.charAt(i);
            priorityQueue.insert(current);
        }
        assertFalse(priorityQueue.isEmpty());
        priorityQueue.clear();
        assertTrue(priorityQueue.isEmpty());
        int expected = 0;
        int actual = priorityQueue.getSize();
        assertEquals(expected, actual);
    }

    @Test
    void dynamicSizeIncrease() {
        fail("Not Implemented");
    }

    @Test
    void dynamicSizeDecrease() {
        fail("Not Implemented");
    }
}