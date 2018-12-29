/*
 * HeapSort.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;

public final class HeapSort implements Sortable {
    private HeapSort() {
    }

    public static Comparable[] sort(Comparable[] array) {
        if (array == null) {
            return null;
        }
        throw new UnsupportedOperationException();
    }
}
