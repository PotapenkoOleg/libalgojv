/*
 * TernaryTrie.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tries.ternarytrie;

import com.libalgojv.common.dto.KeyValuePair;
import com.libalgojv.common.interfaces.Queue;
import com.libalgojv.common.interfaces.SymbolTable;
import com.libalgojv.queues.linkedlistqueue.LinkedListQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements ternary trie symbol table data structure
 */
public class TernaryTrie<E> implements SymbolTable<E> {
    //#region Private Fields
    // TODO: R^2 branching at root
    private TernaryTrieNode root;
    private int size;
    //#endregion

    //#region Node Class
    private static class TernaryTrieNode<Item> {
        private final char character;
        private Item value;
        private TernaryTrieNode left;
        private TernaryTrieNode middle;
        private TernaryTrieNode right;

        TernaryTrieNode(char character) {
            this.character = character;
        }

        //region Getters and Setters
        char getCharacter() {
            return character;
        }

        Item getValue() {
            return value;
        }

        void setValue(Item value) {
            this.value = value;
        }

        TernaryTrieNode getLeft() {
            return left;
        }

        void setLeft(TernaryTrieNode left) {
            this.left = left;
        }

        TernaryTrieNode getMiddle() {
            return middle;
        }

        void setMiddle(TernaryTrieNode middle) {
            this.middle = middle;
        }

        TernaryTrieNode getRight() {
            return right;
        }

        void setRight(TernaryTrieNode right) {
            this.right = right;
        }
        //endregion
    }
    //#endregion

    //#region Public Methods

