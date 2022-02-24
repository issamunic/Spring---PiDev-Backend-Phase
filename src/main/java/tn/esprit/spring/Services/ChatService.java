package tn.esprit.spring.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
	
	
	public boolean testMessage(String message){
		String ch="";
		boolean test=false;
		File file = new File("dictionnaire.txt");
		try {
			Scanner scan = new Scanner(file);
			ch=scan.nextLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] ListDictionnaire = ch.split(",");
		String[] TestmessageList=message.split(" ");
		int i=0;
		while( i < TestmessageList.length && test == false ){
			int j=0;
			while(j < ListDictionnaire.length && test == false )
			{
				test=TestmessageList[i].contains(ListDictionnaire[i]); 
			}
			
		}
		return test;
	}
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
