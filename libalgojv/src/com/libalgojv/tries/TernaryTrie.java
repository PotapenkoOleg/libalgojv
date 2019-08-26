/*
 * TernaryTrie.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tries;

import com.libalgojv.common.interfaces.Queue;
import com.libalgojv.common.interfaces.SymbolTable;
import com.libalgojv.queues.linkedlistqueue.LinkedListQueue;

/**
 * Implements TernaryTrie symbol table data structure
 */
public class TernaryTrie<Value> implements SymbolTable<Value> {
    //#region Private Fields
    // TODO: R^2 branching at root
    private Node root;
    private int size;
    //#endregion

    //#region Node Class
    private class Node {
        private final char character;
        private Value value;
        private Node left;
        private Node middle;
        private Node right;

        Node(char character) {
            this.character = character;
        }

        //region Getters and Setters
        char getCharacter() {
            return character;
        }

        Value getValue() {
            return value;
        }

        void setValue(Value value) {
            this.value = value;
        }

        Node getLeft() {
            return left;
        }

        void setLeft(Node left) {
            this.left = left;
        }

        Node getMiddle() {
            return middle;
        }

        void setMiddle(Node middle) {
            this.middle = middle;
        }

        Node getRight() {
            return right;
        }

        void setRight(Node right) {
            this.right = right;
        }
        //endregion
    }
    //#endregion

    //#region Constructors
    // #endregion

    //#region Public Methods

    /**
     * Inserts new key in symbol table
     *
     * @param key   string key for data retrieval
     * @param value value associated with the key
     */
    @Override
    public void put(String key, Value value) {
        // TODO: balance trie with rotations
        root = put(root, key, value, 0);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public Value get(String key) {
        Node node = get(root, key, 0);
        if (node == null) {
            return null;
        }
        return node.getValue();
    }

    /**
     * @param key
     */
    @Override
    public void delete(String key) {
        Node child = delete(root, key, 0);
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

    private Node put(Node node, String key, Value value, int levelCounter) {
        char character = key.charAt(levelCounter);
        if (node == null) {
            node = new Node(character);
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

    private Node get(Node node, String key, int levelCounter) {
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

    private Node delete(Node node, String key, int levelCounter) {
        if (node == null) {
            return null;
        }
        char character = key.charAt(levelCounter);
        if (character < node.getCharacter()) {
            Node childNode = delete(node.getLeft(), key, levelCounter);
            if ((childNode != null) && (isNodeEmpty(childNode))) {
                setChildToNull(node, childNode);
            }
            return node;
        } else if (character > node.getCharacter()) {
            Node childNode = delete(node.getRight(), key, levelCounter);
            if ((childNode != null) && (isNodeEmpty(childNode))) {
                setChildToNull(node, childNode);
            }
            return node;
        } else if (levelCounter < key.length() - 1) {
            Node childNode = delete(node.getMiddle(), key, levelCounter + 1);
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

    private boolean isNodeEmpty(Node node) {
        return (node.getValue() == null) && isNodeHaveNoChildren(node);
    }

    private boolean isNodeHaveNoChildren(Node node) {
        return (node.getLeft() == null) && (node.getMiddle() == null) && (node.getRight() == null);
    }

    private void setChildToNull(Node parent, Node child) {
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

//    private void collect(Node x, String prefix, Queue<String> q) {
//        if (x == null) {
//            return;
//        }
//        if (x.getValue() != null) {
//            q.enqueue(prefix);
//            int R = 26;
//            for (char c = 0; c < R; c++) {
//                //collect(x.next[c], prefix + c, q);
//            }
//        }
//    }

    //#endregion

}
