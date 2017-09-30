package ru.vyukov.bakapa.ws;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark method as message receiver. The method must have one argument.
 * 
 * @author gelo
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

	/**
	 * Subsribe path
	 * 
	 * @return
	 */
	String value();

}