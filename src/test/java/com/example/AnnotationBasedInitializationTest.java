package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AnnotationBasedInitializationTest {

    @Mock
    CalculatorService mockCalculatorService;
    Calculator calculator;
    AutoCloseable closeable;

    @BeforeEach
    void setup() {
        // Initialize annotated mocks
        closeable = MockitoAnnotations.openMocks(this);
        calculator = new Calculator(mockCalculatorService);
    }

    @AfterEach
    void teardown() throws Exception {
        closeable.close();
    }

    @Test
    void testAnnotationBasedInitialization() {
        // Stub the behavior of the mock
        when(mockCalculatorService.add(10, 20)).thenReturn(30);
        // Verify the result using the real method
        int result = calculator.add(10, 20);
        assertEquals(30, result);

    }

    private static class Calculator {

        public static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

        private final CalculatorService calculatorService;

        public Calculator(CalculatorService calculatorService) {
            this.calculatorService = calculatorService;
        }

        int add(int a, int b) {
            LOGGER.trace("starting addition of a={} b={})", a, b);
            int sum = calculatorService.add(a, b);
            LOGGER.trace("completed addition of a={} b={})", a, b);
            return sum;
        }
    }

    private interface CalculatorService {
        int add(int a, int b);
    }

}