    /**
     * Inserts new key in symbol table
     *
     * @param key   string key for data retrieval
     * @param value value associated with the key
     */
    @Override
    public void put(String key, E value) {
        // TODO: balance trie with rotations
        root = put(root, key, value, 0);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public E get(String key) {
        TernaryTrieNode node = get(root, key, 0);
        if (node == null) {
            return null;
        }
        return (E) node.getValue();
    }

    /**
     * @param key
     */
    @Override
    public void delete(String key) {
        TernaryTrieNode child = delete(root, key, 0);
        setChildToNull(root, child);
        if (isNodeEmpty(root)) {
            root = null;
        }
    }

    /**
     * @param key
     * @return
     */
    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    /**
     *
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * @return
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    public Iterable<String> getAllKeys() {
        Queue<String> queue = new LinkedListQueue<>();
        collect(root, "", queue);
        return queue;
    }

    public Iterable<String> getKeysWithPrefix(String prefix) {
        TernaryTrieNode subTree = get(root, prefix, 0);
        if (subTree == null) {
            return null;
        }
        Queue<String> queue = new LinkedListQueue<>();
        collect(subTree.getMiddle(), prefix, queue);
        return queue;
    }

    @Override
    public Iterable<KeyValuePair<String, E>> getKeyValuePairsWithPrefix(String prefix) {
        TernaryTrieNode subTree = get(root, prefix, 0);
        if (subTree == null) {
            return null;
        }
        Queue<KeyValuePair<String, E>> list = new LinkedListQueue<>();
        collectKeyValuePairs(subTree.getMiddle(), prefix, list);
        return list;
    }

    public String longestPrefixOf(String query) {
        int prefixLength = search(root, query, 0, Integer.MIN_VALUE);
        if (prefixLength == Integer.MIN_VALUE) {
            // prefix not found
            return null;
        }
        return query.substring(0, prefixLength + 1);
    }

    //#endregion

    //#region Private Methods

    private TernaryTrieNode put(TernaryTrieNode node, String key, E value, int levelCounter) {
        char character = key.charAt(levelCounter);
        if (node == null) {
            node = new TernaryTrieNode(character);
        }
        if (character < node.getCharacter()) {
            node.setLeft(put(node.getLeft(), key, value, levelCounter));
        } else if (character > node.getCharacter()) {
            node.setRight(put(node.getRight(), key, value, levelCounter));
        } else if (levelCounter < key.length() - 1) {
            node.setMiddle(put(node.getMiddle(), key, value, levelCounter + 1));
        } else {
            if (node.getValue() == null) {
                size++;
            }
            node.setValue(value);
        }
        return node;
    }

    private TernaryTrieNode get(TernaryTrieNode node, String key, int levelCounter) {
        if (node == null) {
            return null;
        }
        char character = key.charAt(levelCounter);
        if (character < node.getCharacter()) {
            return get(node.getLeft(), key, levelCounter);
        } else if (character > node.getCharacter()) {
            return get(node.getRight(), key, levelCounter);
        } else if (levelCounter < key.length() - 1) {
            return get(node.getMiddle(), key, levelCounter + 1);
        } else return node;
    }

    private TernaryTrieNode delete(TernaryTrieNode node, String key, int levelCounter) {
        if (node == null) {
            return null;
        }
        char character = key.charAt(levelCounter);
        if (character < node.getCharacter()) {
            TernaryTrieNode childNode = delete(node.getLeft(), key, levelCounter);
            if ((childNode != null) && (isNodeEmpty(childNode))) {
                setChildToNull(node, childNode);
            }
            return node;
        } else if (character > node.getCharacter()) {
            TernaryTrieNode childNode = delete(node.getRight(), key, levelCounter);
            if ((childNode != null) && (isNodeEmpty(childNode))) {
                setChildToNull(node, childNode);
            }
            return node;
        } else if (levelCounter < key.length() - 1) {
            TernaryTrieNode childNode = delete(node.getMiddle(), key, levelCounter + 1);
            if ((childNode != null) && (isNodeEmpty(childNode))) {
                setChildToNull(node, childNode);
            }
            return node;
        } else {
            node.setValue(null);
            size--;
            return node;
        }
    }

    private boolean isNodeEmpty(TernaryTrieNode node) {
        return (node.getValue() == null) && isNodeHaveNoChildren(node);
    }

    private boolean isNodeHaveNoChildren(TernaryTrieNode node) {
        return (node.getLeft() == null) && (node.getMiddle() == null) && (node.getRight() == null);
    }

    private void setChildToNull(TernaryTrieNode parent, TernaryTrieNode child) {
        if (parent == null) {
            return;
        }

        if (parent.getLeft() == child) {
            parent.setLeft(null);
        }
        if (parent.getMiddle() == child) {
            parent.setMiddle(null);
        }
        if (parent.getRight() == child) {
            parent.setRight(null);
        }
    }

    private void collect(TernaryTrieNode node, String prefix, Queue<String> queue) {
        if (node == null) {
            return;
        }
        collect(node.getLeft(), prefix, queue);
        char character = node.getCharacter();
        if (node.getValue() != null) {
            queue.enqueue(prefix + character);
        }
        collect(node.getMiddle(), prefix + character, queue);
        collect(node.getRight(), prefix, queue);
    }

    private void collectKeyValuePairs(TernaryTrieNode node, String prefix, Queue<KeyValuePair<String, E>> queue) {
        if (node == null) {
            return;
        }
        collectKeyValuePairs(node.getLeft(), prefix, queue);
        char character = node.getCharacter();
        E value = (E) node.getValue();
        if (value != null) {
            String key = prefix + character;
            queue.enqueue(new KeyValuePair<>(key, value));
        }
        collectKeyValuePairs(node.getMiddle(), prefix + character, queue);
        collectKeyValuePairs(node.getRight(), prefix, queue);
    }

    private int search(TernaryTrieNode node, String query, int levelCounter, int prefixLength) {
        if (node == null) {
            return prefixLength;
        }
        char character = query.charAt(levelCounter);
        if (character < node.getCharacter()) {
            return search(node.getLeft(), query, levelCounter, prefixLength);
        } else if (character > node.getCharacter()) {
            return search(node.getRight(), query, levelCounter, prefixLength);
        } else {
            if (node.getValue() != null) {
                prefixLength = levelCounter;
            }
            return search(node.getMiddle(), query, levelCounter + 1, prefixLength);
        }
    }
    //#endregion
}
