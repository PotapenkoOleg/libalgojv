/*
 * SingleLinkedList.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.lists.singlelinkedlist;

import com.libalgojv.common.interfaces.BagKeyValue;
import com.libalgojv.common.interfaces.List;
import com.libalgojv.common.enums.AlgorithmType;
import com.libalgojv.common.enums.InsertPosition;
import com.libalgojv.common.enums.RemovePosition;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class SingleLinkedList<Key, Value> implements List<Key, Value>, BagKeyValue<Key, Value> {

    //#region Private Fields
    private SingleLinkedListNode<Key, Value> first;
    private final AlgorithmType algorithmType;
    //#endregion

    //#region Constructors
    public SingleLinkedList() {
        this(AlgorithmType.ITERATIVE);
    }

    public SingleLinkedList(AlgorithmType algorithmType) {
        this.algorithmType = algorithmType;
    }
    //#endregion

    //#region Node Class
    // Make Node class protected to use it with test accessors, should be private
    class SingleLinkedListNode<Key, Value> {
        private final Key key;
        private final Value value;
        private SingleLinkedListNode<Key, Value> next;

        SingleLinkedListNode(final Key key, final Value value) {
            this.key = key;
            this.value = value;
        }

        //region Getters and Setters
        Key getKey() {
            return key;
        }

        Value getValue() {
            return value;
        }

        SingleLinkedListNode<Key, Value> getNext() {
            return next;
        }

        void setNext(SingleLinkedListNode<Key, Value> next) {
            this.next = next;
        }
        //endregion
    }
    //#endregion

    //#region Public Methods
    @Override
    public void add(final Key key, final Value value, final InsertPosition position) {
        SingleLinkedListNode<Key, Value> newNode = new SingleLinkedListNode<>(key, value);
        if (position == InsertPosition.BEGINNING) {
            addAtBeginning(newNode);
        }
        if (position == InsertPosition.END) {
            addAtEnd(newNode);
        }
    }

    @Override
    public void add(Key key, Value value, Key before) {
        if (first == null) {
            throw new NoSuchElementException();
        }
        SingleLinkedListNode<Key, Value> newNode = new SingleLinkedListNode<>(key, value);
        if (first.getKey() == before) {
            addAtBeginning(newNode);
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            addByKeyIterative(before, newNode);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            addByKeyRecursive(before, newNode);
        }
    }

    @Override
    public void add(Key key, Value value) {
        add(key, value, InsertPosition.BEGINNING);
    }

    @Override
    public Value remove(final RemovePosition position) {
        if (first == null) {
            return null;
        }
        if (position == RemovePosition.BEGINNING) {
            return removeFromBeginning();
        }
        if (position == RemovePosition.END) {
            return removeFromEnd();
        }
        return null;
    }

    @Override
    public Value remove(final Key key) {
        if (first == null) {
            return null;
        }
        if (first.getKey() == key) {
            Value result = first.getValue();
            first = first.getNext();
            return result;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            return removeByKeyIterative(key);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            return removeByKeyRecursive(key);
        }
        return null;
    }

    @Override
    public Value get(final Key key) {
        if (first == null) {
            return null;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            return getByKeyIterative(key);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            return getByKeyRecursive(key);
        }
        return null;
    }

    @Override
    public void clear() {
        first = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int getSize() {
        if (first == null) {
            return 0;
        }
        if (first.getNext() == null) {
            return 1;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            return sizeIterative();
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            return sizeRecursive(first, 0);
        }
        return 0;
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

    public Value getNthElementFromEnd(final int n) {
        if (first == null) {
            return null;
        }
        SingleLinkedListNode<Key, Value> current = first;
        SingleLinkedListNode<Key, Value> previous = null;
        int distanceCounter = 0;
        while (true) {
            if (distanceCounter == n) {
                previous = first;
            }
            distanceCounter++;
            current = current.getNext();
            if (current == null) {
                break;
            }
            if (distanceCounter > n) {
                // previous goes behind current, so it never be null
                // it always assigned, because distanceCounter equals to n before it becomes greater
                previous = previous.getNext();
            }
        }
        // no such position in list, eg 100th position in list with length 5
        if (previous == null) {
            return null;
        }
        return previous.getValue();
    }

    public void makeCyclic() {
        if (first == null) {
            return;
        }
        SingleLinkedListNode<Key, Value> current = first;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(first);
    }

    public void makeCyclic(final Key key) {
        if (first == null) {
            return;
        }
        SingleLinkedListNode<Key, Value> current = first;
        SingleLinkedListNode<Key, Value> cyclicNode = null;
        while (current.getNext() != null) {
            if (current.getKey() == key) {
                cyclicNode = current;
            }
            current = current.getNext();
        }
        current.setNext(cyclicNode);
    }

    public boolean isCyclic() {
        if (first == null) {
            return false;
        }
        if (first.getNext() == null) {
            return false;
        }
        SingleLinkedListNode<Key, Value> slow = first;
        SingleLinkedListNode<Key, Value> fast = first.getNext();
        while (true) {
            if ((fast == null) || (fast.getNext() == null)) {
                return false;
            } else if ((fast == slow) || (fast.getNext() == slow)) {
                return true;
            } else {
                slow = slow.getNext();
                fast = fast.getNext().getNext();
            }
        }
    }

    //#endregion

    //#region Private Methods
    private void addAtBeginning(SingleLinkedListNode<Key, Value> newNode) {
        newNode.setNext(first);
        first = newNode;
    }

    private void addAtEnd(SingleLinkedListNode<Key, Value> newNode) {
        if (first == null) {
            first = newNode;
            return;
        }
        if (algorithmType == AlgorithmType.ITERATIVE) {
            addAtEndIterative(newNode);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            addAtEndRecursive(newNode);
        }
    }

    private void addAtEndIterative(SingleLinkedListNode<Key, Value> newNode) {
        SingleLinkedListNode<Key, Value> current = first;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
    }

    private void addAtEndRecursive(SingleLinkedListNode<Key, Value> newNode) {
        addAtEndRecursive(first, newNode);
    }

    private void addAtEndRecursive(
            SingleLinkedListNode<Key, Value> current,
            SingleLinkedListNode<Key, Value> newNode
    ) {
        if (current.getNext() == null) {
            current.setNext(newNode);
        } else {
            addAtEndRecursive(current.getNext(), newNode);
        }
    }

    private void addByKeyIterative(Key before, SingleLinkedListNode<Key, Value> newNode) {
        SingleLinkedListNode<Key, Value> current = first;
        while (current.getNext() != null) {
            if (current.getNext().getKey() == before) {
                newNode.setNext(current.getNext());
                current.setNext(newNode);
                return;
            }
            current = current.getNext();
        }
        throw new NoSuchElementException();
    }

    private void addByKeyRecursive(Key before, SingleLinkedListNode<Key, Value> newNode) {
        addByKeyRecursive(before, first, newNode);
    }

    private void addByKeyRecursive(
            Key before,
            SingleLinkedListNode<Key, Value> current,
            SingleLinkedListNode<Key, Value> newNode
    ) {
        if (current.getNext() == null) {
            throw new NoSuchElementException();
        }
        if (current.getNext().getKey() == before) {
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        } else {
            addByKeyRecursive(before, current.getNext(), newNode);
        }
    }

    private Value removeFromBeginning() {
        Value result = first.getValue();
        first = first.getNext();
        return result;
    }

    private Value removeFromEnd() {
        if (algorithmType == AlgorithmType.ITERATIVE) {
            return removeFromEndIterative();
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            return removeFromEndRecursive();
        }
        return null;
    }

    private Value removeFromEndIterative() {
        if (first.getNext() == null) {
            Value result = first.getValue();
            first = null;
            return result;
        }
        SingleLinkedListNode<Key, Value> current = first;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        Value result = current.getNext().getValue();
        current.setNext(null);
        return result;
    }

    private Value removeFromEndRecursive() {
        if (first.getNext() == null) {
            Value result = first.getValue();
            first = null;
            return result;
        }
        return removeFromEndRecursive(first);
    }

    private Value removeFromEndRecursive(SingleLinkedListNode<Key, Value> current) {
        if (current.getNext().getNext() == null) {
            Value result = current.getNext().getValue();
            current.setNext(null);
            return result;
        }
        return removeFromEndRecursive(current.getNext());
    }

    private Value removeByKeyIterative(Key key) {
        SingleLinkedListNode<Key, Value> current = first;
        while ((current.getNext() != null) && (current.getNext().getKey() != key)) {
            current = current.getNext();
        }
        if (current.getNext() != null) {
            Value result = current.getNext().getValue();
            current.setNext(current.getNext().getNext());
            return result;
        }
        return null;
    }

    private Value removeByKeyRecursive(Key key) {
        return removeByKeyRecursive(key, first);
    }

    private Value removeByKeyRecursive(Key key, SingleLinkedListNode<Key, Value> current) {
        SingleLinkedListNode<Key, Value> nextNode = current.getNext();
        if (nextNode != null) {
            if (nextNode.getKey() == key) {
                Value result = nextNode.getValue();
                current.setNext(nextNode.getNext());
                return result;
            }
            return removeByKeyRecursive(key, nextNode);
        }
        return null;
    }

    private Value getByKeyIterative(Key key) {
        SingleLinkedListNode<Key, Value> current = first;
        do {
            if (current.getKey() == key) {
                return current.getValue();
            }
            current = current.getNext();
        } while (current != null);
        return null;
    }

    private Value getByKeyRecursive(Key key) {
        return getByKeyRecursive(key, first);
    }

    private Value getByKeyRecursive(Key key, SingleLinkedListNode<Key, Value> current) {
        if (current.getKey() == key) {
            return current.getValue();
        }
        SingleLinkedListNode<Key, Value> nextNode = current.getNext();
        if (nextNode != null) {
            return getByKeyRecursive(key, nextNode);
        }
        return null;
    }

    private int sizeRecursive(SingleLinkedListNode<Key, Value> current, int size) {
        size++;
        SingleLinkedListNode<Key, Value> next = current.getNext();
        if (next != null) {
            size = sizeRecursive(next, size);
        }
        return size;
    }

    private int sizeIterative() {
        SingleLinkedListNode<Key, Value> current = first;
        int size = 0;
        do {
            size++;
            current = current.getNext();
        } while (current != null);
        return size;
    }
    //#endregion

    //#region Iterable
    @Override
    public Iterator<Value> iterator() {
        return new Iterator<Value>() {
            private SingleLinkedListNode<Key, Value> current;

            {
                this.current = new SingleLinkedListNode<>(null, null);
                current.setNext(first);
            }

            @Override
            public boolean hasNext() {
                return current.getNext() != null;
            }

            @Override
            public Value next() {
                if (hasNext()) {
                    current = current.getNext();
                    return current.getValue();
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Value> action) {
        for (Value value : this) {
            action.accept(value);
        }
    }
    //#endregion
}
