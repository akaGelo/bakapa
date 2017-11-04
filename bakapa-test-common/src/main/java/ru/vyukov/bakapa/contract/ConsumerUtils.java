package ru.vyukov.bakapa.contract;

import org.springframework.cloud.contract.spec.internal.ClientDslProperty;

public class ConsumerUtils {


    public static ClientDslProperty everyDayCron() {
        return new ClientDslProperty(PatternUtils.cronExpression(), "0 0 0 * * *");
    }
}