/*
 * HashTable.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

public interface HashTable<Key, Value> {
    void add(final Key key, final Value value);

    Value get(final Key key);

    Value remove(final Key key);

    boolean isEmpty();

    int getSize();

    void clear();
}
