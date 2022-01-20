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
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public final class SelectionSort {
    // Hide constructor
    private SelectionSort() {
    }

    public static <T> void sort(Comparable<T>[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (Sortable.less(array[j], array[min])) {
                    min = j;
                }
            }
            Sortable.exchange(array, i, min);
        }
    }
}
