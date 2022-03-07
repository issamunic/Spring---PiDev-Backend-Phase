package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Service.ChatReactService;
import tn.esprit.spring.Service.ChatService;
import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.ChatReact;
import tn.esprit.spring.entities.Groups;
import tn.esprit.spring.entities.React;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.serviceInterface.IChatService;

@RestController
@RequestMapping("/chat")
public class ChatReactControllers{
	@Autowired
	ChatReactService crs;
	
	@PostMapping("/react")
	public void ReactToMessage(@RequestBody ChatReact typeReact) {
		 crs.ReactToMessage(typeReact);
		 
	}
	
	@DeleteMapping("/react/{idReact}")
	public void deleteReact(@PathVariable("idReact") Long idReact) {
		crs.deleteReact(idReact);
		
	}
	
	@PutMapping("/react/{idReact}/{typeReact}")
	public void updateReact(@PathVariable("idReact")Long idReact,@PathVariable("typeReact")React typeReact) {
		crs.updateReact(idReact,typeReact);
	}
	
}
