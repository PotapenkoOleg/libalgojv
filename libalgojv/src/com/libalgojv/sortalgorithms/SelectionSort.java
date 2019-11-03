/*
 * SelectionSort.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;

import java.util.Comparator;

public final class SelectionSort implements Sortable {
    // Hide constructor
    private SelectionSort() {
    }

    private static class StableSelectionSortKey<Key extends Comparable> {
        private Key key;
        private int sequenceNumber;

        StableSelectionSortKey(Key key, int sequenceNumber) {
            this.key = key;
            this.sequenceNumber = sequenceNumber;
        }

        Key getKey() {
            return key;
        }

        int getSequenceNumber() {
            return sequenceNumber;
        }
    }

    private static class StableSelectionSortComparator<T> implements Comparator<T> {
        @Override
        public int compare(StableSelectionSortKey left, StableSelectionSortKey right) {
            int result = left.getKey().compareTo(right.getKey());
            if (result == 0) {
                result = Integer.compare(left.getSequenceNumber(), right.getSequenceNumber());
            }
            return result;
        }
    }

    public static void sort(Comparable[] array) {
        if (array == null) {
            return;
        }
        sort(array, Comparable::compareTo);
    }

    private static void sort(Comparable[] array, Comparator<Comparable> comparator) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[j], array[min]) < 0) {
                    min = j;
                }
            }
            Sortable.exchange(array, i, min);
        }
    }

    public static void stableSort(Comparable[] array) {
        if (array == null) {
            return;
        }

        int sequenceNumber = 0;
        StableSelectionSortKey[] newArray = new StableSelectionSortKey[array.length];
        for (Comparable element : array) {
            StableSelectionSortKey wrapper = new StableSelectionSortKey(element, sequenceNumber);
            newArray[sequenceNumber] = wrapper;
            sequenceNumber++;
        }

        sort(array, new StableSelectionSortComparator<StableSelectionSortKey>());

        sequenceNumber = 0;
        for (StableSelectionSortKey element : newArray) {
            array[sequenceNumber] = newArray[sequenceNumber].getKey();
        }
    }
}
