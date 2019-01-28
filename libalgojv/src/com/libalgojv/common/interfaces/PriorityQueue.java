/*
 * PriorityQueue.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

public interface PriorityQueue<E extends Comparable<E>> {
    void insert(final E item);

    E delete();

    E peek();

    boolean isEmpty();

    int getSize();

    void clear();
}
