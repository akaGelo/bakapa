package ru.vyukov.bakapa.ws;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class StomSubsribeAnnotationBeanPostProcessor implements BeanPostProcessor, Ordered {

	private Map<String, SubscribeMethodInstance> subscribeMethods = new HashMap<>();

	@Override
	public int getOrder() {
		return LOWEST_PRECEDENCE;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean.getClass().isAnnotationPresent(StomReceiver.class)) {
			// on @StemReceiver beans
			Method[] declaredMethods = bean.getClass().getDeclaredMethods();
			for (Method method : declaredMethods) {
				if (method.isAnnotationPresent(Subscribe.class)) {
					// on @Subscribe methods
					Class<?>[] parameterTypes = method.getParameterTypes();
					if (1 != parameterTypes.length) {
						throw new IllegalArgumentException("@Subscibe method must have one parameter");
					}
					String path = method.getAnnotation(Subscribe.class).value();
					SubscribeMethodInstance subscribeMethodInstance = new SubscribeMethodInstance(path, method, bean,
							parameterTypes[0]);
					SubscribeMethodInstance prev = null;
					if (null != (prev = subscribeMethods.put(path, subscribeMethodInstance))) {
						throw new IllegalStateException("Path [" + path + "] already used  " + prev.getMethod()
								+ " and " + subscribeMethodInstance.getMethod());
					}
				}
			}
		}
		// ------------------------------

		if (bean instanceof SubscribeMethodsInvokerSessionHandler) {
			((SubscribeMethodsInvokerSessionHandler) bean).setSubscribeMethodInstances(subscribeMethods);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
