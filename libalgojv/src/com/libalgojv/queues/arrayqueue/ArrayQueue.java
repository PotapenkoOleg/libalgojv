/*
 * ArrayQueue.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.queues.arrayqueue;

import com.libalgojv.common.interfaces.Bag;
import com.libalgojv.common.interfaces.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class ArrayQueue<E> implements Queue<E>, Bag<E> {

    //#region Private Fields
    private E[] array;
    private int size = 0;
    //#endregion

    //#region Constructors
    public ArrayQueue() {
        this(2);
    }

    public ArrayQueue(final int n) {
        array = (E[]) new Object[n];
    }
    //#endregion

    //#region Public Methods
    @Override
    public void enqueue(final E item) {
        if (size == array.length) {
            resize(2 * array.length);
        }
        // TODO: add to the end and read in reverse
        ++size;
        for (int i = size - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = item;
    }

    @Override
    public E dequeue() {
        if (size == 0) {
            return null;
        }
        E result = array[--size];
        array[size] = null; // make sure we don't copy garbage during array resize
        if ((size > 0) && (size == array.length / 4)) {
            resize(array.length / 2);
        }
        return result;
    }

    @Override
    public E peek() {
        // TODO:
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        // TODO:
        throw new UnsupportedOperationException();
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
        return array.length;
    }

    @Override
    public void add(E item) {
        enqueue(item);
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
