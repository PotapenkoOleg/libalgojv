/*
 * Trie.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tries.trie;

import com.libalgojv.common.dto.KeyValuePair;
import com.libalgojv.common.interfaces.Queue;
import com.libalgojv.common.interfaces.SymbolTable;
import com.libalgojv.queues.linkedlistqueue.LinkedListQueue;

/**
 * Implements R-way trie symbol table data structure
 */
public class Trie<E> implements SymbolTable<E> {
    //#region Private Fields
    private static final int NUMBER_OF_LETTERS_IN_EXTENDED_ASCII = 256;
    private final int numberOfLetters;
    private TrieNode root;
    private int size;
    //#endregion

    //#region Node Class
    private static class TrieNode<Item> {
        private Item value;
        private TrieNode[] nextLevel;

        TrieNode(final int numberOfLetters) {
            nextLevel = new TrieNode[numberOfLetters];
        }

        //region Getters and Setters
        Item getValue() {
            return value;
        }

        void setValue(Item value) {
            this.value = value;
        }

        TrieNode[] getNextLevel() {
            return nextLevel;
        }

        void setNextLevelAt(int index, TrieNode next) {
            nextLevel[index] = next;
        }

        TrieNode getNextLevelAt(int index) {
            return nextLevel[index];
        }
        //endregion
    }
    //#endregion

    //#region Constructors
    public Trie() {
        this(NUMBER_OF_LETTERS_IN_EXTENDED_ASCII);
    }

    public Trie(int numberOfLetters) {
        this.numberOfLetters = numberOfLetters;
        root = new TrieNode(numberOfLetters);
    }
    //#endregion

    //#region Public Methods
    @Override
    public E get(final String key) {
        TrieNode node = get(root, key, 0);
        if (node == null) {
            return null;
        }
        return (E) node.getValue();
    }

    @Override
    public void put(final String key, final E value) {
        root = put(root, key, value, 0);
    }

    @Override
    public void delete(final String key) {
        delete(root, key, 0);
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    @Override
    public void clear() {
        root = new TrieNode(numberOfLetters);
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return isLevelEmpty(root);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Iterable<String> getAllKeys() {
        Queue<String> queue = new LinkedListQueue<>();
        collect(root, "", queue);
        return queue;
    }

    @Override
    public Iterable<String> getKeysWithPrefix(String prefix) {
        TrieNode subTree = get(root, prefix, 0);
        if (subTree == null) {
            return null;
        }
        Queue<String> queue = new LinkedListQueue<>();
        collect(subTree, prefix, queue);
        return queue;
    }

    @Override
    public Iterable<KeyValuePair<String, E>> getKeyValuePairsWithPrefix(String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String longestPrefixOf(String query) {
        int prefixLength = search(root, query, 0, 0);
        return query.substring(0, prefixLength);
    }

    //#endregion

    //#region Private Methods

    private TrieNode get(TrieNode node, String key, int levelCounter) {
        if (node == null) {
            return null;
        }
        if (levelCounter == key.length()) {
            return node;
        }
        char index = key.charAt(levelCounter);
        TrieNode nextLevel = node.getNextLevelAt(index);
        return get(nextLevel, key, levelCounter + 1);
    }

    private TrieNode put(TrieNode node, String key, E value, int levelCounter) {
        if (node == null) {
            node = new TrieNode(numberOfLetters);
        }
        if (levelCounter == key.length()) {
            if (node.getValue() == null) {
                size++;
            }
            node.setValue(value);
            return node;
        }
        char index = key.charAt(levelCounter);
        TrieNode nextLevel = node.getNextLevelAt(index);
        TrieNode next = put(nextLevel, key, value, levelCounter + 1);
        node.setNextLevelAt(index, next);
        return node;
    }

    private boolean delete(TrieNode node, String key, int levelCounter) {
        if (node == null) {
            return false;
        }
        if (levelCounter == key.length()) {
            node.value = null;
            size--;
        } else {
            char index = key.charAt(levelCounter);
            TrieNode nextLevel = node.getNextLevelAt(index);
            boolean isNextLevelEmpty = delete(nextLevel, key, levelCounter + 1);
            if (isNextLevelEmpty) {
                node.setNextLevelAt(index, null);
            }
        }
        return isLevelEmpty(node);
    }

    private boolean isLevelEmpty(TrieNode level) {
        boolean hasElement = false;
        for (TrieNode current : level.getNextLevel()) {
            if (current != null) {
                hasElement = true;
                break;
            }
        }
        return !hasElement;
    }

    private void collect(TrieNode node, String prefix, Queue<String> queue) {
        if (node == null) {
            return;
        }
        if (node.getValue() != null) {
            queue.enqueue(prefix);
        }
        for (char character = 0; character < numberOfLetters; character++) {
            var nextLevel = node.getNextLevel();
            collect(nextLevel[character], prefix + character, queue);
        }
    }

    private int search(TrieNode node, String query, int levelCounter, int prefixLength) {
        if (node == null) {
            return prefixLength;
        }
        if (node.getValue() != null) {
            prefixLength = levelCounter;
        }
        if (levelCounter == query.length()) {
            return prefixLength;
        }
        char character = query.charAt(levelCounter);
        var nextLevel = node.getNextLevel();
        return search(nextLevel[character], query, levelCounter + 1, prefixLength);
    }

    //#endregion
}
