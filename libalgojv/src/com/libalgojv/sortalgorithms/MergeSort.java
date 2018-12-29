/*
 * MergeSort.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;

public abstract class MergeSort implements Sortable {
    // make implementation abstract to implement marker interface
    // and avoid creating instance of a class
    @Override
    public abstract Comparable[] sort(Comparable[] array);

    public static Comparable[] sortArray(Comparable[] array) {
        return MergeSort.sortArray(array, 0, array.length / 1, array.length);
    }

    public static Comparable[] sortArray(Comparable[] array, int low, int middle, int high) {
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
