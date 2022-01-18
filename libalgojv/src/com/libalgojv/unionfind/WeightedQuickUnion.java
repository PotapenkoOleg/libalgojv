package com.libalgojv.unionfind;

public class WeightedQuickUnion {
    private final int[] id;
    private final int[] treeSize;
    private final int size;

    public WeightedQuickUnion(int size) {
        this.size = size;
        id = new int[size];
        treeSize = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
            treeSize[i] = 1;
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
        if (treeSize[i] < treeSize[i]) {
            id[i] = j;
            treeSize[j] += treeSize[i];
        } else {
            id[j] = i;
            treeSize[i] += treeSize[j];
        }
    }

    public int getSize() {
        return size;
    }
}