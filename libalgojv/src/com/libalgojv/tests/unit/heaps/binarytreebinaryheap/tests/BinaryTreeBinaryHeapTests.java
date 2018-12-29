/*
 * BinaryTreeBinaryHeapTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.heaps.binarytreebinaryheap.tests;

import com.libalgojv.heaps.binarytreebinaryheap.BinaryTreeBinaryHeap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeBinaryHeapTests {
    private BinaryTreeBinaryHeap<Integer> binaryTreeBinaryHeap = null;

    @BeforeEach
    void setUp() {
        binaryTreeBinaryHeap = new BinaryTreeBinaryHeap<>();
    }

    @AfterEach
    void tearDown() {
        binaryTreeBinaryHeap = null;
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