package ru.vyukov.bakapa.validators;

import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CronExpressionValidator implements ConstraintValidator<CronExpression, String> {


    @Override
    public void initialize(CronExpression constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == value) {
            return false;
        }

        try {
            new CronSequenceGenerator(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}