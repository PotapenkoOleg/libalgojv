/*
 * ImprovedMergeSortTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;
import com.libalgojv.sortalgorithms.ImprovedMergeSort;
import com.libalgojv.sortalgorithms.KnuthShuffle;
import com.libalgojv.sortalgorithms.StableSortKeyWrapper;
import com.libalgojv.sortalgorithms.StableSortWrapperArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImprovedMergeSortTest {
    private Integer[] sortedArray;
    private Integer[] unsortedArray;
    private Integer[] reverseSortedArray;

    private Character[] sortExampleActual;
    private Character[] sortExampleExpected;

    @BeforeEach
    void setUp() {
        sortedArray = new Integer[]{1, 2, 3};
        unsortedArray = new Integer[]{1, 3, 2};
        reverseSortedArray = new Integer[]{3, 2, 1};

        sortExampleActual = new Character[]{'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};
        sortExampleExpected = new Character[]{'A', 'E', 'E', 'L', 'M', 'O', 'P', 'R', 'S', 'T', 'X'};
    }

    @AfterEach
    void tearDown() {
        sortedArray = null;
        unsortedArray = null;
        reverseSortedArray = null;

        sortExampleActual = null;
        sortExampleExpected = null;
    }

    @Test
    void sortArray() {
        ImprovedMergeSort.sort(sortedArray);
        assertTrue(Sortable.isSorted(sortedArray));

        ImprovedMergeSort.sort(unsortedArray);
        assertTrue(Sortable.isSorted(unsortedArray));

        ImprovedMergeSort.sort(reverseSortedArray);
        assertTrue(Sortable.isSorted(reverseSortedArray));
    }

    @Test
    void sortExample() {
        KnuthShuffle.shuffle(sortExampleActual);
        ImprovedMergeSort.sort(sortExampleActual);
        assertArrayEquals(sortExampleExpected, sortExampleActual);
    }

    @Test
    void stableSortExample() {
        StableSortKeyWrapper<Character>[] sortExampleStableActual =
                StableSortWrapperArray.toStableSortWrapperArray(sortExampleActual);
        ImprovedMergeSort.sort(sortExampleStableActual);
        Object[] sortExampleActual = StableSortWrapperArray.toObjectTypeArray(sortExampleStableActual);
        assertArrayEquals(sortExampleExpected, sortExampleActual);
    }
}