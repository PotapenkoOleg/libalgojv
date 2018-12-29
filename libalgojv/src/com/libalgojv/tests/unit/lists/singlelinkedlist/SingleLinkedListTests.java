/*
 * SingleLinkedListTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.lists.singlelinkedlist;

import com.libalgojv.common.interfaces.List;
import com.libalgojv.common.enums.AlgorithmType;
import com.libalgojv.common.enums.InsertPosition;
import com.libalgojv.common.enums.RemovePosition;
import com.libalgojv.lists.singlelinkedlist.SingleLinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListTests {

    private List<Integer, Integer> list;

    @BeforeEach
    void setUp() {
        list = new SingleLinkedList<>(AlgorithmType.ITERATIVE);
    }

    @AfterEach
    void tearDown() {
        list = null;
    }

    @Test
    void addAtBeginning() {
        int expected = 42;
        list.add(expected, -expected, InsertPosition.BEGINNING);
        int actual = list.get(expected);

        assertEquals(-expected, actual);

        int expected2 = expected + 1;
        list.add(expected2, -expected2, InsertPosition.BEGINNING);
        actual = list.get(expected2);

        assertEquals(-expected2, actual);

        int expected3 = expected2 + 1;
        list.add(expected3, -expected3, InsertPosition.BEGINNING);
        actual = list.get(expected3);

        assertEquals(-expected3, actual);
    }

    @Test
    void addAtEnd() {
        int expected = 42;
        list.add(expected, -expected, InsertPosition.END);
        int actual = list.get(expected);

        assertEquals(-expected, actual);

        int expected2 = expected + 1;
        list.add(expected2, -expected2, InsertPosition.END);
        actual = list.get(expected2);

        assertEquals(-expected2, actual);

        int expected3 = expected2 + 1;
        list.add(expected3, -expected3, InsertPosition.END);
        actual = list.get(expected3);

        assertEquals(-expected3, actual);
    }

    @Test
    void addByKey() {
        if (!(list instanceof SingleLinkedList)) {
            fail("Invalid test class ");
        }
        Integer expected = 42;
        SingleLinkedList singleLinkedList = (SingleLinkedList) list;

        try {
            // invalid position - should be exception here
            // can't add by value in empty list
            list.add(expected, -expected, expected);
        } catch (NoSuchElementException ex) {
        }

        list.clear();
        for (Integer i = expected; i <= expected + 3; i++) {
            list.add(i, -i, InsertPosition.END);
        }

        list.add(0, 0, (expected + 2));
        Iterator<Integer> iterator = singleLinkedList.iterator();
        iterator.next();
        iterator.next();
        Integer actual = iterator.next();
        assertEquals(Optional.of(0), Optional.of(actual));

        try {
            // invalid key - should be exception here
            list.add(100, -100, 100);
        } catch (NoSuchElementException ex) {
        }

        // last element
        list.add(-100, 100, (expected + 3));
        iterator = singleLinkedList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        actual = iterator.next();

        assertEquals(Optional.of(100), Optional.of(actual));
    }

    @Test
    void removeFromBeginning() {
        Integer expected = 42;
        Integer actual = list.remove(RemovePosition.BEGINNING);

        assertNull(actual);

        list.add(expected, -expected, InsertPosition.BEGINNING);
        actual = list.remove(RemovePosition.BEGINNING);

        assertEquals(Optional.of(-expected), Optional.of(actual));
        assertTrue(list.isEmpty());

        list.add(expected + 1, -(expected + 1), InsertPosition.BEGINNING);
        list.add(expected, -expected, InsertPosition.BEGINNING);

        actual = list.remove(RemovePosition.BEGINNING);

        assertEquals(Optional.of(-expected), Optional.of(actual));
        assertFalse(list.isEmpty());

        list.clear();

        list.add(expected + 2, -(expected + 2), InsertPosition.BEGINNING);
        list.add(expected + 1, -(expected + 1), InsertPosition.BEGINNING);
        list.add(expected, -expected, InsertPosition.BEGINNING);

        actual = list.remove(RemovePosition.BEGINNING);
        assertEquals(Optional.of(-expected), Optional.of(actual));

        int actualSize = list.getSize();
        int expectedSize = 2;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void removeFromEnd() {
        Integer expected = 42;
        Integer actual = list.remove(RemovePosition.END);

        assertNull(actual);

        list.add(expected, -expected, InsertPosition.BEGINNING);
        actual = list.remove(RemovePosition.END);

        assertEquals(Optional.of(-expected), Optional.of(actual));
        assertTrue(list.isEmpty());

        list.add(expected, -expected, InsertPosition.BEGINNING);
        list.add(expected + 1, -(expected + 1), InsertPosition.BEGINNING);

        actual = list.remove(RemovePosition.END);

        assertEquals(Optional.of(-expected), Optional.of(actual));
        assertFalse(list.isEmpty());

        list.clear();

        list.add(expected, -expected, InsertPosition.BEGINNING);
        list.add(expected + 1, -(expected + 1), InsertPosition.BEGINNING);
        list.add(expected + 2, -(expected + 2), InsertPosition.BEGINNING);

        actual = list.remove(RemovePosition.END);
        assertEquals(Optional.of(-expected), Optional.of(actual));

        int actualSize = list.getSize();
        int expectedSize = 2;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void removeByKey() {
        Integer expected = 42;

        // empty list
        Integer actual = list.remove(expected);

        assertNull(actual);

        list.add(expected, -expected, InsertPosition.BEGINNING);
        actual = list.remove(expected);

        assertEquals(Optional.of(-expected), Optional.of(actual));
        assertTrue(list.isEmpty());

        list.add(expected + 1, -(expected + 1), InsertPosition.BEGINNING);
        list.add(expected, -expected, InsertPosition.BEGINNING);

        actual = list.remove(expected);

        assertEquals(Optional.of(-expected), Optional.of(actual));

        list.clear();

        list.add(expected + 2, -(expected + 2), InsertPosition.BEGINNING);
        list.add(expected, -expected, InsertPosition.BEGINNING);
        list.add(expected + 1, -(expected + 1), InsertPosition.BEGINNING);

        actual = list.remove(expected);

        assertEquals(Optional.of(-expected), Optional.of(actual));
        assertFalse(list.isEmpty());

        // key not found
        actual = list.remove(expected - 1);
        assertNull(actual);
    }

    @Test
    void get() {
        Integer expected = 42;
        Integer actual = list.get(expected);

        // empty list
        assertNull(actual);

        list.add(expected, -expected, InsertPosition.BEGINNING);
        actual = list.get(expected);

        assertEquals(Optional.of(-expected), Optional.of(actual));

        list.add(expected + 1, -(expected + 1), InsertPosition.BEGINNING);
        actual = list.get(expected);

        assertEquals(Optional.of(-expected), Optional.of(actual));

        actual = list.get(expected - 1);

        // key not found
        assertNull(actual);
    }

    @Test
    void getNthElementFromEnd() {
        if (!(list instanceof SingleLinkedList)) {
            fail("Invalid test class ");
        }

        SingleLinkedList<Integer, Integer> singleLinkedList = (SingleLinkedList<Integer, Integer>) list;

        Integer actual = singleLinkedList.getNthElementFromEnd(0);
        assertNull(actual);

        Integer expected = 42;
        singleLinkedList.add(expected, -expected, InsertPosition.BEGINNING);
        actual = singleLinkedList.getNthElementFromEnd(0);
        assertEquals(Optional.of(-expected), Optional.of(actual));

        singleLinkedList.clear();

        for (Integer i = expected; i <= expected + 1; i++) {
            singleLinkedList.add(i, -i, InsertPosition.END);
        }

        // Existing element
        actual = singleLinkedList.getNthElementFromEnd(1);
        assertEquals(Optional.of(-expected), Optional.of(actual));

        // Not existing element
        actual = singleLinkedList.getNthElementFromEnd(100);
        assertNull(actual);

        singleLinkedList.clear();

        for (Integer i = expected; i <= expected + 5; i++) {
            singleLinkedList.add(i, -i, InsertPosition.END);
        }

        // Existing element - general case
        expected = 45;
        actual = singleLinkedList.getNthElementFromEnd(2);
        assertEquals(Optional.of(-expected), Optional.of(actual));

        // Not existing element - general case
        actual = singleLinkedList.getNthElementFromEnd(100);
        assertNull(actual);
    }

    @Test
    void isCyclic() {
        if (!(list instanceof SingleLinkedList)) {
            fail("Invalid test class ");
        }
        Integer expected = 42;
        SingleLinkedList singleLinkedList = (SingleLinkedList) list;
        singleLinkedList.add(expected, -expected, InsertPosition.END);
        singleLinkedList.add(expected + 1, -(expected + 1), InsertPosition.END);
        singleLinkedList.makeCyclic();
        Iterator<Integer> iterator = singleLinkedList.iterator();
        iterator.next();
        iterator.next();
        Integer actual = iterator.next();
        assertEquals(Optional.of(-expected), Optional.of(actual));
        singleLinkedList.clear();
        singleLinkedList.add(expected, -expected, InsertPosition.END);
        singleLinkedList.add(expected + 1, -(expected + 1), InsertPosition.END);
        singleLinkedList.add(expected + 2, -(expected + 2), InsertPosition.END);
        singleLinkedList.makeCyclic(expected + 1);
        iterator = singleLinkedList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        actual = iterator.next();
        assertEquals(Optional.of(-(expected + 1)), Optional.of(actual));
    }

    @Test
    void checkCyclic() {
        if (!(list instanceof SingleLinkedList)) {
            fail("Invalid test class ");
        }

        SingleLinkedList<Integer, Integer> singleLinkedList = (SingleLinkedList<Integer, Integer>) list;

        Integer elementKeyAndValue = 42;
        // empty list
        boolean actual = singleLinkedList.isCyclic();
        assertFalse(actual);

        singleLinkedList.add(elementKeyAndValue, -elementKeyAndValue, InsertPosition.BEGINNING);
        actual = singleLinkedList.isCyclic();
        assertFalse(actual);

        singleLinkedList.makeCyclic();
        actual = singleLinkedList.isCyclic();
        assertTrue(actual);

        singleLinkedList.clear();

        for (Integer i = elementKeyAndValue; i <= elementKeyAndValue + 5; i++) {
            singleLinkedList.add(i, -i, InsertPosition.END);
        }
        actual = singleLinkedList.isCyclic();
        assertFalse(actual);

        singleLinkedList.makeCyclic();
        actual = singleLinkedList.isCyclic();
        assertTrue(actual);

        singleLinkedList.clear();

        for (Integer i = elementKeyAndValue; i <= elementKeyAndValue + 5; i++) {
            singleLinkedList.add(i, -i, InsertPosition.END);
        }
        singleLinkedList.makeCyclic(45);
        actual = singleLinkedList.isCyclic();
        assertTrue(actual);
    }

    @Test
    void isEmpty() {
        boolean actual = list.isEmpty();

        assertTrue(actual);

        list.add(0, 0, InsertPosition.BEGINNING);
        actual = list.isEmpty();

        assertFalse(actual);
    }

    @Test
    void clear() {
        boolean actual = list.isEmpty();

        assertTrue(actual);

        list.add(0, 0, InsertPosition.BEGINNING);
        actual = list.isEmpty();

        assertFalse(actual);

        list.clear();
        actual = list.isEmpty();

        assertTrue(actual);
    }

    @Test
    void size() {
        int expected = 0;
        int actual = list.getSize();

        assertEquals(expected, actual);

        list.add(expected, -expected, InsertPosition.BEGINNING);

        expected = 1;
        actual = list.getSize();

        assertEquals(expected, actual);

        list.add(expected, -expected, InsertPosition.BEGINNING);

        expected = 2;
        actual = list.getSize();

        assertEquals(expected, actual);

        list.clear();

        expected = 0;
        actual = list.getSize();

        assertEquals(expected, actual);
    }

    @Test
    void toStringTest() {
        Integer item = 42;
        list.add(item, -item, InsertPosition.END);
        list.add(item + 1, -(item + 1), InsertPosition.END);
        list.add(item + 2, -(item + 2), InsertPosition.END);
        String actual = list.toString();
        String expected = "-42\n-43\n-44\n";
        assertEquals(expected, actual);
    }

    @Test
    void iterator() {
        Integer expected = 42;
        list.add(expected, -expected, InsertPosition.BEGINNING);

        for (Integer actual : list) {
            assertEquals(Optional.of(-expected), Optional.of(actual));
        }
    }

    @Test
    void forEach() {
        Integer expected = 42;
        list.add(expected, -expected, InsertPosition.BEGINNING);
        list.forEach(actual -> assertEquals(Optional.of(-expected), Optional.of(actual)));
    }
}