package tn.esprit.spring.Controllers;

import java.awt.TrayIcon.MessageType;

import org.apache.tomcat.websocket.MessageHandlerResultType;
import org.junit.platform.commons.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Chat;

@Component
@Slf4j
public class WebSocketEventListener {
	
	@Autowired
	private SimpMessageSendingOperations sendingOperations;
	@EventListener
	public void handleWebSocketConnectListener(final SessionConnectedEvent event){
		log.info("binbong");
	}
	public void handleWebSocketDisConnectListener(final SessionConnectedEvent event){
		final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		final String username = (String) headerAccessor.getSessionAttributes().get("username");
	
	}
	
}























