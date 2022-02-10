/*
 * InsertionSort.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.sortalgorithms;

import com.libalgojv.common.interfaces.Sortable;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class KnuthShuffle {
    private KnuthShuffle() {
    }

    public static <T> void shuffle(T @NotNull [] array) {
        for (int i = 0; i < array.length; i++) {
            Random rand = new Random();
            int r = rand.nextInt(i + 1);
            Sortable.exchange(array, i, r);
        }
    }
}