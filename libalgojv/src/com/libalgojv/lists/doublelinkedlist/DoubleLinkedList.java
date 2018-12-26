/*
 * DoubleLinkedList.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.lists.doublelinkedlist;

import com.libalgojv.common.interfaces.BagKeyValue;
import com.libalgojv.common.interfaces.List;
import com.libalgojv.common.enums.AlgorithmType;
import com.libalgojv.common.enums.InsertPosition;
import com.libalgojv.common.enums.RemovePosition;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class DoubleLinkedList<Key, Value> implements List<Key, Value>, BagKeyValue<Key, Value> {

    //#region Private Fields
    private DoublelinkedListNode<Key, Value> first;
    private DoublelinkedListNode<Key, Value> last;
    private final AlgorithmType algorithmType;
    //#endregion

    //#region Constructors
    public DoubleLinkedList() {
        this(AlgorithmType.RECURSIVE);
    }

    public DoubleLinkedList(AlgorithmType algorithmType) {
        this.algorithmType = algorithmType;
    }
    //#endregion

    //region Node Class
    private class DoublelinkedListNode<Key, Value> {
        private Key key;
        private Value value;
        private DoublelinkedListNode<Key, Value> next;
        private DoublelinkedListNode<Key, Value> previous;

        DoublelinkedListNode(final Key key, final Value value) {
            this.key = key;
            this.value = value;
        }

        //#region Getters And Setters
        Key getKey() {
            return key;
        }

        Value getValue() {
            return value;
        }

        DoublelinkedListNode<Key, Value> getNext() {
            return next;
        }

        void setNext(DoublelinkedListNode<Key, Value> next) {
            this.next = next;
        }

        DoublelinkedListNode<Key, Value> getPrevious() {
            return previous;
        }

        void setPrevious(DoublelinkedListNode<Key, Value> previous) {
            this.previous = previous;
        }
        //#endregion
    }
    //#endregion

    //#region Public Methods
    @Override
    public void add(final Key key, final Value value, final InsertPosition position) {
        DoublelinkedListNode<Key, Value> newNode = new DoublelinkedListNode<>(key, value);
        if (position == InsertPosition.BEGINNING) {
            addAtBeginning(newNode);
        }
        if (position == InsertPosition.END) {
            addAtEnd(newNode);
        }
    }

    @Override
    public void add(final Key key, final Value value, final Key before) {
        if (algorithmType == AlgorithmType.ITERATIVE) {
            addByKeyIterative(key, value, before);
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            addByKeyRecursive(key, value, before);
        }
    }

    @Override
    public void add(Key key, Value value) {
        add(key, value, InsertPosition.BEGINNING);
    }

    @Override
    public Value remove(final RemovePosition position) {
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
        if (first == last) {
            Value result = first.getValue();
            first = null;
            last = null;
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
        last = null;
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
        if (algorithmType == AlgorithmType.ITERATIVE) {
            return getSizeIterative();
        }
        if (algorithmType == AlgorithmType.RECURSIVE) {
            return getSizeRecursive();
        }
        return 0;
    }

    public void makeCyclic() {
        if (first == null) {
            return;
        }
        DoublelinkedListNode<Key, Value> current = first;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(first);
        first.setPrevious(current);
    }

    public void makeCyclic(final Key key) {
        if (first == null) {
            return;
        }
        DoublelinkedListNode<Key, Value> current = first;
        DoublelinkedListNode<Key, Value> cyclicNode = null;
        while (current.getNext() != null) {
            if (current.getValue() == key) {
                cyclicNode = current;
            }
            current = current.getNext();
        }
        current.setNext(cyclicNode);
        cyclicNode.setPrevious(current); // creating cycle in both directions, previous node is lost now
    }

    public boolean isCyclic() {
        if (first == null) {
            return false;
        }
        if (first.getNext() == null) {
            return false;
        }
        DoublelinkedListNode<Key, Value> slow = first;
        DoublelinkedListNode<Key, Value> fast = first.getNext();
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
    private void addAtBeginning(final DoublelinkedListNode<Key, Value> newNode) {
        if (first == null) {
            first = newNode;
            last = newNode;
            return;
        }
        first.setPrevious(newNode);
        newNode.setNext(first);
        first = newNode;
    }

    private void addAtEnd(final DoublelinkedListNode<Key, Value> newNode) {
        if (first == null) {
            first = newNode;
            last = newNode;
            return;
        }
        last.setNext(newNode);
        newNode.setPrevious(last);
        last = newNode;
    }

    private void addByKeyIterative(final Key key, final Value value, final Key before) {
        throw new UnsupportedOperationException();
    }

    private void addByKeyRecursive(final Key key, final Value value, final Key before) {
        throw new UnsupportedOperationException();
    }

    private Value getByKeyIterative(final Key key) {
        DoublelinkedListNode<Key, Value> current = first;
        do {
            if (current.getValue() == key) {
                return current.getValue();
            }
            current = current.getNext();
        } while (current != null);
        return null;
    }

    private Value getByKeyRecursive(final Key key){
        return getByKeyRecursive(key, first);
    }

    private Value getByKeyRecursive(final Key key, final DoublelinkedListNode<Key, Value> current) {
        if (current.getKey() == key) {
            return current.getValue();
        }
        DoublelinkedListNode<Key, Value> nextNode = current.getNext();
        if (nextNode != null) {
            return getByKeyRecursive(key, nextNode);
        }
        return null;
    }

    private Value removeFromBeginning() {
        if (first == null) {
            return null;
        }
        Value result = first.getValue();
        if (first == last) {
            first = null;
            last = null;
            return result;
        }
        first = first.getNext();
        first.setPrevious(null);
        return result;
    }

    private Value removeFromEnd() {
        if (first == null) {
            return null;
        }
        Value result = last.getValue();
        if (first == last) {
            first = null;
            last = null;
            return result;
        }
        last = last.getPrevious();
        last.setNext(null);
        return result;
    }

    private Value removeByKeyIterative(final Key key) {
        DoublelinkedListNode<Key, Value> current = first;
        while (current != null) {
            Value result = current.getValue();
            if (result == key) {
                DoublelinkedListNode<Key, Value> previous = current.getPrevious();
                DoublelinkedListNode<Key, Value> next = current.getNext();
                if (current == first) {
                    first = next;
                    first.setPrevious(null);
                    return result;
                }
                if (current == last) {
                    last = previous;
                    last.setNext(null);
                    return result;
                }
                previous.setNext(next);
                next.setPrevious(previous);
                return result;
            }
            current = current.getNext();
        }
        return null;
    }

    private Value removeByKeyRecursive(final Key key) {
        return removeByKeyRecursive(key, first);
    }

    private Value removeByKeyRecursive(final Key key, final DoublelinkedListNode<Key, Value> current) {
        throw new UnsupportedOperationException();
    }

    private int getSizeIterative() {
        int size = 0;
        DoublelinkedListNode<Key, Value> current = first;
        do {
            size++;
            current = current.getNext();
        } while (current != null);
        return size;
    }

    private int getSizeRecursive() {
        throw new UnsupportedOperationException();
    }
    //#endregion

    //#region Iterable
    @Override
    public Iterator<Value> iterator() {
        return new Iterator<Value>() {
            private DoublelinkedListNode<Key, Value> current;

            {
                this.current = new DoublelinkedListNode<Key, Value>(null, null);
                current.setNext(first);
                // don't set first previous - it might corrupt list
            }

            @Override
            public boolean hasNext() {
                return (current != null) && (current.getNext() != null);
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
        for (Value item : this) {
            action.accept(item);
        }
    }
    //#endregion
}
