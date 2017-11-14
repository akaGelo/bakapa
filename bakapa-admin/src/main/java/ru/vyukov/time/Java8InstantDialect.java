package ru.vyukov.time;

import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * fix https://github.com/thymeleaf/thymeleaf-extras-java8time/issues/17
 */
public class Java8InstantDialect extends AbstractDialect implements IExpressionEnhancingDialect {

    private static final String TEMPORAL_EVALUATION_VARIABLE_NAME = "instant";


    @Override
    public String getPrefix() {
        return "java8time-instant";
    }


    @Override
    public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext processingContext) {
        Map<String, Object> expressionObjects = new HashMap<>();
        Locale locale = processingContext.getContext().getLocale();
        expressionObjects.put(TEMPORAL_EVALUATION_VARIABLE_NAME, new InstantFormatter(locale));
        return expressionObjects;
    }

}
