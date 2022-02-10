package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;

import java.lang.reflect.Array;

public final class ImprovedMergeSort extends MergeSortBase {
    private ImprovedMergeSort() {
    }

    private static final int CUT_OFF = 7;

    private static <T> void sort(Comparable<T>[] array, Comparable<T>[] auxiliary, int low, int high) {
        if (high <= low) {
            return;
        }
        if (high <= low + CUT_OFF - 1) {
            InsertionSort.sort(array, low, high);
            return;
        }
        int middle = low + (high - low) / 2;
        sort(array, auxiliary, low, middle);
        sort(array, auxiliary, middle + 1, high);
        if (Sortable.less(array[middle], array[middle + 1])) {
            return;
        }
        // TODO: switch arrays to avoid copy
        merge(array, auxiliary, low, middle, high);
    }

    public static <T> void sort(Comparable<T>[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        Comparable[] auxiliary = (Comparable[]) Array.newInstance(Comparable.class, array.length);
        sort(array, auxiliary, 0, array.length - 1);
    }
}
