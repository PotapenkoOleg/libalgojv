package com.libalgojv.tries;

import com.libalgojv.common.interfaces.SymbolTable;

public class Trie<Value> implements SymbolTable<Value> {
    //#region Private Fields
    private static final int NUMBER_OF_LETTERS_IN_EXTENDED_ASCII = 256;
    private final int numberOfLetters;
    private Node root;
    private int size;
    //#endregion

    //#region Node Class
    private class Node<Value> {
        private Value value;
        private Node[] nextLevel;

        Node(final int numberOfLetters) {
            nextLevel = new Node[numberOfLetters];
        }

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
        root = new Node(numberOfLetters);
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
        root = new Node(numberOfLetters);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<String> getKeysWithPrefix(String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] wildcardMatch(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String longestPrefixOf(String prefix) {
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
        Node nextLevel = node.getNextLevelAt(index);
        return get(nextLevel, key, levelCounter + 1);
    }

    private Node put(Node node, String key, Value value, int levelCounter) {
        if (node == null) {
            node = new Node(numberOfLetters);
        }
        if (levelCounter == key.length()) {
            if (node.getValue() == null) {
                size++;
            }
            node.setValue(value);
            return node;
        }
        char index = key.charAt(levelCounter);
        Node nextLevel = node.getNextLevelAt(index);
        Node next = put(nextLevel, key, value, levelCounter + 1);
        node.setNextLevelAt(index, next);
        return node;
    }

    private boolean delete(Node node, String key, int levelCounter) {
        if (node == null) {
            return false;
        }
        if (levelCounter == key.length()) {
            node.value = null;
            size--;
        } else {
            char index = key.charAt(levelCounter);
            Node nextLevel = node.getNextLevelAt(index);
            boolean isNextLevelEmpty = delete(nextLevel, key, levelCounter + 1);
            if (isNextLevelEmpty) {
                node.setNextLevelAt(index, null);
            }
        }
        return isLevelEmpty(node);
    }

    private boolean isLevelEmpty(Node level) {
        boolean hasElement = false;
        for (Node current : level.nextLevel) {
            if (current != null) {
                hasElement = true;
                break;
            }
        }
        return !hasElement;
    }
    //#endregion
}
