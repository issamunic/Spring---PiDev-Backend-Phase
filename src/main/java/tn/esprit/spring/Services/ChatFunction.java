package tn.esprit.spring.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Chat;
import tn.esprit.spring.entity.GroupeType;
import tn.esprit.spring.entity.Groups;
import tn.esprit.spring.entity.MessageType;
import tn.esprit.spring.entity.Users;
import tn.esprit.spring.repository.ChatRepository;
import tn.esprit.spring.repository.GroupsRepository;
import tn.esprit.spring.repository.UserRepository;

@Slf4j
public class ChatFunction {
	
	@Autowired
	ChatRepository chatRepo;

	@Autowired
	UserRepository userRepo;
	@Autowired
	GroupsRepository groupRepo;
	
	
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
	

	// function test bad words
	public static String testMessage(String message){
		String ch="";
		int d=0;
		String s3="";
		File file = new File("dictionnaire.txt");
		try {
			Scanner scan = new Scanner(file);
			ch=scan.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String[] ListDictionnaire = ch.split(",");
		
		String newMsg=message;
		
		// test 
		while( d < ListDictionnaire.length )
		{
			s3="";
		    if(newMsg.contains(ListDictionnaire[d]))
		    {
		        for(int c=0; c<ListDictionnaire[d].length() ; c++){
		            s3+="*";
		        }
		    }
		    if(newMsg.contains(ListDictionnaire[d]))
		    	newMsg=newMsg.replaceAll("\\s", "").replaceAll(ListDictionnaire[d],s3);
		    d+=1;
		}
		int i=0;
		// output
        while(newMsg.length() < message.length())
        {
        	s3="";
            if(message.charAt(i) ==' ')
                {
                    s3=newMsg.substring(0,i)+" "+newMsg.substring(i,newMsg.length());
                    newMsg=s3;
                    
                }
            i+=1;
        }
		
		return newMsg;
	}

	public static MessageType MessageType(String newMessage) {
		MessageType typeOfMessage =MessageType.text;
		boolean link=false; boolean text=false; boolean test=false;
		
		String[] listMessage = newMessage.split(" ");
		
		int i=0;
		while (i < listMessage.length && !test)
		{  
		    if(listMessage[i].matches("(http://|https://)*[A-Za-z0-9]*.[A-Za-z0-9]*.(fr|com|tn|gov|us)$"))
		    
		    link=true;
		    else 
		    text=true;
		    test=link && text;
			i+=1;
		}
		
		if( link && text)
			typeOfMessage=typeOfMessage.linkAndText;
		else if( link )
			typeOfMessage=typeOfMessage.link;
		
		return typeOfMessage;
	}
	
}
