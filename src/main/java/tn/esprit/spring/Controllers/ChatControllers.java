package tn.esprit.spring.Controllers;

import org.hibernate.boot.model.IdGeneratorStrategyInterpreter.GeneratorNameDeterminationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.Services.ChatReactService;
import tn.esprit.spring.Services.ChatService;
import tn.esprit.spring.Services.IChatService;
import tn.esprit.spring.entity.Chat;
import tn.esprit.spring.entity.Groups;
import tn.esprit.spring.entity.Users;

@RestController
//@RequestMapping("/chat")
@Slf4j
public class ChatControllers{
	@Autowired
	ChatService cs;
	
	
	@MessageMapping("/chat.send/{groups}")
	public Chat senderMessage(Chat chatMessage,@DestinationVariable Long group){
		log.info(chatMessage.toString());
		return cs.SendMessage(chatMessage, group);
	}
	
	@MessageMapping("/chat.newUser")
	@SendTo("/topic/public")
	public Chat newUser(@Payload final Chat chatMessage,SimpMessageHeaderAccessor headerAccessor){
		log.info(chatMessage.toString());
		headerAccessor.getSessionAttributes().put("username",chatMessage.getMessageUser());
		return chatMessage;
	}
	
}
