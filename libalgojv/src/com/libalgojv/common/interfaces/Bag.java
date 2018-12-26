/*
 * Bag.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

public interface Bag<E> extends Iterable<E> {
    void add(final E item);

    boolean isEmpty();

    int getSize();
}
