package com.example.admin.dayone;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void test_add() {
        int expected = 35;
        int actual = Calculator.add(10,20);
        assertEquals(expected,actual);
    }
}