package ru.vyukov.bakapa.contract;

import org.springframework.cloud.contract.spec.internal.ServerDslProperty;


public class ProducerUtils {


    public static ServerDslProperty everyDayCron() {
        return new ServerDslProperty(PatternUtils.cronExpression(), "0 0 0 * * *");
    }
}
