/*
 * HashTable.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

public interface HashTable<Key, Value> {
    void add(Key key, Value value);

    Value get(Key key);

    Value remove(Key key);

    boolean isEmpty();

    int getSize();

    void clear();
}
