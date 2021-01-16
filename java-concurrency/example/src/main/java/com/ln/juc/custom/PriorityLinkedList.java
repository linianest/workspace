package com.ln.juc.custom;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.collections
 * @Name:PriorityPriorityLinkedList
 * @Author:linianest
 * @CreateTime:2021/1/11 14:50
 * @version:1.0
 * @Description TODO:PriorityLinkedList 单向有序的linkedlist
 */
public class PriorityLinkedList<E extends Comparable> {
    private PriorityLinkedList.Node<E> first;
    private final PriorityLinkedList.Node<E> NULL = (PriorityLinkedList.Node<E>) null;
    private static final String PLAIN_NULL = "null";

    private int size;

    public PriorityLinkedList() {
        this.first = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public static <E extends Comparable> PriorityLinkedList<E> of(E... elements) {
        final PriorityLinkedList<E> list = new PriorityLinkedList<>();
        if (elements.length != 0) {
            for (E element : elements) {
                list.addFirst(element);
            }
        }
        return list;
    }

    public PriorityLinkedList<E> addFirst(E e) {
        final PriorityLinkedList.Node<E> node = new PriorityLinkedList.Node<>(e);
        Node<E> previous = NULL;
        Node<E> current = first;
        while (current != null && e.compareTo(current.value) > 0) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            first = node;

        } else {
            previous.next = node;
        }
        node.next = current;
        size++;
        return this;
    }

    public boolean contains(E e) {
        PriorityLinkedList.Node<E> current = first;
        while (current != null) {
            if (current.value == e) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    static class NoElementExamption extends RuntimeException {
        public NoElementExamption(String message) {
            super(message);
        }
    }

    public E removeFirst() {
        /**
         * also return the NULL always when the PriorityLinkedList is empty.
         */
        if (this.isEmpty()) {
            throw new PriorityLinkedList.NoElementExamption("The linked list is empty.");
        }
        PriorityLinkedList.Node<E> node = first;
        first = node.next;
        size--;
        return node.value;
    }

    private static class Node<E> {
        E value;
        PriorityLinkedList.Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (null != value)
                return value.toString();
            return PLAIN_NULL;
        }
    }

    public static void main(String[] args) {
        final PriorityLinkedList<Integer> list = PriorityLinkedList.of(-10,3,-4,29,14,32,8);
        System.out.println("============");
        System.out.println(list.toString());
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        } else {
            final StringBuilder builder = new StringBuilder("[");
            PriorityLinkedList.Node<E> current = first;
            while (current != null) {
                builder.append(current.toString())
                        .append(",");
                current = current.next;
            }

            builder.replace(builder.length() - 1, builder.length(), "]");
            return builder.toString();
        }
    }
}
