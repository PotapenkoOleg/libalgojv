package com.libalgojv.common.interfaces;

public interface OrderedSymbolTable<Key extends Comparable<Key>, Value> {

    /**
     * Add key-value pair to the table
     *
     * @param key   Key to be inserted
     * @param value Value associated with key
     */
    void add(Key key, Value value);

    /**
     * Get value for the key
     *
     * @param key Key to search for
     * @return
     */
    Value get(Key key);

    /**
     * Remove kay-value pair from symbol table
     *
     * @param key
     */
    void remove(Key key);

    /**
     * Checks for key-value pair presence in table
     *
     * @param key
     * @return
     */
    boolean contains(Key key);

    /**
     * Check is the table empty
     *
     * @return
     */
    boolean isEmpty();

    /**
     * Returns number of key-value pairs
     *
     * @return
     */
    int getSize();

    /**
     * Number of keys between lo and hi
     *
     * @param low
     * @param high
     * @return
     */
    int getSize(Key low, Key high);

    /**
     * Gets smallest key
     *
     * @return
     */
    Key getMin();

    /**
     * Gets largest key
     *
     * @return
     */
    Key getMax();

    /**
     * Lagrgest key less than or equals to key
     *
     * @param key
     * @return
     */
    Key getFloor(Key key);

    /**
     * Smallest key greater than or equal to key
     *
     * @param key
     * @return
     */
    Key getCeiling(Key key);

    /**
     * Number of keys less than key
     *
     * @return
     */
    int getRank();

    /**
     * Key of rank k
     *
     * @param rank
     * @return
     */
    Key select(int rank);

    /**
     * Deletes  smallest key
     */
    void deleteMin();

    /**
     * Deletes largest key
     */
    void deleteMax();


    /**
     * Keys lo .. hi in sorted order
     *
     * @param low
     * @param high
     * @return
     */
    Iterable<Key> getKeys(Key low, Key high);

    /**
     * All keys in the table in sorted order
     *
     * @return
     */
    Iterable<Key> getAllKeys();
}
