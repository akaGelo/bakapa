package ru.vyukov.bakapa.ws;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ru.vyukov.bakapa.ws.SubscribeMethodInstance;
import ru.vyukov.bakapa.ws.SubscribeMethodsInvokerSessionHandler;

@RunWith(MockitoJUnitRunner.class)
public class SubscribeMethodsInvokerSessionHandlerTest {
	@Mock
	private Map<String, SubscribeMethodInstance> subscribeMethodInstances;
	@InjectMocks
	private SubscribeMethodsInvokerSessionHandler subscribeMethodsInvokerSessionHandler;
	
	
	@Test
	public void testGetPayloadType() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
	@Test
	public void testHandleFrame() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
