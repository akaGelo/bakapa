package ru.vyukov.bakapa.controller.config.utils;

import java.lang.annotation.*;

/**
 * Current authorized AuthenticationAgent
 *
 * @author Oleg Vyukov
 */
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthenticationAgent {
}
