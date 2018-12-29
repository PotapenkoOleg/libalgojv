/*
 * DoubleLinkedListTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.lists.doublelinkedlist;

import com.libalgojv.common.enums.AlgorithmType;
import com.libalgojv.common.interfaces.List;
import com.libalgojv.lists.doublelinkedlist.DoubleLinkedList;
import com.libalgojv.common.enums.InsertPosition;
import com.libalgojv.common.enums.RemovePosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// TODO: check back links

class DoubleLinkedListTests {

    private List<Integer, Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList<>(AlgorithmType.RECURSIVE);
    }

    @AfterEach
    void tearDown() {
        list = null;
    }

    @Test
    void addAtBeginning() {
        if (!(list instanceof DoubleLinkedList)) {
            fail("Invalid test class ");
        }

        DoubleLinkedList<Integer, Integer> doubleLinkedList = (DoubleLinkedList<Integer, Integer>) list;

        int expected = 42;
        list.add(expected, expected, InsertPosition.BEGINNING);
        if (doubleLinkedList.isCyclic()) {
            fail("List is cyclic");
        }
        int actual = list.get(expected);

        assertEquals(expected, actual);

        int expected2 = expected + 1;
        list.add(expected2, expected2, InsertPosition.BEGINNING);
        if (doubleLinkedList.isCyclic()) {
            fail("List is cyclic");
        }
        actual = list.get(expected2);

        assertEquals(expected2, actual);

        int expected3 = expected2 + 1;
        list.add(expected3, expected3, InsertPosition.BEGINNING);
        if (doubleLinkedList.isCyclic()) {
            fail("List is cyclic");
        }
        actual = list.get(expected3);

        assertEquals(expected3, actual);
    }

    @Test
    void addAtEnd() {
        if (!(list instanceof DoubleLinkedList)) {
            fail("Invalid test class ");
        }

        DoubleLinkedList<Integer, Integer> doubleLinkedList = (DoubleLinkedList<Integer, Integer>) list;

        int expected = 42;
        list.add(expected, expected, InsertPosition.END);
        if (doubleLinkedList.isCyclic()) {
            fail("List is cyclic");
        }
        int actual = list.get(expected);

        assertEquals(expected, actual);

        int expected2 = expected + 1;
        list.add(expected2, expected2, InsertPosition.END);
        list.add(expected, expected, InsertPosition.END);
        if (doubleLinkedList.isCyclic()) {
            fail("List is cyclic");
        }
        actual = list.get(expected2);

        assertEquals(expected2, actual);

        int expected3 = expected2 + 1;
        list.add(expected3, expected3, InsertPosition.END);
        list.add(expected, expected, InsertPosition.END);
        if (doubleLinkedList.isCyclic()) {
            fail("List is cyclic");
        }
        actual = list.get(expected3);

        assertEquals(expected3, actual);
    }

    @Test
    void addByKey() {
        if (!(list instanceof DoubleLinkedList)) {
            fail("Invalid test class ");
        }
        Integer expected = 42;
        DoubleLinkedList doubleLinkedList = (DoubleLinkedList) list;

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
        Iterator<Integer> iterator = doubleLinkedList.iterator();
        iterator.next();
        iterator.next();
        Integer actual = iterator.next();

        assertEquals(Optional.of(0), Optional.of(actual));

        try {
            // invalid position - should be exception here
            list.add(100, -100, 100);
        } catch (NoSuchElementException ex) {
        }

        // last element
        list.add(-100, 100, (expected + 3));
        iterator = doubleLinkedList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        actual = iterator.next();

        assertEquals(Optional.of(100), Optional.of(actual));

        // first element
        list.add(-200, 200, expected);
        iterator = doubleLinkedList.iterator();
        actual = iterator.next();

        assertEquals(Optional.of(200), Optional.of(actual));
    }

    @Test
    void removeFromBeginning() {
        Integer expected = 42;
        Integer actual = list.remove(RemovePosition.BEGINNING);

        assertNull(actual);

        list.add(expected, expected, InsertPosition.BEGINNING);
        actual = list.remove(RemovePosition.BEGINNING);

        assertEquals(expected, actual);
        assertTrue(list.isEmpty());

        list.add(expected + 1, expected + 1, InsertPosition.BEGINNING);
        list.add(expected, expected, InsertPosition.BEGINNING);

        actual = list.remove(RemovePosition.BEGINNING);

        assertEquals(expected, actual);
        assertFalse(list.isEmpty());

        list.clear();

        list.add(expected + 2, expected + 2, InsertPosition.BEGINNING);
        list.add(expected + 1, expected + 1, InsertPosition.BEGINNING);
        list.add(expected, expected, InsertPosition.BEGINNING);

        actual = list.remove(RemovePosition.BEGINNING);

        int actualSize = list.getSize();
        int expectedSize = 2;

        assertEquals(expected, actual);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void removeFromEnd() {
        Integer expected = 42;
        Integer actual = list.remove(RemovePosition.END);

        assertNull(actual);

        list.add(expected, expected, InsertPosition.BEGINNING);
        actual = list.remove(RemovePosition.END);

        assertEquals(expected, actual);
        assertTrue(list.isEmpty());

        list.add(expected, expected, InsertPosition.BEGINNING);
        list.add(expected + 1, expected + 1, InsertPosition.BEGINNING);

        actual = list.remove(RemovePosition.END);

        assertEquals(expected, actual);
        assertFalse(list.isEmpty());

        list.clear();

        list.add(expected, expected, InsertPosition.BEGINNING);
        list.add(expected + 1, expected + 1, InsertPosition.BEGINNING);
        list.add(expected + 2, expected + 2, InsertPosition.BEGINNING);

        actual = list.remove(RemovePosition.END);

        int actualSize = list.getSize();
        int expectedSize = 2;

        assertEquals(expected, actual);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void removeByKey() {
        Integer expected = 42;

        // empty list
        Integer actual = list.remove(expected);

        assertNull(actual);

        list.add(expected, expected, InsertPosition.BEGINNING);
        actual = list.remove(expected);

        assertEquals(expected, actual);
        assertTrue(list.isEmpty());

        // last element
        list.add(expected + 1, -expected + 1, InsertPosition.BEGINNING);
        list.add(expected, -expected, InsertPosition.BEGINNING);
        actual = list.remove(expected);

        assertEquals(-expected, actual);

        list.clear();

        list.add(expected + 2, -expected + 2, InsertPosition.BEGINNING);
        list.add(expected, -expected, InsertPosition.BEGINNING);
        list.add(expected + 1, -expected + 1, InsertPosition.BEGINNING);

        actual = list.remove(expected);

        assertEquals(-expected, actual);
        assertFalse(list.isEmpty());

        // check last pointer is updated
        actual = list.remove(RemovePosition.END);
        assertEquals(-expected + 2, actual);

        // key not found
        actual = list.remove(100);
        assertNull(actual);

        // first element
        list.add(44, -44, InsertPosition.END);
        actual = list.remove(expected + 1);
        assertEquals(-expected + 1, actual);
        assertFalse(list.isEmpty());
    }

    @Test
    void get() {
        Integer expected = 42;
        Integer actual = list.get(expected);

        // empty list
        assertNull(actual);

        list.add(expected, expected, InsertPosition.BEGINNING);
        actual = list.get(expected);

        assertEquals(expected, actual);

        list.add(expected + 1, expected + 1, InsertPosition.BEGINNING);
        actual = list.get(expected);

        assertEquals(expected, actual);

        actual = list.get(expected - 1);

        // key not found
        assertNull(actual);
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
    void isEmpty() {
        boolean actual = list.isEmpty();

        assertTrue(actual);

        list.add(0, 0, InsertPosition.BEGINNING);
        actual = list.isEmpty();

        assertFalse(actual);
    }

    @Test
    void size() {
        int expected = 0;
        int actual = list.getSize();

        assertEquals(expected, actual);

        list.add(expected, expected, InsertPosition.BEGINNING);

        expected = 1;
        actual = list.getSize();

        assertEquals(expected, actual);

        list.add(expected, expected, InsertPosition.BEGINNING);

        expected = 2;
        actual = list.getSize();

        assertEquals(expected, actual);

        list.clear();

        expected = 0;
        actual = list.getSize();

        assertEquals(expected, actual);
    }

    @Test
    void makeCyclic() {
        if (!(list instanceof DoubleLinkedList)) {
            fail("Invalid test class ");
        }
        Integer expected = 42;
        DoubleLinkedList doubleLinkedList = (DoubleLinkedList) list;
        doubleLinkedList.add(expected, expected, InsertPosition.END);
        doubleLinkedList.add(expected + 1, expected + 1, InsertPosition.END);
        doubleLinkedList.makeCyclic();
        Iterator<Integer> iterator = doubleLinkedList.iterator();
        iterator.next();
        iterator.next();
        Integer actual = iterator.next();
        assertEquals(expected, actual);
        doubleLinkedList.clear();
        doubleLinkedList.add(expected, expected, InsertPosition.END);
        doubleLinkedList.add(expected + 1, expected + 1, InsertPosition.END);
        doubleLinkedList.add(expected + 2, expected + 2, InsertPosition.END);
        doubleLinkedList.makeCyclic(expected + 1);
        iterator = doubleLinkedList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        actual = iterator.next();
        assertEquals(java.util.Optional.of(expected + 1), java.util.Optional.of(actual));
    }

    @Test
    void checkCyclic() {
        if (!(list instanceof DoubleLinkedList)) {
            fail("Invalid test class ");
        }

        DoubleLinkedList<Integer, Integer> doubleLinkedList = (DoubleLinkedList<Integer, Integer>) list;

        Integer elementKeyAndValue = 42;
        // empty list
        boolean actual = doubleLinkedList.isCyclic();
        assertFalse(actual);

        doubleLinkedList.add(elementKeyAndValue, elementKeyAndValue, InsertPosition.BEGINNING);
        actual = doubleLinkedList.isCyclic();
        assertFalse(actual);

        doubleLinkedList.makeCyclic();
        actual = doubleLinkedList.isCyclic();
        assertTrue(actual);

        doubleLinkedList.clear();

        for (Integer i = elementKeyAndValue; i <= elementKeyAndValue + 5; i++) {
            doubleLinkedList.add(i, i, InsertPosition.END);
        }
        actual = doubleLinkedList.isCyclic();
        assertFalse(actual);

        doubleLinkedList.makeCyclic();
        actual = doubleLinkedList.isCyclic();
        assertTrue(actual);

        doubleLinkedList.clear();

        for (Integer i = elementKeyAndValue; i <= elementKeyAndValue + 5; i++) {
            doubleLinkedList.add(i, i, InsertPosition.END);
        }
        doubleLinkedList.makeCyclic(45);
        actual = doubleLinkedList.isCyclic();
        assertTrue(actual);
    }

    @Test
    void toStringTest() {
        Integer item = 42;
        list.add(item, item, InsertPosition.END);
        list.add(item + 1, item + 1, InsertPosition.END);
        list.add(item + 2, item + 2, InsertPosition.END);
        String actual = list.toString();
        String expected = "42\n43\n44\n";
        assertEquals(expected, actual);
    }

    @Test
    void iterator() {
        Integer expected = 42;
        list.add(expected, expected, InsertPosition.BEGINNING);

        for (Integer actual : list) {
            assertEquals(expected, actual);
        }
    }

    @Test
    void listIterator() {
        if (!(list instanceof DoubleLinkedList)) {
            fail("Invalid test class ");
        }

        DoubleLinkedList<Integer, Integer> doubleLinkedList = (DoubleLinkedList<Integer, Integer>) list;

        Integer expected = 42;
        Integer actual;

        for (Integer i = expected; i <= expected + 5; i++) {
            doubleLinkedList.add(i, -i, InsertPosition.END);
        }

        ListIterator listIterator = doubleLinkedList.getListiterator();

        // set list iterator position to the end of the list
        for (Integer i = expected; i <= expected + 5; i++) {
            actual = (Integer) listIterator.next();
            assertEquals(-i, actual);
        }

        for (Integer i = expected + 5; i > expected; i--) {
            actual = (Integer) listIterator.previous();
            assertEquals(-i + 1, actual);
        }

        // remove test
        doubleLinkedList.remove(44);
        doubleLinkedList.remove(42);
        doubleLinkedList.remove(47);

        listIterator = doubleLinkedList.getListiterator();

        // set list iterator position to the end of the list
        for (Integer i = expected + 1; i < expected + 5; i++) {
            if (i == 44) {
                continue;
            }
            actual = (Integer) listIterator.next();
            assertEquals(-i, actual);
        }

        for (Integer i = expected + 4; i > expected + 1; i--) {
            if (i == 45) {
                continue;
            }
            actual = (Integer) listIterator.previous();
            assertEquals(-i + 1, actual);
        }

        // add test
        doubleLinkedList.add(42, -42, 43);
        doubleLinkedList.add(44, -44, 45);
        doubleLinkedList.remove(45);
        doubleLinkedList.add(45, -45, 46);

        listIterator = doubleLinkedList.getListiterator();

        // set list iterator position to the end of the list
        for (Integer i = expected; i <= expected + 4; i++) {
            actual = (Integer) listIterator.next();
            assertEquals(-i, actual);
        }

        for (Integer i = expected + 4; i > expected; i--) {
            actual = (Integer) listIterator.previous();
            assertEquals(-i + 1, actual);
        }
    }

    @Test
    void forEach() {
        Integer expected = 42;
        list.add(expected, expected, InsertPosition.BEGINNING);
        list.forEach(actual -> assertEquals(expected, actual));
    }
}