/*
 * HashTableLinearProbing.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.hashtables.hashtablelinearprobing;

import com.libalgojv.common.interfaces.HashTable;

public class HashTableLinearProbing<Key, Value> implements HashTable<Key, Value> {
    //#region Private Fields
    private final static int DEFAULT_CAPACITY = 16; // should be power of two
    private int size = 0;
    private int capacity;
    private Key[] keys;
    private Value[] values;
    //#endregion

    //#region Constructors
    public HashTableLinearProbing() {
        this(DEFAULT_CAPACITY);
    }

    public HashTableLinearProbing(int capacity) {
        this.capacity = capacity;
        values = (Value[]) new Object[capacity];
        keys = (Key[]) new Object[capacity];
    }
    //#endregion

    //#region Public Methods
    @Override
    public void add(Key key, Value value) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        if (add(key, value, keys, values)) {
            size++;
        }
    }

    @Override
    public Value get(Key key) {
        int hashCode = getHashCode(key);
        for (
                int counter = 0;
                keys[hashCode] != null && counter != getCapacity();
                hashCode = (hashCode + 1) % getCapacity(), counter++
        ) {
            if (key.equals(keys[hashCode])) {
                return values[hashCode];
            }
        }
        return null;
    }

    @Override
    public Value remove(Key key) {
        if ((size > 0) && (size == getCapacity() / 4)) {
            resize(getCapacity() / 2);
        }
        int hashCode = getHashCode(key);
        for (
                int counter = 0;
                keys[hashCode] != null && counter != getCapacity();
                hashCode = (hashCode + 1) % getCapacity(), counter++
        ) {
            if (key.equals(keys[hashCode])) {
                Value result = values[hashCode];
                keys[hashCode] = null;
                values[hashCode] = null;
                size--;
                // moving hash codes which are not in correct positions
                for (int i = hashCode; ; i = (i + 1) % getCapacity()) {
                    int nextIndex = (i + 1) % getCapacity();
                    if ((keys[nextIndex] != null) && (keys[nextIndex].hashCode() != nextIndex)) {
                        keys[i] = keys[nextIndex];
                        values[i] = values[nextIndex];
                        // put nulls in next cell to make sure no previous value duplicated
                        keys[nextIndex] = null;
                        values[nextIndex] = null;
                    } else {
                        break;
                    }
                }
                return result;
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < getCapacity(); i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }
    //#endregion

    //#region Private Methods
    private int getHashCode(final Key key) {
        return (key.hashCode() & 0x7fffffff) % getCapacity();
    }

    private boolean add(Key key, Value value, Key[] keys, Value[] values) {
        boolean result = true;
        int hashCode = getHashCode(key);
        for (; keys[hashCode] != null; hashCode = (hashCode + 1) % getCapacity()) {
            if (keys[hashCode].equals(key)) {
                result = false;
                break; // replace value
            }
        }
        keys[hashCode] = key;
        values[hashCode] = value;
        return result;
    }

    private void resize(final int capacity) {
        this.capacity = capacity;
        Key[] keysCopy = (Key[]) new Object[capacity];
        Value[] valuesCopy = (Value[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            Key currentKey = keys[i];
            Value currentValue = values[i];
            add(currentKey, currentValue, keysCopy, valuesCopy);
        }
        keys = keysCopy;
        values = valuesCopy;
    }
    //#endregion
}
