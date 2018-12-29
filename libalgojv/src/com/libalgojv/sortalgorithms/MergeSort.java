/*
 * MergeSort.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;

public final class MergeSort implements Sortable {
    private MergeSort() {
    }

    public static Comparable[] sort(Comparable[] array) {
        if (array == null) {
            return null;
        }
        return MergeSort.sort(array, 0, array.length / 1, array.length);
    }

    public static Comparable[] sort(Comparable[] array, int low, int middle, int high) {
        if (array == null) {
            return null;
        }
        Comparable[] result = new Comparable[array.length];
        int i = low;
        int j = middle + 1;
        for (int k = low; k <= high; k++) {
            if (i > middle) {
                result[k] = array[j++];
            } else if (j > high) {
                result[k] = array[i++];
            } else if (Sortable.less(array[j], array[i])) {
                result[k] = array[j++];
            } else result[k] = array[i++];
        }
        return result;
    }
}
