/*
 * BagKV.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

public interface BagKeyValue<Key, Value> extends Iterable<Value> {
    void add(final Key key, final Value value);

    boolean isEmpty();

    int getSize();
}
