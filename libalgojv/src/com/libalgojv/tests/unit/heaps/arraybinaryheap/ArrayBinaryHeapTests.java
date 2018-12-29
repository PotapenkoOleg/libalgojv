/*
 * ArrayBinaryHeapTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.heaps.arraybinaryheap;

import com.libalgojv.heaps.arraybinaryheap.ArrayBinaryHeap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBinaryHeapTests {
    private ArrayBinaryHeap<Integer> arrayBinaryHeap;

    @BeforeEach
    void setUp() {
        arrayBinaryHeap = new ArrayBinaryHeap<>(50);
    }

    @AfterEach
    void tearDown() {
        arrayBinaryHeap = null;
    }

    @Test
    void insert() {
        fail("Not Implemented");
    }

    @Test
    void deleteMax() {
        fail("Not Implemented");
    }

    @Test
    void isEmpty() {
        fail("Not Implemented");
    }

    @Test
    void peek() {
        fail("Not Implemented");
    }

    @Test
    void size() {
        fail("Not Implemented");
    }
}