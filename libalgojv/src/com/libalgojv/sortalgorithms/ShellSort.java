/*
 * ShellSort.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;

public final class ShellSort implements Sortable {
    private ShellSort() {
    }

    public static void sort(Comparable[] array) {
        if (array == null) {
            return;
        }
        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                for (int j = i; j >= h && Sortable.less(array[j], array[j - h]); j -= h) {
                    Sortable.exchange(array, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
