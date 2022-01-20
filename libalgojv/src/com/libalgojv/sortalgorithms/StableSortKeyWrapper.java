package com.libalgojv.sortalgorithms;

public final class StableSortKeyWrapper<E extends Comparable<E>> implements Comparable<E> {
    private final E key;
    private final Integer sequenceNumber;

    public StableSortKeyWrapper(E key, int sequenceNumber) {
        this.key = key;
        this.sequenceNumber = sequenceNumber;
    }

    public E getKey() {
        return key;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public int compareTo(E o) {
        if (!(o instanceof StableSortKeyWrapper)) {
            throw new IllegalArgumentException();
        }
        StableSortKeyWrapper<E> other = (StableSortKeyWrapper<E>) o;
        if (this.getKey().compareTo(other.getKey()) < 0) {
            return -1;
        }
        if (this.getKey().compareTo(other.getKey()) > 0) {
            return 1;
        }
        if (this.getKey().compareTo(other.getKey()) == 0) {
            return this.getSequenceNumber().compareTo(other.getSequenceNumber());
        }
        throw new IllegalArgumentException();
    }
}






