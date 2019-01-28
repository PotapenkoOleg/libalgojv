/*
 * BinaryTree.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.common.interfaces;

import java.util.function.Consumer;

public interface BinarySearchTree<Key extends Comparable<Key>, Value> extends Iterable<Value> {
    void add(final Key key, final Value value);

    Value get(final Key key);

    Value remove(final Key key);

    Key getMin();

    Key getMax();

    Key getFloor();

    Key getCeiling();

    int getRank();

    void preorder(final Consumer<? super Value> action);

    void inorder(final Consumer<? super Value> action);

    void postorder(final Consumer<? super Value> action);

    void clear();

    boolean isEmpty();

    int getSize();
}
