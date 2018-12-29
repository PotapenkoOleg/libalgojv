/*
 * BinaryTree.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.trees.binarytree;

import com.libalgojv.common.enums.AlgorithmType;
import com.libalgojv.common.interfaces.BinarySearchTree;

import java.util.Iterator;
import java.util.Stack;
import java.util.function.Consumer;

public class BinaryTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    //#region Private Fields
    private BinaryTreeNode<Key, Value> root;
    private final AlgorithmType algorithmType;
    //#endregion

    //#region Node Class
    private class BinaryTreeNode<Key extends Comparable<Key>, Value> {
        private final Key key;
        private final Value value;
        private BinaryTreeNode<Key, Value> left;
        private BinaryTreeNode<Key, Value> right;

        //#region Getters and Setters
        Key getKey() {
            return key;
        }

        Value getValue() {
            return value;
        }

        BinaryTreeNode<Key, Value> getLeft() {
            return left;
        }

        void setLeft(BinaryTreeNode<Key, Value> left) {
            this.left = left;
        }

        BinaryTreeNode<Key, Value> getRight() {
            return right;
        }

        void setRight(BinaryTreeNode<Key, Value> right) {
            this.right = right;
        }
        //#endregion

        BinaryTreeNode(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
    //#endregion

    //#region Constructors
    public BinaryTree() {
        this(AlgorithmType.RECURSIVE);
    }

    public BinaryTree(AlgorithmType algorithmType) {
        this.algorithmType = algorithmType;
    }
    //#endregion

    //#region Public Methods
    @Override
    public void add(final Key key, final Value value) {
        BinaryTreeNode<Key, Value> newNode = new BinaryTreeNode<>(key, value);
        if (root == null) {
            root = newNode;
            return;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            addIterative(newNode);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            addRecursive(newNode);
        }
    }

    @Override
    public Value get(final Key key) {
        if (root == null) {
            return null;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            return getIterative(key);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            return getRecursive(key);
        }
        return null;
    }

    @Override
    public Value remove(final Key key) {
        if (root == null) {
            return null;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            return removeIterative(key);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            return removeRecursive(key);
        }
        return null;
    }

    @Override
    public void preorder(final Consumer<? super Value> action) {
        if (root == null) {
            return;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            preorderIterative(action);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            preorderRecursive(action);
        }
    }

    @Override
    public void inorder(final Consumer<? super Value> action) {
        if (root == null) {
            return;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            inorderIterative(action);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            inorderIterative(action);
            //inorderRecursive(action);
        }
    }

    @Override
    public void postorder(final Consumer<? super Value> action) {
        if (root == null) {
            return;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            postorderIterative(action);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            postorderRecursive(action);
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getHeight() {
        if (root == null) {
            return 0;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            return heightIterative();
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            return heightRecursive();
        }
        return 0;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
    //#endregion

    //#region Private Methods
    private void addIterative(final BinaryTreeNode<Key, Value> newNode) {
        throw new UnsupportedOperationException();
    }

    private void addRecursive(final BinaryTreeNode<Key, Value> newNode) {
        addRecursive(root, newNode);
    }

    private void addRecursive(
            final BinaryTreeNode<Key, Value> current,
            final BinaryTreeNode<Key, Value> newNode
    ) {
        int compareResult = newNode.getKey().compareTo(current.getKey());
        if (compareResult < 0) {
            BinaryTreeNode<Key, Value> left = current.getLeft();
            if (left == null) {
                current.setLeft(newNode);
                return;
            }
            addRecursive(left, newNode);
        }
        if (compareResult == 0) {
            // the element is already in tree
            return;
        }
        if (compareResult > 0) {
            BinaryTreeNode<Key, Value> right = current.getRight();
            if (right == null) {
                current.setRight(newNode);
                return;
            }
            addRecursive(right, newNode);
        }
    }

    private Value getIterative(final Key key) {
        BinaryTreeNode<Key, Value> current = root;
        while (current != null) {
            int result = key.compareTo(current.getKey());
            if (result < 0) {
                current = current.getLeft();
            }
            if (result == 0) {
                return current.getValue();
            }
            if (result > 0) {
                current = current.getRight();
            }
        }
        return null;
    }

    private Value getRecursive(final Key key) {
        return getRecursive(root, key);
    }

    private Value getRecursive(final BinaryTreeNode<Key, Value> current, final Key key) {
        if (current == null) {
            return null;
        }
        int compareResult = key.compareTo(current.getKey());
        if (compareResult < 0) {
            return getRecursive(current.getLeft(), key);
        }
        if (compareResult > 0) {
            return getRecursive(current.getRight(), key);
        }
        return current.getValue();
    }

    private Value removeIterative(final Key key) {
        throw new UnsupportedOperationException();
        //return null;
    }

    private Value removeRecursive(final Key key) {
        return removeRecursive(root, key);
    }

    private Value removeRecursive(final BinaryTreeNode<Key, Value> current, final Key key) {
        throw new UnsupportedOperationException();
    }

    private void preorderIterative(final Consumer<? super Value> action) {
        Stack<BinaryTreeNode<Key, Value>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            BinaryTreeNode<Key, Value> current = stack.pop();
            action.accept(current.getValue());
            BinaryTreeNode<Key, Value> node = current.getRight();
            if (node != null) {
                stack.push(node);
            }
            node = current.getLeft();
            if (node != null) {
                stack.push(node);
            }
        }
    }

    private void preorderRecursive(final Consumer<? super Value> action) {
        preorderRecursive(root, action);
    }

    private void preorderRecursive(
            final BinaryTreeNode<Key, Value> current,
            final Consumer<? super Value> action
    ) {
        if (current == null) {
            return;
        }
        action.accept(current.getValue());
        preorderRecursive(current.getLeft(), action);
        preorderRecursive(current.getRight(), action);
    }

    private void inorderIterative(final Consumer<? super Value> action) {
        // https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
        Stack<BinaryTreeNode<Key, Value>> stack = new Stack<>();
        BinaryTreeNode<Key, Value> current = root;
        while ((current != null) && (!stack.empty())) {

            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            action.accept(current.getValue());
            current = current.getRight();
        }
    }

    private void inorderRecursive(final Consumer<? super Value> action) {
        inorderRecursive(root, action);
    }

    private void inorderRecursive(
            final BinaryTreeNode<Key, Value> current,
            final Consumer<? super Value> action
    ) {
        if (current == null) {
            return;
        }
        inorderRecursive(current.getLeft(), action);
        action.accept(current.getValue());
        inorderRecursive(current.getRight(), action);
    }

    private void postorderIterative(final Consumer<? super Value> action) {
        //https://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
        Stack<BinaryTreeNode<Key, Value>> stack = new Stack<>();
        BinaryTreeNode<Key, Value> current = root;
        BinaryTreeNode<Key, Value> previous = null;

        stack.push(current);
        while (!stack.empty()) {
            current = stack.peek();
            if (previous == null || previous.getLeft() == current || previous.getRight() == current) {
                if (current.getLeft() != null) {
                    stack.push(current.getLeft());
                } else if (current.getRight() != null) {
                    stack.push(current.getRight());
                } else {
                    Value value = stack.pop().getValue();
                    action.accept(value);
                }
            } else if (current.getLeft() == previous) {
                if (current.getRight() != null)
                    stack.push(current.getRight());
                else {
                    stack.pop();
                    Value value = stack.pop().getValue();
                    action.accept(value);
                }

            } else if (current.getRight() == previous) {
                stack.pop();
                Value value = stack.pop().getValue();
                action.accept(value);
            }

            previous = current;
        }
    }

    private void postorderRecursive(final Consumer<? super Value> action) {
        postorderRecursive(root, action);
    }

    private void postorderRecursive(
            final BinaryTreeNode<Key, Value> current,
            final Consumer<? super Value> action
    ) {
        if (current == null) {
            return;
        }
        postorderRecursive(current.getLeft(), action);
        postorderRecursive(current.getRight(), action);
        action.accept(current.getValue());
    }

    private int heightIterative() {
        throw new UnsupportedOperationException();
    }

    private int heightRecursive() {
        return heightRecursive(root);
    }

    private int heightRecursive(final BinaryTreeNode<Key, Value> current) {
        if (current == null) {
            return 0;
        }
        return 1 + Math.max(heightRecursive(current.getLeft()), heightRecursive(current.getRight()));
    }
    //#endregion

    //#region Iterable
    @Override
    public Iterator<Value> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEach(Consumer<? super Value> action) {
        for (Value value : this) {
            action.accept(value);
        }
    }
    //#endregion
}
