package ru.kuryakin;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {

    @Test
    public void add() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(10);
        tree.add(5);
        tree.add(7);
        tree.add(10);
        assertEquals(3, tree.size());
        assertTrue(tree.contains(5));
        tree.add(3);
        tree.add(1);
        tree.add(3);
        tree.add(4);
        assertEquals(6, tree.size());
        assertFalse(tree.contains(8));
        tree.add(8);
        tree.add(15);
        tree.add(15);
        tree.add(20);
        assertEquals(9, tree.size());
        assertTrue(tree.contains(8));
        assertTrue(tree.checkInvariant());
    }

    @Test
    public void del(){
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(10);
        tree.add(5);
        tree.add(7);
        tree.add(8);

        tree.remove(7);

        assertEquals(3, tree.size());
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(5));
        assertFalse(tree.contains(7));
        assertTrue(tree.checkInvariant());
    }

    @Test
    public void del2(){
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(10);
        tree.add(5);
        tree.add(7);
        tree.add(4);
        tree.add(8);
        tree.add(6);

        tree.remove(7);
        tree.remove(10);
        assertEquals(4, tree.size());
    }

    @Test
    public void del3(){
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(100);
        tree.add(50);
        tree.add(78);
        tree.add(46);
        tree.add(125);
        tree.add(15);
        tree.add(101);


        assertTrue(tree.remove(100));
        assertFalse(tree.contains(7));
        assertTrue(tree.remove(46));

        assertEquals(5, tree.size());

    }

    @Test
    public void del4() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(100);
        tree.add(50);
        tree.add(78);
        tree.add(46);
        tree.add(125);
        tree.add(15);
        tree.add(101);

        assertTrue(tree.remove(100));
        assertFalse(tree.contains(7));
        assertEquals(6, tree.size());

        tree.add(98);
        tree.add(56);
        tree.add(128);
        tree.add(12);
        tree.add(67);
        tree.add(96);
        tree.add(76);

        assertFalse(tree.remove(100));
        assertFalse(tree.contains(7));
        assertEquals(13, tree.size());

        tree.add(137);
        tree.add(168);
        tree.add(37);
        tree.add(142);
        tree.add(101);
        tree.add(164);
        tree.add(76);

        assertTrue(tree.remove(37));
        assertTrue(tree.remove(76));
        assertTrue(tree.remove(101));
        assertEquals(15, tree.size());
    }


}
