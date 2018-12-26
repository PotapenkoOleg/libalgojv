/*
 * Queue.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

public interface Queue<E> extends Iterable<E> {
    void enqueue(final E item);

    E dequeue();

    boolean isEmpty();

    int getSize();
}
