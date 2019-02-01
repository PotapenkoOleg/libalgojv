package com.libalgojv.tests.unit.tries;

import com.libalgojv.common.interfaces.SymbolTable;
import com.libalgojv.tries.TernaryTrie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TernaryTrieTests {
    private SymbolTable<Integer> symbolTable;

    @BeforeEach
    void setUp() {
        symbolTable = new TernaryTrie<Integer>();
    }

    @AfterEach
    void tearDown() {
        symbolTable = null;
    }

    @Test
    void put() {
        fail("Not Implemented");
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
    void contains() {
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