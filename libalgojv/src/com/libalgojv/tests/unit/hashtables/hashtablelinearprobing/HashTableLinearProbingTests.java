/*
 * HashTableLinearProbingTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.hashtables.hashtablelinearprobing;

import com.libalgojv.common.interfaces.HashTable;
import com.libalgojv.hashtables.hashtablelinearprobing.HashTableLinearProbing;
import com.libalgojv.tests.unit.hashtables.SimpleTransactionKey;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableLinearProbingTests {
    private HashTable<SimpleTransactionKey, Integer> hashTable;

    @BeforeEach
    void setUp() {
        // making extremely small capacity to create collisions
        hashTable = new HashTableLinearProbing<>(2);
    }

    @AfterEach
    void tearDown() {
        hashTable = null;
    }

    @Test
    void add() {
        if (!(hashTable instanceof HashTableLinearProbing)) {
            fail("Invalid test class ");
        }

        HashTableLinearProbing<SimpleTransactionKey, Integer> hashTableLinearProbing = (HashTableLinearProbing) hashTable;

        SimpleTransactionKey key3 = new SimpleTransactionKey(3);
        SimpleTransactionKey key4 = new SimpleTransactionKey(4);
        SimpleTransactionKey key0 = new SimpleTransactionKey(0);

        hashTable.add(key3, 3);
        hashTable.add(key4, 4);
        hashTable.add(key0, 0);
        assertEquals(3, hashTable.getSize());
        assertEquals(4, hashTableLinearProbing.getCapacity());

        int actual = hashTable.get(key3);
        assertEquals(3, actual);

        actual = hashTable.get(key0);
        assertEquals(0, actual);

        actual = hashTable.get(key4);
        assertEquals(4, actual);
    }

    @Test
    void get() {
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

        Integer actual = hashTable.get(key4);
        assertEquals(java.util.Optional.of(4), java.util.Optional.of(actual));

        actual = hashTable.get(key3);
        assertEquals(java.util.Optional.of(3), java.util.Optional.of(actual));

        actual = hashTable.get(key2);
        assertEquals(java.util.Optional.of(2), java.util.Optional.of(actual));

        actual = hashTable.get(key1);
        assertEquals(java.util.Optional.of(1), java.util.Optional.of(actual));

        actual = hashTable.get(key0);
        assertEquals(java.util.Optional.of(0), java.util.Optional.of(actual));

        // replace value
        hashTable.add(key0, 42);

        actual = hashTable.get(key0);
        assertEquals(java.util.Optional.of(42), java.util.Optional.of(actual));

        // collision case
        SimpleTransactionKey key8 = new SimpleTransactionKey(8);
        hashTable.add(key8, 8);

        actual = hashTable.get(key8);
        assertEquals(java.util.Optional.of(8), java.util.Optional.of(actual));

        // not existing item
        SimpleTransactionKey key100 = new SimpleTransactionKey(100);
        actual = hashTable.get(key100);
        assertNull(actual);

        SimpleTransactionKey key6 = new SimpleTransactionKey(6);
        SimpleTransactionKey key7 = new SimpleTransactionKey(7);

        hashTable.add(key6, 6);
        hashTable.add(key7, 7);

        // full hash table case - no nulls
        actual = hashTable.get(key100);
        assertNull(actual);
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

        // collision case
        SimpleTransactionKey key8 = new SimpleTransactionKey(8);
        SimpleTransactionKey key9 = new SimpleTransactionKey(9);
        SimpleTransactionKey key7 = new SimpleTransactionKey(7);
        hashTable.add(key8, 8);
        hashTable.add(key9, 9);
        hashTable.add(key7, 7);

        Integer actual = hashTable.remove(key4);
        assertEquals(java.util.Optional.of(4), java.util.Optional.of(actual));
        actual = hashTable.get(key4);
        assertNull(actual);

        actual = hashTable.get(key8);
        assertEquals(Integer.valueOf(8), actual);

        // not existing item
        SimpleTransactionKey key100 = new SimpleTransactionKey(100);
        actual = hashTable.remove(key100);
        assertNull(actual);

        // full hash table case - no nulls
        SimpleTransactionKey key6 = new SimpleTransactionKey(6);
        hashTable.add(key6, 6);

        actual = hashTable.remove(key100);
        assertNull(actual);
    }

    @Test
    void clear() {
        if (!(hashTable instanceof HashTableLinearProbing)) {
            fail("Invalid test class ");
        }

        HashTableLinearProbing<SimpleTransactionKey, Integer> hashTableLinearProbing = (HashTableLinearProbing) hashTable;

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

        // collision case
        SimpleTransactionKey key8 = new SimpleTransactionKey(8);
        hashTable.add(key8, 8);

        hashTable.clear();
        assertEquals(0, hashTable.getSize());
        assertEquals(8, hashTableLinearProbing.getCapacity());
    }
}