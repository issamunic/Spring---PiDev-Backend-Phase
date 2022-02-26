package tn.esprit.spring.Controllers;

import java.util.List;

import org.hibernate.boot.model.IdGeneratorStrategyInterpreter.GeneratorNameDeterminationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.Services.ChatReactService;
import tn.esprit.spring.Services.ChatService;
import tn.esprit.spring.Services.IChatService;
import tn.esprit.spring.entity.Chat;
import tn.esprit.spring.entity.ChatReact;
import tn.esprit.spring.entity.Groups;
import tn.esprit.spring.entity.Users;

@RestController
@RequestMapping("/chat")
@Slf4j
public class ChatControllers{
	@Autowired
	ChatService chatService;
	
	/*
	@MessageMapping("/chat.send/{groups}")
	public Chat senderMessage(Chat chatMessage,@DestinationVariable Long group){
		log.info(chatMessage.toString());
		return chatService.SendMessage(chatMessage, group);
	}
	
	@MessageMapping("/chat.newUser")
	@SendTo("/topic/public")
	public Chat newUser(@Payload final Chat chatMessage,SimpMessageHeaderAccessor headerAccessor){
		log.info(chatMessage.toString());
		headerAccessor.getSessionAttributes().put("username",chatMessage.getMessageUser());
		return chatMessage;
	}*/
	
	
	@PostMapping("/{to}")
	public void SendMessage(@RequestBody Chat chatMessage,@PathVariable("to") Long to) {
		chatService.SendMessage(chatMessage, to);
		 
	}
	
	@GetMapping("/getAllMessage")
	@ResponseBody
	public List<Chat> getAllMessage() {
		return chatService.getAllMessage();
	}
	
	@PostMapping("/getMessageByGroup/{idGroup}")
	public List<Chat> getMessageByGroup(@PathVariable("idGroup") Long idGroup){
		return chatService.getMessageByGroup(idGroup);
	}	
	
	@DeleteMapping("message/{idMessage}")
	public void DeleteMessage(@PathVariable("idMessage")Long idMessage) {
		chatService.DeleteMessage(idMessage);
		 
	}
	
}
