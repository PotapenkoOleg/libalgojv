/*
 * HashTableSeparateChaining.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.hashtables.hashtableseparatechaining;

import com.libalgojv.common.interfaces.HashTable;

public class HashTableSeparateChaining<Key, Value> implements HashTable<Key, Value> {
    //#region Private Fields
    private final static int DEFAULT_CAPACITY = 16; // should be power of two
    private int size = 0;
    private final int capacity;
    private final HashTableSeparateChainingNode[] hashTable;
    //#endregion

    //#region Node Class
    private static class HashTableSeparateChainingNode<EKey, EValue> {
        private final EKey key;
        private EValue value;
        private HashTableSeparateChainingNode next;

        HashTableSeparateChainingNode(final EKey key, final EValue value, HashTableSeparateChainingNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        //region Getters and Setters
        EKey getKey() {
            return key;
        }

        EValue getValue() {
            return value;
        }

        HashTableSeparateChainingNode getNext() {
            return next;
        }

        void setNext(HashTableSeparateChainingNode next) {
            this.next = next;
        }

        void setValue(EValue value) {
            this.value = value;
        }
        //endregion
    }
    //#endregion

    //#region Constructors
    public HashTableSeparateChaining() {
        this(DEFAULT_CAPACITY);
    }

    public HashTableSeparateChaining(final int capacity) {
        this.capacity = capacity;
        hashTable = new HashTableSeparateChainingNode[capacity];
    }
    //#endregion

    //#region Public Methods
    @Override
    public void add(final Key key, final Value value) {
        int hashCode = getHashCode(key);
        HashTableSeparateChainingNode current = hashTable[hashCode];
        for (; current != null; current = current.getNext()) {
            if (key.equals(current.getKey())) {
                current.setValue(value);
                return;
            }
        }
        hashTable[hashCode] = new HashTableSeparateChainingNode(key, value, hashTable[hashCode]);
        size++;
    }

    @Override
    public Value get(final Key key) {
        int hashCode = getHashCode(key);
        for (HashTableSeparateChainingNode current = hashTable[hashCode]; current != null; current = current.getNext()) {
            if (key.equals(current.getKey())) {
                return (Value) current.getValue();
            }
        }
        return null;
    }

    @Override
    public Value remove(final Key key) {
        int hashCode = getHashCode(key);
        HashTableSeparateChainingNode current = hashTable[hashCode];
        if (current == null) {
            return null;
        }
        if (key.equals(current.getKey())) {
            Value result = (Value) current.getValue();
            hashTable[hashCode] = current.getNext();
            size--;
            return result;
        }
        while ((current.getNext() != null) && (!key.equals(current.getNext().getKey()))) {
            current = current.getNext();
        }
        if ((current.getNext() != null) && (key.equals(current.getNext().getKey()))) {
            Value result = (Value) current.getNext().getValue();
            current.setNext(current.getNext().getNext());
            size--;
            return result;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            hashTable[i] = null;
        }
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    //#endregion

    //#region Private Methods
    private int getHashCode(final Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }
    //#endregion
}
