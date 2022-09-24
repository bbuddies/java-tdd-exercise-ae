package com.odde.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TenisTest {

    Tenis tenis = new Tenis();

    @Test
    void test_for_0_0() {
        Assertions.assertEquals("Love All", tenis.getScore());
    }

    @Test
    void test_for_15_0() {
        tenis.leftWin();
        Assertions.assertEquals("fifteen Love", tenis.getScore());
    }

    @Test
    void test_for_15_15() {
        tenis.leftWin();
        tenis.rightWin();
        Assertions.assertEquals("fifteen All", tenis.getScore());
    }

    @Test
    void test_for_30_15() {
        tenis.leftWin();
        tenis.leftWin();
        tenis.rightWin();
        Assertions.assertEquals("thirty fifteen", tenis.getScore());
    }
}
