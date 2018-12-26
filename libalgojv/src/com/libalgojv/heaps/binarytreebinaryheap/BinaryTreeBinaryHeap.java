/*
 * BinaryTreeBinaryHeap.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.heaps.binarytreebinaryheap;

import com.libalgojv.common.interfaces.PriorityQueue;

public class BinaryTreeBinaryHeap<Key extends Comparable<Key>> implements PriorityQueue<Key> {

    @Override
    public void insert(final Key v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Key deleteMax() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Key peek() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException();
    }
}
