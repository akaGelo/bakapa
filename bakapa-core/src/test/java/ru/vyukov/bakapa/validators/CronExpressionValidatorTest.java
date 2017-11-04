package ru.vyukov.bakapa.validators;

import org.junit.Test;
import org.mockito.Mock;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.*;

public class CronExpressionValidatorTest {

    private final CronExpressionValidator cronExpressionValidator = new CronExpressionValidator();

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Test
    public void isValid() throws Exception {
        assertFalse(isTest("test"));
        assertFalse(isTest("* * * * *"));

        assertTrue(isTest("0 * * * * MON-FRI"));

        assertTrue(isTest("0 * * * * *"));
        assertTrue(isTest("* * * * * *"));

        assertTrue(isTest("*/10 * * * * *"));

        assertTrue(isTest("0 0 0 * * *"));


    }

    private boolean isTest(String expression) {
        return cronExpressionValidator.isValid(expression, constraintValidatorContext);
    }

}
