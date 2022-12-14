package com.libalgojv.common.interfaces;

import com.libalgojv.common.dto.KeyValuePair;

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

    Iterable<KeyValuePair<String, E>> getKeyValuePairsWithPrefix(String prefix);

    String longestPrefixOf(String prefix);
}
