/*
 * InsertionSort.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;

public abstract class InsertionSort implements Sortable {
    // make implementation abstract to implement marker interface
    // and avoid creating instance of a class
    @Override
    public abstract Comparable[] sort(Comparable[] array);

    public static void sortArray(Comparable[] array) {
        if (array == null) {
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
