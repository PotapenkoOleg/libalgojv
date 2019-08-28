/*
 * TernaryTrie.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tries.ternarytrie;

import com.libalgojv.common.interfaces.SymbolTable;

/**
 * Implements TernaryTrie symbol table data structure
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
        return (E)node.getValue();
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
        throw new UnsupportedOperationException();
//        Queue<String> queue = new LinkedListQueue<>();
//        collect(root, "", queue);
//        return queue;
    }

    public Iterable<String> getKeysWithPrefix(String prefix) {
        // sh -> ["she", "shells", "shore"]
        throw new UnsupportedOperationException();
    }

    public String[] wildcardMatch(String key) {
        // ".he" -> ["she", "the"]
        throw new UnsupportedOperationException();
    }

    public String longestPrefixOf(String prefix) {
        // shellsort -> "shells"
        throw new UnsupportedOperationException();
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

    //#endregion
}
