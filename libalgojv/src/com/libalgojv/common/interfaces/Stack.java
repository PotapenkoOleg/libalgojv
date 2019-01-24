/*
 * Stack.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

public interface Stack<E> extends Iterable<E> {
    E pop();

    void push(final E item);

    E peek();

    void clear();

    boolean isEmpty();

    int getSize();
}
