package org.example;

import org.junit.Test;

import static java.lang.Thread.sleep;

public class CounterTest
{

    @Test
    public void testCounter() throws InterruptedException {
        Counter ct = new Counter(5);
        ct.putTest(3,10000);

        System.out.println(ct.count(3));
    }
}
