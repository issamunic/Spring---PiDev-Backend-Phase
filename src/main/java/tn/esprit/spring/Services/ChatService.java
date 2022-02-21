package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.ChatRepository;
import tn.esprit.spring.repository.GroupsRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
public class ChatService implements IChatService{

	@Autowired
	ChatRepository cr;
	@Autowired
	UserRepository ur;
	@Autowired
	GroupsRepository gr;
	
	@Override
	public Chat SendMessage(Chat chatMessage ,Long to) {
		Users user=ur.findById(to).get();
		chatMessage.setMessageUser(user);
		return cr.save(chatMessage);
	}
	@Override
	public void DeleteMessage(Long idMessage) {
		cr.deleteById(idMessage);
		
	}
	@Override
	public List<Chat> getMessage(Long to,Long idUser) {
	
		return null;
	}


}
