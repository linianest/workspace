package com.ln.juc.custom;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.collections
 * @Name:SimpleSkipList
 * @Author:linianest
 * @CreateTime:2021/1/11 16:54
 * @version:1.0
 * @Description TODO: 跳表数据结构
 */

import java.util.Random;

/**
 * 跳表的数据结构特点：
 * 1、一种随机的数据结构
 * 2、最底层包含整个跳表的所有结构
 * 3、典型的空间换时间的算法
 */
public class SimpleSkipList {
    private final static byte HEAD_BIT = (byte) 1;
    private final static byte DATA_BIT = (byte) 0;
    private final static byte TAIL_BIT = (byte) -1;

    private static class Node {
        private Integer value;
        private Node up, down, left, right;
        // 数据类型
        private byte bit;

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value) {
            this(value, DATA_BIT);
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private int height;
    private Random random;

    public SimpleSkipList() {
        this.head = new Node(null, HEAD_BIT);
        this.tail = new Node(null, TAIL_BIT);
        head.right = tail;
        tail.left = head;
        this.random = new Random(System.currentTimeMillis());
    }

    public void dumpSkipList() {
        Node temp = head;
        int i = height + 1;
        while (temp != null) {
            System.out.printf("Total [%d] height [%d]", height + 1, i--);
            Node node = temp.right;
            while (node.bit == DATA_BIT) {
                System.out.printf("->%d ", node.value);
                node = node.right;
            }
            System.out.printf("\n");
            temp = temp.down;
        }

        System.out.println("==========================");
    }

    public boolean contains(Integer element) {
        Node node = this.find(element);
        return (node.value == element);
    }

    public Integer get(Integer element) {
        Node node = this.find(element);
        return (node.value == element) ? node.value : null;
    }

    private Node find(Integer element) {
        Node current = head;
        for (; ; ) {
            while (current.right.bit != TAIL_BIT && current.right.value <= element) {
                current = current.right;
            }
            if (current.down != null) {
                current = current.down;
            } else {
                break;
            }
        }
        return current; //the current<=element< current.right(if exist)
    }

    public void add(Integer element) {
        Node nearNode = this.find(element);
        Node newNode = new Node(element);
        newNode.left = nearNode;
        newNode.right = nearNode.right;
        nearNode.right.left = newNode;
        nearNode.right = newNode;

        int currentLevel = 0;
        while (random.nextDouble() < 0.5d) {
            if (currentLevel >= height) {
                height++;

                Node dumyHead = new Node(null, HEAD_BIT);
                Node dumyTail = new Node(null, TAIL_BIT);

                dumyHead.right = dumyTail;
                dumyHead.down = head;
                head.up = dumyHead;

                dumyTail.left = dumyHead;
                dumyTail.down = tail;
                tail.up = dumyTail;

                head = dumyHead;
                tail = dumyTail;
            }

            while ((nearNode != null) && nearNode.up == null) {
                nearNode = nearNode.left;
            }
            nearNode = nearNode.up;

            Node upNode = new Node(element);
            upNode.left = nearNode;
            upNode.right = nearNode.right;
            upNode.down = newNode;

            nearNode.right.left = upNode;
            nearNode.right = upNode;

            newNode.up = upNode;
            newNode = upNode;
            currentLevel++;
        }
        size++;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SimpleSkipList skipList = new SimpleSkipList();
        skipList.add(10);
        skipList.dumpSkipList();
        skipList.add(1);
        skipList.dumpSkipList();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            skipList.add(random.nextInt(1000));
        }
        skipList.dumpSkipList();
    }
}
