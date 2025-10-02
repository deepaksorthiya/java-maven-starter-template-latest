package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MockitoJUnit5ExtensionTest {
    @Mock
    CalculatorService calculatorService;

    @InjectMocks
    Calculator calculator;

    @Test
    void testMockitoJUnit5Extension() {
        // Define mock behavior
        when(calculatorService.add(3, 7)).thenReturn(10);
        // Verify the result
        int result = calculator.add(3, 7);
        assertEquals(10, result);
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