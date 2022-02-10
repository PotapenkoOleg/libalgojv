/*
 * BottomUpMergeSort.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import java.lang.reflect.Array;

public final class BottomUpMergeSort extends MergeSortBase {
    private BottomUpMergeSort() {
    }

    public static <T> void sort(Comparable<T>[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        Comparable[] auxiliary = (Comparable[]) Array.newInstance(Comparable.class, array.length);
        for (int size = 1; size < array.length; size = size + size) {
            for (int low = 0; low < array.length - size; low += size + size) {
                merge(array, auxiliary, low, low + size - 1, Math.min(low + size + size - 1, array.length - 1));
            }
        }
    }
}
