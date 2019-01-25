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

public class ArrayBinaryHeap<Key extends Comparable<Key>> implements PriorityQueue<Key> {
    // TODO: dynamic memory management
    //#region Private Fields
    private final static int DEFAULT_CAPACITY = 64;
    private final int ROOT_INDEX = 1;
    private final BiPredicate<Integer, Integer> compare;
    private Key[] keys;
    private int size;
    private int capacity;
    //#endregion

    //#region Constructors
    public ArrayBinaryHeap(final BinaryHeapType binaryHeapType) {
        this(binaryHeapType, DEFAULT_CAPACITY);
    }

    public ArrayBinaryHeap(final BinaryHeapType binaryHeapType, final int capacity) {
        this.capacity = capacity;
        keys = (Key[]) new Comparable[capacity + 1]; // starts with index 1
        if (binaryHeapType == BinaryHeapType.MAX) {
            compare = (left, right) -> keys[left].compareTo(keys[right]) < 0;
        } else {
            compare = (left, right) -> keys[left].compareTo(keys[right]) > 0;
        }
    }
    //#endregion

    //#region Public Methods
    @Override
    public void insert(final Key key) {
        ++size;
        keys[size] = key;
        swim(size);
    }

    @Override
    public Key delete() {
        if (isEmpty()) {
            return null; // no balancing is needed for empty heap
        }
        Key max = keys[ROOT_INDEX];
        exchange(1, size);
        size--;
        sink(1);
        keys[size + 1] = null;
        return max;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Key peek() {
        return keys[ROOT_INDEX];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 1; i <= size; i++) {
            keys[i] = null;
        }
        size = 0;
    }

    public int getCapacity() {
        return capacity;
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
        Key temp = keys[left];
        keys[left] = keys[right];
        keys[right] = temp;
    }
    //#endregion
}
