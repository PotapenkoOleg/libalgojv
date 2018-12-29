/*
 * DoubleLinkedList.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.lists.doublelinkedlist;

import com.libalgojv.common.interfaces.BagKeyValue;

import com.libalgojv.common.enums.AlgorithmType;
import com.libalgojv.common.enums.InsertPosition;
import com.libalgojv.common.enums.RemovePosition;
import com.libalgojv.common.interfaces.List;
import com.libalgojv.lists.singlelinkedlist.SingleLinkedList;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class DoubleLinkedList<Key, Value> implements List<Key, Value>, BagKeyValue<Key, Value> {

    //#region Private Fields
    private DoubleLinkedListNode<Key, Value> first;
    private DoubleLinkedListNode<Key, Value> last;
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
    private class DoubleLinkedListNode<Key, Value> {
        private Key key;
        private Value value;
        private DoubleLinkedListNode<Key, Value> next;
        private DoubleLinkedListNode<Key, Value> previous;

        DoubleLinkedListNode(final Key key, final Value value) {
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

        DoubleLinkedListNode<Key, Value> getNext() {
            return next;
        }

        void setNext(DoubleLinkedListNode<Key, Value> next) {
            this.next = next;
        }

        DoubleLinkedListNode<Key, Value> getPrevious() {
            return previous;
        }

        void setPrevious(DoubleLinkedListNode<Key, Value> previous) {
            this.previous = previous;
        }
        //#endregion
    }
    //#endregion

    //#region Public Methods
    @Override
    public void add(final Key key, final Value value, final InsertPosition position) {
        DoubleLinkedListNode<Key, Value> newNode = new DoubleLinkedListNode<>(key, value);
        if (first == null) {
            first = newNode;
            last = newNode;
            return;
        }
        if (position == InsertPosition.BEGINNING) {
            addAtBeginning(newNode);
        }
        if (position == InsertPosition.END) {
            addAtEnd(newNode);
        }
    }

    @Override
    public void add(final Key key, final Value value, final Key before) {
        if (first == null) {
            throw new NoSuchElementException();
        }
        DoubleLinkedListNode<Key, Value> newNode = new DoubleLinkedListNode<>(key, value);
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
            return getSizeRecursive(first, 0);
        }
        return 0;
    }

    public void makeCyclic() {
        if (first == null) {
            return;
        }
        DoubleLinkedListNode<Key, Value> current = first;
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
        DoubleLinkedListNode<Key, Value> current = first;
        DoubleLinkedListNode<Key, Value> cyclicNode = null;
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
        DoubleLinkedListNode<Key, Value> slow = first;
        DoubleLinkedListNode<Key, Value> fast = first.getNext();
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
    private void addAtBeginning(final DoubleLinkedListNode<Key, Value> newNode) {
        first.setPrevious(newNode);
        newNode.setNext(first);
        first = newNode;
    }

    private void addAtEnd(final DoubleLinkedListNode<Key, Value> newNode) {
        if (first == null) {
            first = newNode;
            last = newNode;
            return;
        }
        last.setNext(newNode);
        newNode.setPrevious(last);
        last = newNode;
    }

    private void addByKeyIterative(final Key before, final DoubleLinkedListNode<Key, Value> newNode) {
        DoubleLinkedListNode<Key, Value> current = first;
        while (current.getNext() != null) {
            if (current.getNext().getKey() == before) {
                newNode.setNext(current.getNext());
                current.setNext(newNode);
                newNode.setPrevious(current);
                newNode.getNext().setPrevious(newNode);
                return;
            }
            current = current.getNext();
        }
        throw new NoSuchElementException();
    }

    private void addByKeyRecursive(final Key before, final DoubleLinkedListNode<Key, Value> newNode) {
        throw new UnsupportedOperationException();
    }

    private Value getByKeyIterative(final Key key) {
        DoubleLinkedListNode<Key, Value> current = first;
        do {
            if (current.getValue() == key) {
                return current.getValue();
            }
            current = current.getNext();
        } while (current != null);
        return null;
    }

    private Value getByKeyRecursive(final Key key) {
        return getByKeyRecursive(key, first);
    }

    private Value getByKeyRecursive(final Key key, final DoubleLinkedListNode<Key, Value> current) {
        if (current.getKey() == key) {
            return current.getValue();
        }
        DoubleLinkedListNode<Key, Value> nextNode = current.getNext();
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
        DoubleLinkedListNode<Key, Value> current = first;
        while (current != null) {
            Value result = current.getValue();
            if (result == key) {
                DoubleLinkedListNode<Key, Value> previous = current.getPrevious();
                DoubleLinkedListNode<Key, Value> next = current.getNext();
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

    private Value removeByKeyRecursive(final Key key, final DoubleLinkedListNode<Key, Value> current) {
        throw new UnsupportedOperationException();
    }

    private int getSizeIterative() {
        int size = 0;
        DoubleLinkedListNode<Key, Value> current = first;
        do {
            size++;
            current = current.getNext();
        } while (current != null);
        return size;
    }

    private int getSizeRecursive(DoubleLinkedListNode<Key, Value> current, int size) {
        size++;
        DoubleLinkedListNode<Key, Value> next = current.getNext();
        if (next != null) {
            size = getSizeRecursive(next, size);
        }
        return size;
    }
    //#endregion

    //#region Iterable
    @Override
    public Iterator<Value> iterator() {
        return new Iterator<Value>() {
            private DoubleLinkedListNode<Key, Value> current;

            {
                this.current = new DoubleLinkedListNode<>(null, null);
                current.setNext(first);
                // don't set first's previous - it might corrupt list
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

    //#region ListIterator
    public ListIterator<Value> getListiterator() {
        return new ListIterator<Value>() {
            private DoubleLinkedListNode<Key, Value> current;
            private int currentIndex = -1;

            {
                this.current = new DoubleLinkedListNode<>(null, null);
                current.setNext(first);
                // don't set first's previous - it might corrupt list
            }

            @Override
            public boolean hasNext() {
                return (current != null) && (current.getNext() != null);
            }

            @Override
            public Value next() {
                if (hasNext()) {
                    currentIndex++;
                    current = current.getNext();
                    return current.getValue();
                }
                throw new NoSuchElementException();
            }

            @Override
            public boolean hasPrevious() {
                return (current != null) && (current.getPrevious() != null);
            }

            @Override
            public Value previous() {
                if (hasPrevious()) {
                    currentIndex--;
                    current = current.getPrevious();
                    return current.getValue();
                }
                throw new NoSuchElementException();
            }

            @Override
            public int nextIndex() {
                return currentIndex + 1;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(Value value) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(Value value) {
                throw new UnsupportedOperationException();
            }
        };
    }
    //#endregion

}
