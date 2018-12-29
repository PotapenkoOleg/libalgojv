/*
 * SimpleTransaction.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.hashtables;

import java.util.Date;

class SimpleTransaction {
    private String who;
    private Date when;
    private double amount;

    public SimpleTransaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();
        return hash;
    }

    private int normalizeHash(Integer/*Your type here*/ key) {
        // hash = int between 0 and NUMBER_OF_ELEMENTS_IN_HASH-1;
        int NUMBER_OF_ELEMENTS_IN_HASH = 100;
        return (key.hashCode() & 0x7fffffff) % NUMBER_OF_ELEMENTS_IN_HASH;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SimpleTransaction)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.hashCode() == obj.hashCode();
    }
}
