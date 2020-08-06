package ru.kuryakin;

import com.sun.istack.internal.NotNull;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<T>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }


    public boolean remove(T t) {
        Node<T> focusNode = root;
        Node<T> parent = root;

        boolean isItALeftChild = true;

        while (focusNode.value != t){
            parent = focusNode;

            if (t.compareTo(focusNode.value) < 0){
                isItALeftChild = true;
                focusNode = focusNode.left;
            } else {
                isItALeftChild = false;
                focusNode = focusNode.right;
            }

            if (focusNode == null)
                return false;
        }

        if(focusNode.left == null && focusNode.right == null){
            if (focusNode == root){
                root = null;
            } else if (isItALeftChild){
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (focusNode.right == null){
            if (focusNode == root)
                root = focusNode.left;
            else if (isItALeftChild)
                parent.left = focusNode.left;
            else
                parent.right = focusNode.left;

        } else if (focusNode.left == null){
            if (focusNode == root)
                root = focusNode.right;
            else if (isItALeftChild)
                parent.left = focusNode.right;
            else
                parent.right = focusNode.left;
        } else {
            Node<T> replacement = getReplacementNode(focusNode);

            if (focusNode == root)
                root = replacement;
            else if (isItALeftChild)
                parent.left = replacement;
            else
                parent.right = replacement;
            replacement.left = focusNode.left;
        }


        size--;
        return true;
    }

    public Node<T> getReplacementNode(Node<T> replacedNode){
        Node<T> replacementParent = replacedNode;
        Node<T> replacement = replacedNode;

        Node<T> focusNode = replacedNode.right;

        while (focusNode != null){
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.left;
        }

        if (replacement != replacedNode.right){
            replacementParent.left = replacement.right;
            replacement.right = replacedNode.right;
        }

        return replacement;


    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {}

        private Node<T> findNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        public void remove() {

        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }
}
