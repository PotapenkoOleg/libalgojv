/*
 * PriorityQueue.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

public interface PriorityQueue<Key extends Comparable<Key>> {
    void insert(final Key key);

    Key deleteMax();

    boolean isEmpty();

    Key peek();

    int getSize();
}
