package com.libalgojv.sortalgorithms.tests;

import com.libalgojv.sortalgorithms.MergeSort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTests {
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
    void sortArray() {
        MergeSort.sortArray(null);
        fail("Not Implemented");
    }
}