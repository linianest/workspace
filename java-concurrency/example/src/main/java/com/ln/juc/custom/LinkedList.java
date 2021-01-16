package com.ln.juc.custom;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.collections
 * @Name:LinkedList
 * @Author:linianest
 * @CreateTime:2021/1/11 14:08
 * @version:1.0
 * @Description TODO:LinkedList:单向linkedlist
 */
public class LinkedList<E> {

    private Node<E> first;
    private final Node<E> NULL = (Node<E>) null;
    private static final String PLAIN_NULL = "null";

    private int size;

    public LinkedList() {
        this.first = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public static <E> LinkedList<E> of(E... elements) {
        final LinkedList<E> list = new LinkedList<>();
        if (elements.length != 0) {
            for (E element : elements) {
                list.addFirst(element);
            }
        }
        return list;
    }

    public LinkedList<E> addFirst(E e) {
        final Node<E> node = new Node<>(e);

        node.next = first;
        this.size++;
        this.first = node;
        return this;
    }

    public boolean contains(E e) {
        Node<E> current = first;
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
         * also return the NULL always when the linkedlist is empty.
         */
        if (this.isEmpty()) {
            throw new NoElementExamption("The linked list is empty.");
        }
        Node<E> node = first;
        first = node.next;
        size--;
        return node.value;
    }

    private static class Node<E> {
        E value;
        Node<E> next;

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
        final LinkedList<String> list = LinkedList.of("Hello", "world", "java", "scala","Thread");
        list.addFirst("Concurrency").addFirst("Test");
        System.out.println(list.size);
        System.out.println(list.contains("scala"));
        System.out.println("============");
        System.out.println(list.toString());
        while (!list.isEmpty()){
            System.out.println(list.removeFirst());
        }
        System.out.println(list.size);
        System.out.println(list.isEmpty());
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        } else {
            final StringBuilder builder = new StringBuilder("[");
            Node<E> current = first;
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
