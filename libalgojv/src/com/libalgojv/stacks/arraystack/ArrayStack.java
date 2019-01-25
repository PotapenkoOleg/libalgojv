/*
 * ArrayStack.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.stacks.arraystack;

import com.libalgojv.common.interfaces.Bag;
import com.libalgojv.common.interfaces.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public final class ArrayStack<E> implements Stack<E>, Bag<E> {

    //#region Private Fields
    private final static int DEFAULT_CAPACITY = 2; // should be power of two
    private E[] array;
    private int size = 0;
    private int capacity;
    //#endregion

    //#region Constructors
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(final int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }
    //#endregion

    //#region Public Methods
    @Override
    public E pop() {
        if (size == 0) {
            return null;
        }
        E result = array[--size];
        array[size] = null; // make sure we don't copy garbage during array resize
        if ((size > 0) && (size == capacity / 4)) {
            resize(capacity / 2);
        }
        return result;
    }

    @Override
    public void push(final E item) {
        if (size == capacity) {
            resize(2 * capacity);
        }
        array[size++] = item;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        return array[size - 1];
    }

    @Override
    public void clear() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public void add(final E item) {
        push(item);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.forEach(item -> {
            stringBuilder.append(item);
            stringBuilder.append("\n");
        });
        return stringBuilder.toString();
    }
    //#endregion

    //#region Private Methods
    private void resize(int capacity) {
        this.capacity = capacity;
        E[] copy = (E[]) new Object[capacity];
        System.arraycopy(array, 0, copy, 0, size);
        array = copy;
    }
    //#endregion

    //#region Iterable
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current = size;

            @Override
            public boolean hasNext() {
                return current > 0;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return array[--current];
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        for (E value : this) {
            action.accept(value);
        }
    }
    //#endregion
}
