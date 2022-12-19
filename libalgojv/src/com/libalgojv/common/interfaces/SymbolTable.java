package com.libalgojv.common.interfaces;

/*
 * SymbolTable.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

public interface SymbolTable<E> {
    void put(final String key, final E value);

    E get(final String key);

    void delete(final String key);

    boolean contains(String key);

    void clear();

    boolean isEmpty();

    int getSize();

    Iterable<String> getAllKeys();

    Iterable<String> getKeysWithPrefix(String prefix);

    String longestPrefixOf(String prefix);
}
