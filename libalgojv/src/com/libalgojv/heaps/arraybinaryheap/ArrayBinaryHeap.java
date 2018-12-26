/*
 * ArrayBinaryHeap.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.heaps.arraybinaryheap;

import com.libalgojv.common.interfaces.PriorityQueue;

public class ArrayBinaryHeap<Key extends Comparable<Key>> implements PriorityQueue<Key> {

    //#region Documentation
    // start index is 1
    // array[1] - root of the tree
    // parent node of k is at k/2
    // children of k are at 2k and 2k+1
    //#endregion

    private final int ROOT = 1;

    private Key[] keys;
    private int size;

    public ArrayBinaryHeap(int capacity) {
        keys = (Key[]) new Comparable[capacity + 1];
    }

    //#region Public Methods
    public void insert(final Key key) {
        keys[++size] = key;
        swim(size);
    }

    public Key deleteMax() {
        Key max = keys[ROOT];
        exchange(1, size--);
        sink(1);
        keys[size + 1] = null;
        return max;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Key peek() {
        return keys[ROOT];
    }

    @Override
    public int getSize() {
        return size;
    }
    //#endregion

    //#region Private Methods
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }

    private boolean less(final int left, final int right) {
        return keys[left].compareTo(keys[right]) < 0;
    }

    private void exchange(final int left, final int right) {
        Key temp = keys[left];
        keys[left] = keys[right];
        keys[right] = temp;
    }
    //#endregion
}
