/*
 * BinaryTreeTests.java
 * Project libalgojv
 *
 * Created by Oleg Potapenko on 12/25/18 12:00 PM.
 * Copyright © 2018-2019 Oleg Potapenko. All rights reserved.
 */

package com.libalgojv.tests.unit.trees.binarytree;

import com.libalgojv.trees.binarytree.BinaryTree;
import com.libalgojv.common.interfaces.BinarySearchTree;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTests {

    private BinarySearchTree<Integer, Integer> binaryTree;

    @BeforeEach
    void setUp() {
        binaryTree = new BinaryTree<>();
        //SEXACRHM
    }

    @AfterEach
    void tearDown() {
        binaryTree = null;
    }

    @Test
    void add() {
        binaryTree.add(42, -42);
        binaryTree.add(40, -40);
        binaryTree.add(41, -41);
        binaryTree.add(39, -39);
        binaryTree.add(44, -44);
        binaryTree.add(43, -43);
        binaryTree.add(45, -45);

        //Integer actual = binarySearchTree.get(43);
        //actual = binarySearchTree.get(47);
        binaryTree.inorder(System.out::println);
        fail("Not Implemented");
    }

    @Test
    void get() {
        fail("Not Implemented");
    }

    @Test
    void remove() {
        fail("Not Implemented");
    }

    @Test
    void getMin() {
        binaryTree.add(42, -42);
        binaryTree.add(40, -40);
        binaryTree.add(41, -41);
        binaryTree.add(39, -39);
        binaryTree.add(44, -44);
        binaryTree.add(43, -43);
        binaryTree.add(45, -45);

        Integer expected = 39;
        Integer actual = binaryTree.getMin();
        assertEquals(expected, actual);
    }

    @Test
    void getMax() {
        binaryTree.add(42, -42);
        binaryTree.add(40, -40);
        binaryTree.add(41, -41);
        binaryTree.add(39, -39);
        binaryTree.add(44, -44);
        binaryTree.add(43, -43);
        binaryTree.add(45, -45);

        Integer expected = 45;
        Integer actual = binaryTree.getMax();
        assertEquals(expected, actual);
    }

    @Test
    void getFloor() {
        fail("Not Implemented");
    }

    @Test
    void getCeiling() {
        fail("Not Implemented");
    }

    @Test
    void getRank() {
        fail("Not Implemented");
    }

    @Test
    void preorder() {
        fail("Not Implemented");
    }

    @Test
    void inorder() {
        fail("Not Implemented");
    }

    @Test
    void postorder() {
        fail("Not Implemented");
    }

    @Test
    void clear() {
        fail("Not Implemented");
    }

    @Test
    void isEmpty() {
        fail("Not Implemented");
    }

    @Test
    void getSize() {
        fail("Not Implemented");
    }

    @Test
    void toStringTest() {
        fail("Not Implemented");
    }

    @Test
    void iterator() {
        fail("Not Implemented");
    }

    @Test
    void forEach() {
        fail("Not Implemented");
    }
}