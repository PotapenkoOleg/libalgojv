package com.libalgojv.common.interfaces;

public interface SymbolTable<Value> {
    void put(final String key, final Value value);

    Value get(final String key);

    void delete(final String key);

    boolean contains(String key);

    void clear();

    boolean isEmpty();

    int getSize();

    Iterable<String> getAllKeys();

    Iterable<String> getKeysWithPrefix(String prefix);

    String[] wildcardMatch(String key);

    String longestPrefixOf(String prefix);
}
