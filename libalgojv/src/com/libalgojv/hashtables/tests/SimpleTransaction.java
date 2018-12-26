/*
 * SimpleTransaction.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.hashtables.tests;

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
        // TODO: pass M in constructor
        int M = 100; // hash = int between 0 and M-1;
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: implement equals
        return super.equals(obj);
    }
}
