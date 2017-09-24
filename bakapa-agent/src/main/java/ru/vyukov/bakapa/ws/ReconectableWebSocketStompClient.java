package ru.vyukov.bakapa.ws;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

/**
 * После первого речного коннекта будет проверять наличие подключение и переподключаться 
 * @author gelo
 *
 */
public class ReconectableWebSocketStompClient extends WebSocketStompClient {

	private volatile ScheduledFuture<?> reconnectSchedule;

	/**
	 * True если соединение установлено руками. Нужно что-бы не переподключится
	 * раньше первого соединения
	 */
	private volatile boolean manualConnection;

	private volatile URI url;
	private volatile WebSocketHttpHeaders handshakeHeaders;
	private volatile StompHeaders connectHeaders;
	private volatile StompSessionHandler sessionHandler;

	private volatile ListenableFuture<StompSession> connect;

	public ReconectableWebSocketStompClient(WebSocketClient webSocketClient, TaskScheduler taskScheduler,
			long reconectDelay) {
		super(webSocketClient);
		setTaskScheduler(taskScheduler);
		reconnectSchedule = taskScheduler.scheduleWithFixedDelay(() -> {
			if (!manualConnection) {
				//skip reconnect before manual connection 
				return;
			}
			if (connect.isDone()) {
				try {
					StompSession stompSession = connect.get();
					if (!stompSession.isConnected()) {
						throw new ExecutionException("Connection closed",null);
					}
				} catch (InterruptedException e) {
					;
				} catch (ExecutionException e) {
					this.connect(url, handshakeHeaders, connectHeaders, sessionHandler);
				}
			}
			
		}, reconectDelay);
	}

	@Override
	public ListenableFuture<StompSession> connect(URI url, WebSocketHttpHeaders handshakeHeaders,
			StompHeaders connectHeaders, StompSessionHandler sessionHandler) {
		//save params for reconect 
		this.url = url;
		this.handshakeHeaders = handshakeHeaders;
		this.connectHeaders = connectHeaders;
		this.sessionHandler = sessionHandler;
		this.connect = super.connect(url, handshakeHeaders, connectHeaders, sessionHandler);
		//first manual connect
		this.manualConnection = true;
		return connect;
	}

	@Override
	public void start() {
		super.start();

	}

	@Override
	public void stop() {
		if (null != reconnectSchedule) {
			reconnectSchedule.cancel(false);
		}
		super.stop();
	}

}
