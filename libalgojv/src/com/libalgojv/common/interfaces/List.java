/*
 * List.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

import com.libalgojv.common.enums.InsertPosition;
import com.libalgojv.common.enums.RemovePosition;

public interface List<Key, Value> extends Iterable<Value> {
    void add(final Key key, final Value value, final InsertPosition position);

    void add(final Key key, final Value value, final Key before);

    Value remove(final RemovePosition position);

    Value remove(final Key key);

    Value get(final Key key);

    void clear();

    boolean isEmpty();

    int getSize();
}
