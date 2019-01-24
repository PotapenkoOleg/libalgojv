/*
 * HashTableLinearProbingTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.hashtables;

public class SimpleTransactionKey {
    private int hashValue;

    public SimpleTransactionKey(int hashValue) {
        this.hashValue = hashValue;
    }

    @Override
    public String toString() {
        return Integer.toString(hashValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SimpleTransactionKey)) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return hashValue;
    }
}
