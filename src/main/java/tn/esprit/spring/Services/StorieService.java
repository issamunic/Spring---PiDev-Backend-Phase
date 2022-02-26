package tn.esprit.spring.Services;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.ChatRepository;
import tn.esprit.spring.repository.GroupsRepository;
import tn.esprit.spring.repository.StoriesRepository;
import tn.esprit.spring.repository.UserRepository;

@Slf4j
@Service
public class StorieService implements IStorieService{

	@Autowired
	GroupsRepository groupRepo;
	@Autowired
	ChatRepository chatRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	StoriesRepository storieRepo;
	
	public Long idSession = (long) 1;

	Users userSession = userRepo.findById(idSession).get();
	 
	@Override
	public Stories CreateStorie(Stories storie) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateStorie= new Date();
		storie.setEtatStorie(EtatStories.posted);
		storie.setDateStories(dateStorie);
		return storieRepo.save(storie);
	}
	@Override
	public List<Stories> getStorieByUser(Long idUser) {
		Users userSession = userRepo.findById(idSession).get();
		Users user= userRepo.findById(idUser).get();
		List<Stories> storie = storieRepo.getStorieByUser(user,userSession);
		for(Stories story : storie){
			if(story.getUserStorie()!=userSession){
				story.getViewsStroie().add(userSession);
				storieRepo.save(story);
			}
		}
		
		return storie;
	}
	@Override
	public List<Stories> getFollowersStorie() {
		List<Stories> stories = storieRepo.getFollowersStorie(userSession);
		for(Stories story : stories){
			if(story.getUserStorie()!=userSession){
				story.getViewsStroie().add(userSession);
				storieRepo.save(story);
			}
		}
		return stories;
		
	}
	@Override
	public void BanUserToShowStorie(Stories storie, Long idUser) {
		Stories story = storieRepo.findById(storie.getIdStories()).get();
		Users user = userRepo.findById(idUser).get();
		story.getExceptStroie().add(user);
		storieRepo.save(story);
				
	}
	@Override
	public void unbannedUserToShowStorie(Stories storie, Long idUser) {
		Stories story = storieRepo.findById(storie.getIdStories()).get();
		Users user = userRepo.findById(idUser).get();
		story.getExceptStroie().remove(user);
		storieRepo.save(story);
				
	}
	
	public void updateEtatStorie(Stories storie , Long idUser){
		Stories story = storieRepo.findById(storie.getIdStories()).get();
		Users user = userRepo.findById(idUser).get();
		story.getViewsStroie().add(user);
		storieRepo.save(story);
	}
	
	public void deleteSoty(Stories storie){
		if(storie.getUserStorie().getIdUser() == idSession)
			storieRepo.deleteById(storie.getIdStories());
	}
	@Override
	public List<Stories> getMyArchiveStories() {
		
		return storieRepo.getMyArchiveStories(idSession);
	
	}
	
	public void viewStorie(Stories storie){
		Users user = userRepo.findById(idSession).get();
		if(storie.getUserStorie()!=user)
			storie.getViewsStroie().add(user);
		storieRepo.save(storie);
	}
	
	public void repeatToStorie(Stories storie, String message){
		Chat chat= new Chat();
		// select get group where id user = and id session 
		//chat.setChatGroup(group);
		chat.setMessage(message);
		chat.setMessageUser(userSession);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateMessage = new Date();
		chat.setDateMsg(dateMessage);
		chat.setMessageType(MessageType.storie);
		chatRepo.save(chat);
		}
	
	//twali update f blast select w traja3 boolean w f chat delete 
	@Scheduled(fixedRate=5000)
	public void testScheduler(){
		List<Stories> stories = storieRepo.ScheduledDeleteStories(EtatStories.posted);
		for(Stories storie : stories ){
			storie.setEtatStorie(EtatStories.archived);
			storieRepo.save(storie);
			if(storie.getEtatStorie()==EtatStories.archived){
				log.info("votre storie a été archiver");
			}
		}
		
		
	}
	


}
