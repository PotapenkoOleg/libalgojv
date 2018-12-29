/*
 * ArrayStackTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.stacks.arraystack;

import com.libalgojv.common.interfaces.Stack;
import com.libalgojv.stacks.arraystack.ArrayStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTests {
    private Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new ArrayStack<>();
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    @Test
    void pop() {
        final int actual = 42;
        stack.push(actual);
        stack.push(actual);
        stack.push(actual);

        final int expected = stack.pop();
        assertEquals(expected, actual);
    }

    @Test
    void push() {
        final int actual = 42;
        stack.push(actual);

        final int expected = stack.pop();
        assertEquals(expected, actual);
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
    void size() {
        stack.push(42);
        stack.push(42);
        stack.push(42);
        stack.pop();

        final int expected = 2;
        int actual = stack.getSize();

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

    @Test
    void dynamicSizeIncrease() {
        if (!(stack instanceof ArrayStack)) {
            fail("Invalid stack type");
        }
        ArrayStack currentStack = (ArrayStack) stack;
        int expected = 2;
        int actual = currentStack.getCapacity();
        assertEquals(expected, actual);
        stack.push(42);
        stack.push(42);
        stack.push(42);
        expected = 4;
        actual = currentStack.getCapacity();
        assertEquals(expected, actual);
        stack.push(44);
        stack.push(44);
        expected = 8;
        actual = currentStack.getCapacity();
        assertEquals(expected, actual);
    }

    @Test
    void dynamicSizeDecrease() {
        if (!(stack instanceof ArrayStack)) {
            fail("Invalid stack type");
        }
        ArrayStack currentStack = (ArrayStack) stack;
        int expected = 2;
        int actual = currentStack.getCapacity();
        assertEquals(expected, actual);
        for (int i = 0; i < 8; i++) {
            stack.push(42);
        }
        expected = 8;
        actual = currentStack.getCapacity();
        assertEquals(expected, actual);
        for (int i = 0; i < 6; i++) {
            stack.pop();
        }
        expected = 4;
        actual = currentStack.getCapacity();
        assertEquals(expected, actual);
        stack.pop();
        stack.pop();
        expected = 2;
        actual = currentStack.getCapacity();
        assertEquals(expected, actual);
    }
}