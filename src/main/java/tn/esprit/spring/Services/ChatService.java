package tn.esprit.spring.Services;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.ChatRepository;
import tn.esprit.spring.repository.GroupsRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
@Slf4j
public class ChatService implements IChatService{

	private static Long idSession =(long) 2;
	@Autowired
	ChatRepository chatRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	GroupsRepository groupRepo;
	@Autowired 
	GroupsService groupService;

	ChatFunction chatFunction;
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	
	public void sendAudio(Long to){
		Chat chatMessage = new Chat();
		Groups group=groupRepo.findById(to).get();
		Users user = userRepo.findById(idSession).get();
		
		long currentTimestamp = System.currentTimeMillis();
		Date dateMessage  = new Date((currentTimestamp));	
		
		chatMessage.setDateMsg(dateMessage);
		chatMessage.setChatGroup(group);
		chatMessage.setMessageUser(user);
		chatMessage.setMessageType(MessageType.audio);
		
		try
		{
			AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,44100,16,2,4,44100,false);
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
			if(!AudioSystem.isLineSupported(info))
			{
				log.info("change format");
			}
			
			TargetDataLine targetLine = (TargetDataLine)AudioSystem.getLine(info);
			targetLine.open();
			
			log.info("Start recording");
			targetLine.start();
			
			Thread thread = new Thread()
			{
				@Override 
				public void run(){
					AudioInputStream audioStream = new AudioInputStream(targetLine);
					File audioFile = new File(currentTimestamp+".wav");
					try {
						AudioSystem.write(audioStream,AudioFileFormat.Type.WAVE, audioFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					log.info("Stop recording");
					Path vocalPAth = Paths.get(uploadDirectory,audioFile.getName());
										
					chatMessage.setMessage(audioFile.toString());
					chatRepo.save(chatMessage);
				}
				
			};
			thread.start();
			Thread.sleep(10000);
			targetLine.stop();
			targetLine.close();	
			}
		catch(LineUnavailableException lue){
			lue.printStackTrace();
		}
		catch(InterruptedException ie){
			ie.printStackTrace();
		}
		
	}
		
	@Override
	public Chat SendMessage(Chat chatMessage ,Long to ) 
	{
		Groups group=groupRepo.findById(to).get();
		Users user = userRepo.findById(idSession).get();
		
		long currentTimestamp = System.currentTimeMillis();
		Date dateMessage  = new Date((currentTimestamp));		
		chatMessage.setDateMsg(dateMessage);
		
		String newMessage=chatFunction.testMessage(chatMessage.getMessage());
		chatMessage.setMessage(newMessage);
		
		
		chatMessage.setChatGroup(group);
		chatMessage.setMessageUser(user);
		
		MessageType messageType=chatFunction.MessageType(newMessage);
		chatMessage.setMessageType(messageType);
		
		if(group.getDureeExpiration()!=null)
			chatMessage.setDureeExpiration(group.getDureeExpiration());
		
		return chatRepo.save(chatMessage);
		
	}
	
	@Override
	public Chat readVocal(Long idMessage) 
	{
		Chat vocal = chatRepo.findById(idMessage).get();
		String path ="C:/Users/Lenovo/Desktop/4sae8/spring/290921/PiEsprit/"+vocal.getMessage();
		sound(path);
		return null;
		
		
	}
	
	
	@Override
	public void DeleteMessage(Long idMessage) 
	{
		chatRepo.deleteById(idMessage);
	}
	
	public List<Chat> getAllMessage()
	{
		List<Chat> chat =  chatRepo.findAll();
		return chat;
	}
	
	@Override
	public List<Chat> getMessageByGroup(Long idGroup) 
	{
		Groups group = groupRepo.findById(idGroup).orElse(null);
		Users user = userRepo.findById(idSession).get();
		List<Chat> ListChat = chatRepo.getChatByGroup(group);
		
		if(ListChat.size()>0 ){
			if( group.getGroupUser().contains(user))
			{
				etatMessage( ListChat, user);
			    UpdateDateExpiration(ListChat,group);
			}
			
			return ListChat;
		}
		return null;
	}
	
	@Override
	public Map<MessageType, List<Chat>> CountTypeOfMessage(Long idGroup)
	{
		Groups group = groupRepo.findById(idGroup).orElse(null);
		List<Chat> ListChat = chatRepo.getChatByGroup(group);
		Map<MessageType,List<Chat>> chatByType =
				new HashMap<>();
		chatByType = ListChat.stream()
				.collect(Collectors.groupingBy(Chat::getMessageType));
	
		return chatByType;
	}
			
	
	
	@Scheduled(fixedRate=5000)
	public void ChatScheduler()
	{
		List<Chat> chatEexpirationDate = chatRepo.chatEexpirationDate();
		
		for(Chat chat : chatEexpirationDate )
		{
			log.info("le message "+chat.getMessage()+" a été supprimé le "+chat.getExpirationdate());
			chatRepo.delete(chat);
		}
	}
	
	
	// function for update Date expiration
		public void UpdateDateExpiration(List<Chat> ListChat , Groups group){
			for(Chat chat : ListChat){
				if(chat.getEtat()!=null){
					if( chat.getEtat().size()==group.getGroupUser().size() && 
							group.getGroupeSecuritytype().equals(GroupeType.secure) && 
							chat.getDureeExpiration()!=null ){
						Date expirationdate = dateExpiration(chat.getDureeExpiration());
						chat.setExpirationdate(expirationdate);
						chatRepo.save(chat);
					}
				}
			}
		}
		
		// function to return the new date 
		public Date dateExpiration(Long second){
			long currentTimestamp = System.currentTimeMillis();
			currentTimestamp=currentTimestamp/1000;
			Long expirationdate = (currentTimestamp+second)*1000;
			Date expirationDate  = new Date((expirationdate));
			return expirationDate;
		}
		
		// function for etat Message => SEEN message 
		public  void etatMessage(List<Chat> ListChat, Users user){
			for(Chat chat : ListChat){
				chat.getEtat().add(user);
				chatRepo.save(chat); 
			}			
		}
		
		
		void sound(String path) {
		    File lol = new File(path);
		    try {
		        Clip clip = AudioSystem.getClip();
		        clip.open(AudioSystem.getAudioInputStream(lol));
		        clip.start();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
	


}
