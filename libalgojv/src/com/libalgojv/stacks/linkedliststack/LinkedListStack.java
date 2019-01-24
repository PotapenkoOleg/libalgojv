/*
 * LinkedListStack.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.stacks.linkedliststack;

import com.libalgojv.common.interfaces.Bag;
import com.libalgojv.common.interfaces.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public final class LinkedListStack<E> implements Stack<E>, Bag<E> {

    //#region Private Fields
    private LinkedListNode<E> first;
    private int size = 0;
    //#endregion

    //#region Node Class
    private class LinkedListNode<E> {
        private final E item;
        private LinkedListNode<E> next;

        LinkedListNode(final E item) {
            this.item = item;
        }

        //#region Package Private Methods
        E getItem() {
            return item;
        }

        LinkedListNode<E> getNext() {
            return next;
        }

        void setNext(final LinkedListNode<E> next) {
            this.next = next;
        }
        //#endregion
    }
    //#endregion

    //#region Public Methods
    @Override
    public E pop() {
        if (first == null) {
            return null;
        }
        E item = first.getItem();
        first = first.getNext();
        --size;
        return item;
    }

    @Override
    public void push(final E item) {
        LinkedListNode<E> oldFirst = first;
        first = new LinkedListNode<>(item);
        first.setNext(oldFirst);
        ++size;
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
        return first == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void add(final E item) {
        push(item);
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

    //#region Iterable
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private LinkedListNode<E> current = null;

            {
                current = new LinkedListNode<E>(null);
                current.setNext(first);
            }

            @Override
            public boolean hasNext() {
                return (current != null) && (current.getNext() != null);
            }

            @Override
            public E next() {
                if (hasNext()) {
                    current = current.getNext();
                    return current.getItem();
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        for (E item : this) {
            action.accept(item);
        }
    }
    //#endregion
}
