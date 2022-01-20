/*
 * InsertionSort.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;

public final class InsertionSort {
    private InsertionSort() {
    }

    public static <T> void sort(Comparable<T>[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (Sortable.less(array[j], array[j - 1])) {
                    Sortable.exchange(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
