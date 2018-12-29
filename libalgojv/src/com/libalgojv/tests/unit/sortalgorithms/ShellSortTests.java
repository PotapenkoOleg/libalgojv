/*
 * ShellSortTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.sortalgorithms;

import com.libalgojv.sortalgorithms.InsertionSort;
import com.libalgojv.sortalgorithms.ShellSort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShellSortTests {
    private final Integer[] sortedArray = new Integer[]{1, 2, 3};
    private final Integer[] unsortedArray = new Integer[]{1, 3, 2};
    private final Integer[] reverseSortedArray = new Integer[]{3, 2, 1};

    private final Character[] sortExample = new Character[]{'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sortArray() {
        Integer[] actual = (Integer[]) ShellSort.sortArray(sortedArray);
        assertTrue(SortChecker.isSorted(actual));

        actual = (Integer[]) ShellSort.sortArray(unsortedArray);
        assertTrue(SortChecker.isSorted(actual));

        actual = (Integer[]) ShellSort.sortArray(unsortedArray);
        assertTrue(SortChecker.isSorted(actual));
    }

    @Test
    void sortExample() {
        Character[] expected = new Character[]{'A', 'E', 'E', 'L', 'M', 'O', 'P', 'R', 'S', 'T', 'X'};
        Character[] actual = (Character[]) ShellSort.sortArray(sortExample);
        assertTrue(Arrays.equals(expected, actual));
    }
}