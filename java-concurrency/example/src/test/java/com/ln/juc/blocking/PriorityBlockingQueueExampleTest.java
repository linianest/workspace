package com.ln.juc.blocking;

import org.junit.After;
import org.junit.Before;

import java.util.concurrent.PriorityBlockingQueue;

import static org.junit.Assert.*;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.blocking
 * @Name:PriorityBlockingQueueExampleTest
 * @Author:linianest
 * @CreateTime:2021/1/12 16:27
 * @version:1.0
 * @Description TODO:
 */
public class PriorityBlockingQueueExampleTest {

    private PriorityBlockingQueueExample example;

    @Before
    public void setUp() {
        this.example = new PriorityBlockingQueueExample();
    }

    @After
    public void tearDown(){
        this.example=null;
    }

    public void testAddNewElement(){
        PriorityBlockingQueue queue=new PriorityBlockingQueue();
    }

}