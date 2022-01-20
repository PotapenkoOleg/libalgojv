/*
 * SortableTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortableTests {
    private final int[] sortedArray = new int[]{1, 2, 3};
    private final int[] unsortedArray = new int[]{1, 3, 2};
    private final int[] reverseSortedArray = new int[]{3, 2, 1};

    private final Integer[] sortedArrayComparable = new Integer[]{1, 2, 3};
    private final Integer[] unsortedArrayComparable = new Integer[]{1, 3, 2};
    private final Integer[] reverseSortedArrayComparable = new Integer[]{3, 2, 1};

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

//    @Test
//    void isSorted() {
//        boolean actual = Sortable.isSorted(sortedArray);
//        assertTrue(actual);
//
//        actual = Sortable.isSorted(unsortedArray);
//        assertFalse(actual);
//
//        actual = Sortable.isSorted(reverseSortedArray);
//        assertFalse(actual);
//    }

    @Test
    void isSortedComparable() {
        boolean actual = Sortable.isSorted(sortedArrayComparable);
        assertTrue(actual);

        actual = Sortable.isSorted(unsortedArrayComparable);
        assertFalse(actual);

        actual = Sortable.isSorted(reverseSortedArrayComparable);
        assertFalse(actual);
    }
}