/*
 * ArrayBinaryHeap.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.heaps.arraybinaryheap;

import com.libalgojv.common.enums.BinaryHeapType;
import com.libalgojv.common.interfaces.PriorityQueue;

import java.util.function.BiPredicate;

public class ArrayBinaryHeap<E extends Comparable<E>> implements PriorityQueue<E> {
    //#region Private Fields
    private final static int DEFAULT_CAPACITY = 64;
    private final int ROOT_INDEX = 1;
    private final BiPredicate<Integer, Integer> compare;
    private E[] array;
    private int size;
    private int capacity;
    //#endregion

    //#region Constructors
    public ArrayBinaryHeap(final BinaryHeapType binaryHeapType) {
        this(binaryHeapType, DEFAULT_CAPACITY);
    }

    public ArrayBinaryHeap(final BinaryHeapType binaryHeapType, final int capacity) {
        this.capacity = capacity + 1;// array starts with index 1
        array = (E[]) new Comparable[capacity + 1];
        if (binaryHeapType == BinaryHeapType.MAX) {
            compare = (left, right) -> array[left].compareTo(array[right]) < 0;
        } else {
            compare = (left, right) -> array[left].compareTo(array[right]) > 0;
        }
    }
    //#endregion

    //#region Public Methods
    @Override
    public void insert(final E item) {
        if (size == getCapacity()) {
            resize(2 * getCapacity());
        }
        ++size;
        array[size] = item;
        swim(size);
    }

    @Override
    public E delete() {
        if ((size > 0) && (size == getCapacity() / 4)) {
            resize(getCapacity() / 2);
        }
        if (isEmpty()) {
            return null; // no balancing is needed for empty heap
        }
        E max = array[ROOT_INDEX];
        exchange(1, size);
        size--;
        sink(1);
        array[size + 1] = null;
        return max;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E peek() {
        return array[ROOT_INDEX];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 1; i <= size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int getCapacity() {
        return capacity - 1;
    }
    //#endregion

    //#region Private Methods
    private void swim(int k) {
        while ((k > 1) && compare.test(k / 2, k)) {
            exchange(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if ((j < size) && compare.test(j, j + 1)) {
                j++;
            }
            if (!compare.test(k, j)) {
                break;
            }
            exchange(k, j);
            k = j;
        }
    }

    private void exchange(final int left, final int right) {
        E temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    private void resize(int capacity) {
        capacity++; // we don't use element at 0 position
        this.capacity = capacity;
        E[] copy = (E[]) new Comparable[capacity];
        System.arraycopy(array, 1, copy, 1, size);
        array = copy;
    }
    //#endregion
}
