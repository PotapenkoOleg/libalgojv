package com.libalgojv.tries;

import com.libalgojv.common.interfaces.Queue;
import com.libalgojv.common.interfaces.SymbolTable;
import com.libalgojv.queues.linkedlistqueue.LinkedListQueue;

public class TernaryTrie<Value> implements SymbolTable<Value> {
    //#region Private Fields
    private Node root;
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
    }
    //#endregion

    //#region Constructors
    // #endregion

    //#region Public Methods
    @Override
    public void put(String key, Value value) {
        // TODO: balance trre with rotations
        root = put(root, key, value, 0);
    }

    @Override
    public Value get(String key) {
        Node node = get(root, key, 0);
        if (node == null) {
            return null;
        }
        return node.getValue();
    }

    @Override
    public void delete(String key) {
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

    public Iterable<String> getAllKeys() {
        //throw new UnsupportedOperationException();
        Queue<String> queue = new LinkedListQueue<>();
        collect(root, "", queue);
        return queue;
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

    private void collect(Node x, String prefix, Queue<String> q) {
        if (x == null) {
            return;
        }
        if (x.value != null) {
            q.enqueue(prefix);
            int R = 26;
            for (char c = 0; c < R; c++) {
                //collect(x.next[c], prefix + c, q);
            }
        }
    }
    //#endregion
}
