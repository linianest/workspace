package com.ln.juc.custom;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.custom
 * @Name:MyQueue
 * @Author:linianest
 * @CreateTime:2021/1/13 14:48
 * @version:1.0
 * @Description TODO: 实现一个非线程安全的FIFO的链表队列
 */

/**
 * un-thread-safe
 * @param <E>
 */
public class MyQueue<E> {

    private static class Node<E> {

        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return (element == null) ? "" : element.toString();
        }
    }

    private Node<E> first, last;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E peekFirst() {

        return (first == null) ? null : first.getElement();
    }

    public E peekLast() {
        return (first == null) ? null : last.getElement();
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (size == 0) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    /**
     * 实现FIFO
     *
     * @return
     */
    public E removeFirst() {
        if (isEmpty()) return null;
        E answer = first.getElement();
        first = first.getNext();
        size--;
        if (size == 0) last = null;
        return answer;
    }

    public static void main(String[] args) {
        MyQueue<Object> queue = new MyQueue<>();
        queue.addLast("Hello");
        queue.addLast("World");
        queue.addLast("Java");

        System.out.println(queue.removeFirst()); // Hello
        System.out.println(queue.removeFirst());//World
        System.out.println(queue.removeFirst());//Java

    }

}
