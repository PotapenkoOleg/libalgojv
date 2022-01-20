/*
 * TernaryTrieTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.tries.ternarytrie;

import com.libalgojv.common.interfaces.SymbolTable;
import com.libalgojv.tries.ternarytrie.TernaryTrie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.libalgojv.tests.unit.utils.OSValidator.*;
import static org.junit.jupiter.api.Assertions.*;

class TernaryTrieTests {
    private SymbolTable<Integer> symbolTable;

    private void initSymbolTable() {
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

    @BeforeEach
    void setUp() {
        symbolTable = new TernaryTrie<>();
        initSymbolTable();
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

        // clear all entries
        symbolTable.delete("she");
        symbolTable.delete("sells");
        symbolTable.delete("sea");
        symbolTable.delete("shells");
        symbolTable.delete("by");
        symbolTable.delete("the");
        symbolTable.delete("sea");
        symbolTable.delete("shore");
        symbolTable.delete("a");

        expected = 0;
        actual = symbolTable.getSize();
        assertEquals(expected, actual);

        initSymbolTable();

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

        // delete root
        symbolTable.clear();
        symbolTable.put("a", 0);
        symbolTable.delete("a");
        actual = symbolTable.get("a");
        assertNull(actual);
        expected = 0;
        actual = symbolTable.getSize();
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

    @Test
    void longestPrefixOf() {
        String expected = "shells";
        String actual = symbolTable.longestPrefixOf("shellsort");
        assertEquals(expected, actual);

        expected = "a";
        actual = symbolTable.longestPrefixOf("a");
        assertEquals(expected, actual);

        actual = symbolTable.longestPrefixOf("Invalid");
        assertNull(actual);

        symbolTable.clear();

        symbolTable.put("128", 0);
        symbolTable.put("128.112.055", 0);
        symbolTable.put("128.112.055.015", 0);
        symbolTable.put("128.112.136", 0);
        symbolTable.put("128.112.155.011", 0);
        symbolTable.put("128.112.155.013", 0);
        symbolTable.put("128.112", 0);
        symbolTable.put("128.222", 0);
        symbolTable.put("128.222.136", 0);

        expected = "128.112.136";
        actual = symbolTable.longestPrefixOf("128.112.136.011");
        assertEquals(expected, actual);

        expected = "128.112";
        actual = symbolTable.longestPrefixOf("128.112.100.016");
        assertEquals(expected, actual);

        expected = "128";
        actual = symbolTable.longestPrefixOf("128.166.123.045");
        assertEquals(expected, actual);

        symbolTable.clear();
        symbolTable.put("a", 0);
        expected = "a";
        actual = symbolTable.longestPrefixOf("a");
        assertEquals(expected, actual);
    }

    @Test
    void loadTest() {
        List<AbstractMap.SimpleEntry<String, String>> input = new ArrayList<>();
        SymbolTable<String> symbolTable = new TernaryTrie<>();
        try {
            // Loading 29872 entries
            Path path = null;
            if (isWindows()) {
                path = Paths.get("src\\com\\libalgojv\\tests\\unit\\tries\\ternarytrie\\USZipCodes.csv").toAbsolutePath();
            }
            if (isMac() || isUnix()) {
                path = Paths.get("src/com/libalgojv/tests/unit/tries/ternarytrie/USZipCodes.csv").toAbsolutePath();
            }
            if (path == null) {
                fail("Unsupported OS or path Format");
            }
            List<String> allLines = Files.readAllLines(path);
            for (String line : allLines) {
                String[] parts = line.split(",");
                AbstractMap.SimpleEntry<String, String> keyValuePair =
                        new AbstractMap.SimpleEntry<>(parts[0], parts[1]);
                input.add(keyValuePair);
                symbolTable.put(keyValuePair.getKey(), keyValuePair.getValue());
            }
            for (AbstractMap.SimpleEntry<String, String> entry : input) {
                String key = entry.getKey();
                String expected = entry.getValue();
                String actual = symbolTable.get(key);
                if (!expected.equals(actual)) {
                    fail();
                }
                assertEquals(expected, actual);
            }
            int expected = 29872;
            int actual = symbolTable.getSize();
            assertEquals(expected, actual);
        } catch (IOException e) {
            fail("File not found.");
        }
    }

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