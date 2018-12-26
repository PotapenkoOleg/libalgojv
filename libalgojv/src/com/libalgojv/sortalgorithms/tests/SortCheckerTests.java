/*
 * SortCheckerTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortCheckerTests {
    private final int[] sortedArray = new int[]{1, 2, 3};
    private final int[] unsortedArray = new int[]{1, 3, 2};
    private final int[] reverseSortedArray = new int[]{3, 2, 1};

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isSorted() {
        boolean actual = SortChecker.isSorted(sortedArray);
        assertTrue(actual);

        actual = SortChecker.isSorted(unsortedArray);
        assertFalse(actual);

        actual = SortChecker.isSorted(reverseSortedArray);
        assertFalse(actual);
    }
}