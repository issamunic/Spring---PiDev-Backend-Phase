package tn.esprit.spring.Controllers;

import java.util.List;
import java.util.Map;

import org.hibernate.boot.model.IdGeneratorStrategyInterpreter.GeneratorNameDeterminationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.Services.ChatReactService;
import tn.esprit.spring.Services.ChatService;
import tn.esprit.spring.Services.IChatService;
import tn.esprit.spring.entity.Chat;
import tn.esprit.spring.entity.ChatReact;
import tn.esprit.spring.entity.Groups;
import tn.esprit.spring.entity.MessageType;
import tn.esprit.spring.entity.User;

@RestController
@RequestMapping("/chat")
@Slf4j
public class ChatControllers{
	@Autowired
	ChatService chatService;	
	
	@PostMapping("/{to}")
	public void SendMessage(@RequestBody Chat chatMessage,@PathVariable("to") Long to) {
		chatService.SendMessage(chatMessage, to);
		 
	}
	@PostMapping("voice/{to}")
	public void sendAudio(@PathVariable("to") Long to){
		chatService.sendAudio(to);
	}
	
	@GetMapping("/getAllMessage")
	@ResponseBody
	public List<Chat> getAllMessage() {
		return chatService.getAllMessage();
	}
	
	@PutMapping("/getMessageByGroup/{idGroup}")
	public List<Chat> getMessageByGroup(@PathVariable("idGroup") Long idGroup){
		return chatService.getMessageByGroup(idGroup);
	}	
	
	@DeleteMapping("message/{idMessage}")
	public void DeleteMessage(@PathVariable("idMessage")Long idMessage) {
		chatService.DeleteMessage(idMessage);
		 
	}
	@GetMapping("/readVocal/{idMessage}")
	public Chat readVocal(@PathVariable("idMessage") Long idMessage) {
		return chatService.readVocal(idMessage);
	}
	
	@GetMapping("/CountTypeOfMessage/{idGroup}")
	public Map<MessageType, List<Chat>> CountTypeOfMessage(@PathVariable("idGroup") Long idGroup) {
		return chatService.CountTypeOfMessage(idGroup);
	}
	
}
