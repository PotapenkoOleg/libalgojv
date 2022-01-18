package com.libalgojv.unionfind;

public class QuickFind {
    private final int[] id;
    private final int size;

    public QuickFind(int size) {
        this.size = size;
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    public boolean isConnected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < size; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public int getSize() {
        return size;
    }
}
