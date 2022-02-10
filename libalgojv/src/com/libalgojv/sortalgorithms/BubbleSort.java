package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;

public final class BubbleSort {
    private BubbleSort() {
    }

    public static <T> void sort(Comparable<T>[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (Sortable.less(array[j], array[i])) {
                    Sortable.exchange(array, i, j);
                }
            }
        }
    }
}
