package com.libalgojv.unionfind;

public class QuickUnion {
    private final int[] id;
    private final int size;

    public QuickUnion(int size) {
        this.size = size;
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

    public int getSize() {
        return size;
    }
}
