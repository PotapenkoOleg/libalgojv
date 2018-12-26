/*
 * LinkedListQueue.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.queues.linkedlistqueue;

import com.libalgojv.common.interfaces.Bag;
import com.libalgojv.common.interfaces.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.function.Consumer;

public class LinkedListQueue<E> implements Queue<E>, Bag<E> {

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
    public void enqueue(E item) {
        LinkedListNode<E> oldFirst = first;
        first = new LinkedListNode<>(item);
        first.setNext(oldFirst);
        ++size;
    }

    @Override
    public E dequeue() {
        E result;
        if (first == null) {
            return null;
        }
        if (first.getNext() == null) {
            result = first.getItem();
            first = null;
            --size;
            return result;
        }
        LinkedListNode<E> previous = first;
        LinkedListNode<E> current = first.getNext();
        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }
        result = current.getItem();
        previous.setNext(null);
        --size;
        return result;
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
    public void add(E item) {
        enqueue(item);
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
            private Stack<E> stack = new Stack<>();

            {
                LinkedListNode<E> current = first;
                while (current != null) {
                    stack.push(current.getItem());
                    current = current.getNext();
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.empty();
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return stack.pop();
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
    //endregion
}