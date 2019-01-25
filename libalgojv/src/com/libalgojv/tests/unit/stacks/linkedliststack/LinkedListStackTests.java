/*
 * LinkedListStackTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.stacks.linkedliststack;

import com.libalgojv.common.interfaces.Stack;
import com.libalgojv.stacks.linkedliststack.LinkedListStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTests {
    private Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new LinkedListStack<>();
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    @Test
    void pop() {
        assertNull(stack.pop());

        final int expected = 42;
        stack.push(expected + 2);
        stack.push(expected + 1);
        stack.push(expected);

        int actual = stack.pop();
        assertEquals(expected, actual);

        actual = stack.pop();
        assertEquals(expected + 1, actual);

        actual = stack.pop();
        assertEquals(expected + 2, actual);
    }

    @Test
    void push() {
        final int actual = 42;
        stack.push(actual);

        final int expected = stack.pop();
        assertEquals(expected, actual);
    }

    @Test
    void peek() {
        assertNull(stack.peek());

        final int expected = 42;
        stack.push(expected + 2);
        stack.push(expected + 1);
        stack.push(expected);

        assertEquals(3, stack.getSize());

        int actual = stack.peek();
        assertEquals(expected, actual);

        assertEquals(3, stack.getSize());

        actual = stack.pop();
        assertEquals(expected, actual);
    }

    @Test
    void clear() {
        assertTrue(stack.isEmpty());
        final int expected = 42;
        stack.push(expected + 2);
        stack.push(expected + 1);
        stack.push(expected);

        assertFalse(stack.isEmpty());
        assertEquals(3, stack.getSize());

        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
        assertNull(stack.pop());
    }

    @Test
    void isEmpty() {
        boolean expected = stack.isEmpty();
        assertTrue(expected);

        stack.push(42);
        expected = stack.isEmpty();
        assertFalse(expected);
    }

    @Test
    void getSize() {
        stack.push(42);
        stack.push(42);
        stack.push(42);
        stack.pop();

        final int expected = 2;
        final int actual = stack.getSize();

        assertEquals(expected, actual);
    }

    @Test
    void toStringTest() {
        final int item = 42;
        stack.push(item);
        stack.push(item + 1);
        stack.push(item + 2);
        String actual = stack.toString();
        String expected = "44\n43\n42\n";
        assertEquals(expected, actual);
    }

    @Test
    void iterator() {
        final Integer expected = 42;
        stack.push(expected);
        stack.push(expected + 1);
        Iterator<Integer> iterator = stack.iterator();

        assertTrue(iterator.hasNext());
        Integer actual = iterator.next();
        assertEquals(java.util.Optional.of(expected + 1), java.util.Optional.of(actual));

        assertTrue(iterator.hasNext());
        actual = iterator.next();
        assertEquals(expected, actual);

        assertFalse(iterator.hasNext());
    }

    @Test
    void forEach() {
        StringBuilder stringBuilder = new StringBuilder();
        final int item = 42;
        stack.push(item);
        stack.push(item + 1);
        stack.push(item + 2);
        stack.forEach(stringBuilder::append);
        String actual = stringBuilder.toString();
        String expected = "444342";
        assertEquals(expected, actual);
    }
}