package com.libalgojv.tests.unit.tries;

import com.libalgojv.common.interfaces.SymbolTable;
import com.libalgojv.tries.Trie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTests {
    private SymbolTable<Integer> symbolTable;

    @BeforeEach
    void setUp() {
        symbolTable = new Trie<>();

        symbolTable.put("a", 8);
        symbolTable.put("sea", 2);
        symbolTable.put("by", 4);
        symbolTable.put("sea", 6);
        symbolTable.put("sells", 1);
        symbolTable.put("she", 0);
        symbolTable.put("shells", 3);
        symbolTable.put("shore", 7);
        symbolTable.put("the", 5);
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
        fail();
    }

    @Test
    void getKeysWithPrefix() {
        fail();
    }

    @Test
    void wildcardMatch() {
        fail();
    }

    @Test
    void longestPrefixOf() {
        fail();
    }
}