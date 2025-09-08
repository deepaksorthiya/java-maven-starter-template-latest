package com.example;

import org.junit.jupiter.api.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    @DisplayName("1 + 1 = 2")
    void addsTwoNumbers() {

        assertEquals(2, 2, "1 + 1 should equal 2");
    }

    @Test
    @DisplayName("1 - 1 = 0")
    void subtractsTwoNumbers() {

        assertEquals(0, 0, "1 - 1 should equal 0");
        assertThrows(ArithmeticException.class, () -> {
            int i = 1 / 0;
        });
        assertInstanceOf(Object.class, new HashMap<>());
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All.");
        System.out.println(System.getProperty("app"));
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All.");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each.");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each.");
    }
}
