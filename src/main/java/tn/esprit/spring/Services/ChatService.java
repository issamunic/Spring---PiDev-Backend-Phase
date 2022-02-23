package tn.esprit.spring.Services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.ChatRepository;
import tn.esprit.spring.repository.GroupsRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
@Slf4j
public class ChatService implements IChatService{

	@Autowired
	ChatRepository chatRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	GroupsRepository groupRepo;
	@Autowired 
	GroupsService groupService;
	
	@Override
	public Chat SendMessage(Chat chatMessage ,Long to) {
		Groups group=groupRepo.findById(to).get();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateMessage = new Date();
		chatMessage.setDateMsg(dateMessage);
		chatMessage.setChatGroup(group);
		return chatRepo.save(chatMessage);
	}
	@Override
	public void DeleteMessage(Long idMessage) {
		chatRepo.deleteById(idMessage);
		
	}
	public List<Chat> getAllMessage(){
		Iterable<Chat> chat =  chatRepo.findAll();
		return (List<Chat>) chat;
	}
	
	@Override
	public List<Chat> getMessageByGroup(Long idGroup) {
		Groups group = groupRepo.findById(idGroup).orElse(null);
		return chatRepo.getChatByGroup(group);
	}


}
