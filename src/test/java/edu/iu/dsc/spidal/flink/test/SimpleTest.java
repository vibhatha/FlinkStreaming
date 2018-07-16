package edu.iu.dsc.spidal.flink.test;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @Before
    public void justAnExample() {
        System.out.println("This test method should be run");
    }

    @After
    public void anotherTest() {
        System.out.println("This is another test");
    }

    @Test
    public void initialTest() {
        System.out.println("Intial Test ...");
    }
}
