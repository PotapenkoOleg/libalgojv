/*
 * HashTableSeparateChainingTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.hashtables.hashtableseparatechaining;

import com.libalgojv.common.interfaces.HashTable;
import com.libalgojv.hashtables.hashtableseparatechaining.HashTableSeparateChaining;
import com.libalgojv.tests.unit.hashtables.SimpleTransactionKey;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableSeparateChainingTests {
    private HashTable<SimpleTransactionKey, Integer> hashTable;

    @BeforeEach
    void setUp() {
        // making extremely small capacity to create collisions
        hashTable = new HashTableSeparateChaining<>(2);
    }

    @AfterEach
    void tearDown() {
        hashTable = null;
    }

    @Test
    void add() {
        if (!(hashTable instanceof HashTableSeparateChaining)) {
            fail("Invalid test class ");
        }

        HashTableSeparateChaining<SimpleTransactionKey, Integer> hashTableLinearProbing = (HashTableSeparateChaining) hashTable;

        SimpleTransactionKey key3 = new SimpleTransactionKey(3);
        SimpleTransactionKey key4 = new SimpleTransactionKey(4);
        SimpleTransactionKey key0 = new SimpleTransactionKey(0);

        hashTable.add(key3, 3);
        hashTable.add(key4, 4);
        hashTable.add(key0, 0);
        assertEquals(3, hashTable.getSize());
        assertEquals(2, hashTableLinearProbing.getCapacity());

        int actual = hashTable.get(key3);
        assertEquals(3, actual);

        actual = hashTable.get(key0);
        assertEquals(0, actual);

        actual = hashTable.get(key4);
        assertEquals(4, actual);
    }

    @Test
    void get() {
        // non existing value in empty table
        SimpleTransactionKey key100 = new SimpleTransactionKey(100);
        Integer actual = hashTable.get(key100);
        assertNull(actual);

        SimpleTransactionKey key4 = new SimpleTransactionKey(4);
        SimpleTransactionKey key3 = new SimpleTransactionKey(3);
        SimpleTransactionKey key2 = new SimpleTransactionKey(2);
        SimpleTransactionKey key1 = new SimpleTransactionKey(1);
        SimpleTransactionKey key0 = new SimpleTransactionKey(0);

        hashTable.add(key4, 4);
        hashTable.add(key3, 3);
        hashTable.add(key2, 2);
        hashTable.add(key1, 1);
        hashTable.add(key0, 0);

        actual = hashTable.get(key4);
        assertEquals(java.util.Optional.of(4), java.util.Optional.of(actual));

        actual = hashTable.get(key3);
        assertEquals(java.util.Optional.of(3), java.util.Optional.of(actual));

        actual = hashTable.get(key2);
        assertEquals(java.util.Optional.of(2), java.util.Optional.of(actual));

        actual = hashTable.get(key1);
        assertEquals(java.util.Optional.of(1), java.util.Optional.of(actual));

        actual = hashTable.get(key0);
        assertEquals(java.util.Optional.of(0), java.util.Optional.of(actual));

        // non existing value
        actual = hashTable.get(key100);
        assertNull(actual);

        // replace value
        hashTable.add(key0, 42);
        actual = hashTable.get(key0);
        assertEquals(java.util.Optional.of(42), java.util.Optional.of(actual));
    }

    @Test
    void remove() {
        SimpleTransactionKey key4 = new SimpleTransactionKey(4);
        SimpleTransactionKey key3 = new SimpleTransactionKey(3);
        SimpleTransactionKey key2 = new SimpleTransactionKey(2);
        SimpleTransactionKey key1 = new SimpleTransactionKey(1);
        SimpleTransactionKey key0 = new SimpleTransactionKey(0);

        hashTable.add(key4, 4);
        hashTable.add(key3, 3);
        hashTable.add(key2, 2);
        hashTable.add(key1, 1);
        hashTable.add(key0, 0);

        Integer actual = hashTable.remove(key1);
        assertEquals(java.util.Optional.of(1), java.util.Optional.of(actual));
        actual = hashTable.get(key1);
        assertNull(actual);

        actual = hashTable.remove(key3);
        assertEquals(java.util.Optional.of(3), java.util.Optional.of(actual));
        actual = hashTable.get(key3);
        assertNull(actual);

        actual = hashTable.remove(key2);
        assertEquals(java.util.Optional.of(2), java.util.Optional.of(actual));
        actual = hashTable.get(key2);
        assertNull(actual);

        actual = hashTable.remove(key4);
        assertEquals(java.util.Optional.of(4), java.util.Optional.of(actual));
        actual = hashTable.get(key4);
        assertNull(actual);

        actual = hashTable.getSize();
        assertEquals(java.util.Optional.of(1), java.util.Optional.of(actual));

        // removing not exising value in empty cell
        actual = hashTable.remove(key1);
        assertNull(actual);

        // non existing value
        SimpleTransactionKey key100 = new SimpleTransactionKey(100);
        actual = hashTable.remove(key100);
        assertNull(actual);
    }

    @Test
    void clear() {
        if (!(hashTable instanceof HashTableSeparateChaining)) {
            fail("Invalid test class ");
        }

        HashTableSeparateChaining<SimpleTransactionKey, Integer> hashTableLinearProbing = (HashTableSeparateChaining) hashTable;

        SimpleTransactionKey key4 = new SimpleTransactionKey(4);
        SimpleTransactionKey key3 = new SimpleTransactionKey(3);
        SimpleTransactionKey key2 = new SimpleTransactionKey(2);
        SimpleTransactionKey key1 = new SimpleTransactionKey(1);
        SimpleTransactionKey key0 = new SimpleTransactionKey(0);

        hashTable.add(key4, 4);
        hashTable.add(key3, 3);
        hashTable.add(key2, 2);
        hashTable.add(key1, 1);
        hashTable.add(key0, 0);

        hashTable.clear();

        assertEquals(0, hashTable.getSize());
        assertEquals(2, hashTableLinearProbing.getCapacity());
    }
}