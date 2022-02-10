/*
 * MergeSort.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import java.lang.reflect.Array;

public final class MergeSort extends MergeSortBase {
    private MergeSort() {
    }

    private static <T> void sort(Comparable<T>[] array, Comparable<T>[] auxiliary, int low, int high) {
        if (high <= low) {
            return;
        }
        int middle = low + (high - low) / 2;
        sort(array, auxiliary, low, middle);
        sort(array, auxiliary, middle + 1, high);
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
