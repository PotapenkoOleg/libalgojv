/*
 * TrieTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.tries.trie;

import com.libalgojv.common.interfaces.SymbolTable;
import com.libalgojv.tries.trie.Trie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TrieTests {
    private SymbolTable<Integer> symbolTable;

    @BeforeEach
    void setUp() {
        symbolTable = new Trie<>();

        symbolTable.put("she", 0);
        symbolTable.put("sells", 1);
        symbolTable.put("sea", 2);
        symbolTable.put("shells", 3);
        symbolTable.put("by", 4);
        symbolTable.put("the", 5);
        symbolTable.put("sea", 6);
        symbolTable.put("shore", 7);
        symbolTable.put("a", 8);
    }

    @AfterEach
    void tearDown() {
        symbolTable = null;
    }

    @Test
    void put() {
        Integer expected, actual;

        expected = 8;
        actual = symbolTable.get("a");
        assertEquals(expected, actual);

        expected = 4;
        actual = symbolTable.get("by");
        assertEquals(expected, actual);

        expected = 6;
        actual = symbolTable.get("sea");
        assertEquals(expected, actual);

        expected = 1;
        actual = symbolTable.get("sells");
        assertEquals(expected, actual);

        expected = 0;
        actual = symbolTable.get("she");
        assertEquals(expected, actual);

        expected = 3;
        actual = symbolTable.get("shells");
        assertEquals(expected, actual);

        expected = 7;
        actual = symbolTable.get("shore");
        assertEquals(expected, actual);

        expected = 5;
        actual = symbolTable.get("the");
        assertEquals(expected, actual);
    }

    @Test
    void get() {
        Integer expected, actual;

        expected = 8;
        actual = symbolTable.get("a");
        assertEquals(expected, actual);

        expected = 4;
        actual = symbolTable.get("by");
        assertEquals(expected, actual);

        expected = 6;
        actual = symbolTable.get("sea");
        assertEquals(expected, actual);

        expected = 1;
        actual = symbolTable.get("sells");
        assertEquals(expected, actual);

        expected = 0;
        actual = symbolTable.get("she");
        assertEquals(expected, actual);

        expected = 3;
        actual = symbolTable.get("shells");
        assertEquals(expected, actual);

        expected = 7;
        actual = symbolTable.get("shore");
        assertEquals(expected, actual);

        expected = 5;
        actual = symbolTable.get("the");
        assertEquals(expected, actual);

        // invalid entry
        actual = symbolTable.get("invalid");
        assertNull(actual);
    }

    @Test
    void delete() {
        Integer expected, actual;

        symbolTable.delete("a");
        actual = symbolTable.get("a");
        assertNull(actual);

        symbolTable.delete("by");
        actual = symbolTable.get("by");
        assertNull(actual);

        symbolTable.delete("shore");
        actual = symbolTable.get("shore");
        assertNull(actual);

        expected = 3;
        actual = symbolTable.get("shells");
        assertEquals(expected, actual);

        expected = 6;
        actual = symbolTable.get("sea");
        assertEquals(expected, actual);

        symbolTable.delete("sea");
        expected = 1;
        actual = symbolTable.get("sells");
        assertEquals(expected, actual);

        // invalid entry
        symbolTable.delete("by");
        actual = symbolTable.get("by");
        assertNull(actual);
    }

    @Test
    void clear() {
        boolean actual = symbolTable.isEmpty();
        assertFalse(actual);

        symbolTable.clear();
        actual = symbolTable.isEmpty();
        assertTrue(actual);
    }

    @Test
    void isEmpty() {
        boolean actual = symbolTable.isEmpty();
        assertFalse(actual);

        symbolTable.clear();
        actual = symbolTable.isEmpty();
        assertTrue(actual);

        symbolTable.put("placeholder", 42);
        actual = symbolTable.isEmpty();
        assertFalse(actual);
    }

    @Test
    void getSize() {
        int actual, expected;
        expected = 8;
        actual = symbolTable.getSize();
        assertEquals(expected, actual);

        symbolTable.delete("by");
        expected = 7;
        actual = symbolTable.getSize();
        assertEquals(expected, actual);

        symbolTable.clear();
        expected = 0;
        actual = symbolTable.getSize();
        assertEquals(expected, actual);
    }

    @Test
    void getAllKeys() {
        Map map = new HashMap();

        map.put("she", 0);
        map.put("sells", 0);
        map.put("shells", 0);
        map.put("by", 0);
        map.put("the", 0);
        map.put("sea", 0);
        map.put("shore", 0);
        map.put("a", 0);

        Iterable<String> allKeys = symbolTable.getAllKeys();

        int expected = 8;
        int actual = checkKeys(allKeys, map);
        assertEquals(expected, actual);
    }

    @Test
    void getKeysWithPrefix() {
        Map map = new HashMap();

        map.put("she", 0);
        map.put("sells", 0);
        map.put("shells", 0);
        map.put("sea", 0);
        map.put("shore", 0);

        String prefix = "s";

        Iterable<String> allKeys = symbolTable.getKeysWithPrefix(prefix);

        int expected = 5;
        int actual = checkKeys(allKeys, map);
        assertEquals(expected, actual);

        map.clear();

        map.put("she", 0);
        map.put("shells", 0);
        map.put("shore", 0);

        prefix = "sh";

        allKeys = symbolTable.getKeysWithPrefix(prefix);

        expected = 3;
        actual = checkKeys(allKeys, map);
        assertEquals(expected, actual);

        prefix = "Invalid";

        allKeys = symbolTable.getKeysWithPrefix(prefix);
        assertNull(allKeys);
    }

//    @Test
//    void wildcardMatch() {
//        fail();
//    }
//
//    @Test
//    void longestPrefixOf() {
//        fail();
//    }

    private int checkKeys(Iterable<String> allKeys, Map map) {
        int numberOfItems = 0;
        for (String key : allKeys) {
            numberOfItems++;
            Integer value = (Integer) map.getOrDefault(key, null);
            if (value == null) {
                fail("Invalid key");
            }
        }
        return numberOfItems;
    }
}