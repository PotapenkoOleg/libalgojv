/*
 * Sortable.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

public interface Sortable {

    static <T> boolean less(Comparable<T> left, Comparable<T> right) {
        return left.compareTo((T) right) < 0;
    }

    static <T> void exchange(Comparable<T>[] a, int i, int j) {
        Comparable<T> swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    static <T> boolean isSorted(final Comparable<T>[] array) {
        if ((array == null) || (array.length < 2)) {
            return false;
        }
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
