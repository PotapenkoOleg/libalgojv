package com.libalgojv.tries;

import com.libalgojv.common.interfaces.SymbolTable;

public class Trie<Value> implements SymbolTable<Value> {
    //#region Private Fields
    //private static final int NUMBER_OF_LETTERS_IN_ENGLISH_ALPHABET = 26;
    private static final int NUMBER_OF_LETTERS_IN_EXTENDED_ASCII = 256;
    private final int numberOfLetters;
    private Node root;// = new Node();
    //#endregion

    //#region Node Class
    private class Node<Value> {
        private Value value;
        private Node[] nextLevel = new Node[numberOfLetters];

        Value getValue() {
            return value;
        }

        void setValue(Value value) {
            this.value = value;
        }

        void setNextLevelAt(int index, Node next) {
            nextLevel[index] = next;
        }

        Node getNextLevelAt(int index) {
            return nextLevel[index];
        }
    }
    //#endregion

    //#region Constructors
    public Trie() {
        this(NUMBER_OF_LETTERS_IN_EXTENDED_ASCII);
    }

    public Trie(int numberOfLetters) {
        this.numberOfLetters = numberOfLetters;
    }
    //#endregion

    //#region Public Methods
    @Override
    public Value get(final String key) {
        Node node = get(root, key, 0);
        if (node == null) {
            return null;
        }
        return (Value) node.getValue();
    }

    @Override
    public void put(final String key, final Value value) {
        if (root == null) {
            root = new Node();
        }
        root = put(root, key, value, 0);
    }

    @Override
    public void delete(final String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException();
    }
    //#endregion

    //#region Private Methods
    private Node get(Node node, String key, int levelCounter) {
        if (node == null) {
            return null;
        }
        if (levelCounter == key.length()) {
            return node;
        }
        char index = key.charAt(levelCounter);
        return get(node.getNextLevelAt(index), key, levelCounter + 1);
    }

    private Node put(Node node, String key, Value value, int levelCounter) {
        if (node == null) {
            node = new Node();
        }
        if (levelCounter == key.length()) {
            node.setValue(value);
            return node;
        }
        char index = key.charAt(levelCounter);
        Node next = put(node.getNextLevelAt(index), key, value, levelCounter + 1);
        node.setNextLevelAt(index, next);
        return node;
    }
    //#endregion
}
