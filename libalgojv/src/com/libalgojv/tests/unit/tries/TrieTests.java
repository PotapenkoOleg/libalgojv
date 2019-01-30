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
        symbolTable = new Trie<Integer>();
    }

    @AfterEach
    void tearDown() {
        symbolTable = null;
    }

    @Test
    void put() {
        symbolTable.put("A", 42);
        //fail("Not Implemented");
    }

    @Test
    void get() {
        fail("Not Implemented");
    }

    @Test
    void delete() {
        fail("Not Implemented");
    }

    @Test
    void clear() {
        fail("Not Implemented");
    }

    @Test
    void isEmpty() {
        fail("Not Implemented");
    }

    @Test
    void getSize() {
        fail("Not Implemented");
    }
}