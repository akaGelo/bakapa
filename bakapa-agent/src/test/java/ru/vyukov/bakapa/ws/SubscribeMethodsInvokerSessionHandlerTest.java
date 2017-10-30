package ru.vyukov.bakapa.ws;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

@Ignore
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
