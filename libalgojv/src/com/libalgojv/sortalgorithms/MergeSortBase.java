/*
 * MergeSortBase.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;

class MergeSortBase {
    protected static <T> void merge(
            Comparable<T>[] array,
            Comparable<T>[] auxiliary,
            int low,
            int middle,
            int high
    ) {
        if (high + 1 - low >= 0) {
            System.arraycopy(array, low, auxiliary, low, high + 1 - low);
        }
        int i = low;
        int j = middle + 1;
        for (int k = low; k <= high; k++) {
            if (i > middle) {
                array[k] = auxiliary[j++];
            } else if (j > high) {
                array[k] = auxiliary[i++];
            } else if (Sortable.less(auxiliary[j], auxiliary[i])) {
                array[k] = auxiliary[j++];
            } else {
                array[k] = auxiliary[i++];
            }
        }
    }
}
