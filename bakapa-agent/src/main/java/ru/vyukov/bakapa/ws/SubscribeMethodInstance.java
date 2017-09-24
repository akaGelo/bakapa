package ru.vyukov.bakapa.ws;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.Value;

/**
 * Link to bean reveive method
 * 
 * @author gelo
 *
 */
@Value
public class SubscribeMethodInstance {

	private String destination;

	private Method method;

	private Object beanInstance;

	private Class<?> argType;

	public void invoke(Object arg) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		method.invoke(beanInstance, arg);
	}

}
