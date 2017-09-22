package ru.vyukov.bakapa.agent.ws;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
