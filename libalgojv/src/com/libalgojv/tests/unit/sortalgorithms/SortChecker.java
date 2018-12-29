/*
 * SortChecker.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.sortalgorithms;

final class SortChecker {
    static boolean isSorted(final Comparable[] array) {
        if ((array == null) || (array.length < 2)) {
            return false;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) == -1) {
                return false;
            }
        }
        return true;
    }

    static boolean isSorted(final int[] array) {
        if ((array == null) || (array.length < 2)) {
            return false;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] >= array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
