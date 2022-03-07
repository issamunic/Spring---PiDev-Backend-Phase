package tn.esprit.spring.Services;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	
	private static Long idSession =(long) 1;
	 
	@Override
	public Stories CreateStorie(Stories storie) {
		Date dateStorie= new Date();
		storie.setDateStories(dateStorie);
		storie.setEtatStorie(EtatStories.posted);		
		return storieRepo.save(storie);
	}
	@Override
	public List<Stories> getStorieByUser(Long idUser) {
		Stories story = null;
		Users userSession = userRepo.findById(idSession).get();
		List<Stories> stories = storieRepo.getStorieByUser(idUser);
		stories =FiltreStoryByIdSession(stories , userSession);
		return stories;
	}
	
	
	
	@Override
	public Map<Users, List<Stories>> getFollowersStorie() {
		Users userSession = userRepo.findById(idSession).get();
		List<Stories> stories = storieRepo.getFollowersStorie();
		stories =FiltreStoryByIdSession(stories , userSession);
		Map<Users,List<Stories>> storiesnByPerson =
				new HashMap<>();
		storiesnByPerson = stories.stream()
				.collect(Collectors.groupingBy(Stories::getUserStorie));
		
		
		return storiesnByPerson;
	}
	
	
	@Override
	public void BanUserToShowStorie(Long idStorie, Long idUser) {
		Stories story = storieRepo.findById(idStorie).get();
		Users user = userRepo.findById(idUser).get();
		story.getExceptStroie().add(user);
		storieRepo.save(story);
				
	}
	
	@Override
	public void unbannedUserToShowStorie(Long idStorie, Long idUser) {
		Stories story = storieRepo.findById(idStorie).get();
		Users user = userRepo.findById(idUser).get();
		story.getExceptStroie().remove(user);
		storieRepo.save(story);
				
	}	
	
	@Override
	public void deleteSoty(Long idStorie){
		Stories storie = storieRepo.findById(idStorie).get();
		if(storie.getUserStorie().getIdUser() == idSession)
			storieRepo.deleteById(idStorie);
	}
	
	@Override
	public List<Stories> getMyArchiveStories() {
		EtatStories etatStories = EtatStories.archived;
		Users user = userRepo.findById(idSession).orElse(null);
		
		return storieRepo.getMyArchiveStories(user,etatStories);	
	}
	
	
	@Override
	public List<Users> BannerUsersList (Long idStorie){
		//return userRepo.getBannerUsersList(idStorie);
		return null;
	}
	
	@Override
	public void repeatToStorie(Long idGroup,Chat chat){
		Users userSession = userRepo.findById(idSession).get();
		Groups group = groupRepo.findById(idGroup).get();
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateMessage = new Date();
		chat.setDateMsg(dateMessage);
		
		chat.setChatGroup(group);
		chat.setMessage(chat.getMessage());
		chat.setMessageUser(userSession); 		
		
		chat.setMessageType(MessageType.storie);
		
		chatRepo.save(chat);
		}
	
	//twali update f blast select w traja3 boolean w f chat delete 
	@Scheduled(fixedRate=5000)
	public void testScheduler(){
		List<Stories> stories = storieRepo.ScheduledSetEtatStories(EtatStories.posted);
		for(Stories storie : stories ){
			storie.setEtatStorie(EtatStories.archived);
			storieRepo.save(storie);
			if(storie.getEtatStorie()==EtatStories.archived){
				log.info("votre storie a été archiver");
			}
		}
		
		
	}
	
	
	// filtrage pour éliminer l'affichage si l'utilisateur de la session et banner par le créateur 
		public List<Stories> FiltreStoryByIdSession (List<Stories> stories,Users userSession){
			Stories story=null;
			if(stories != null){
				for(Stories storie : stories ){
					Long idStorie=storie.getIdStories();
					List<Users> users = storieRepo.getExceptByUser(idStorie);
					if(users.contains(userSession)){
						log.info("banned"+users);
						story=storie;
					}
				}
				stories.remove(story);
			}
			return stories;
		}
		
		
		public void viewStorie(Stories storie, Users userSession){
			if(storie.getUserStorie()!=userSession)
			{
				storie.getViewsStroie().add(userSession);
			}
			storieRepo.save(storie);
		}
		
		
		public boolean  FiltreStoryByIdSession(Stories stories ,Users userSession){
			if(stories.getVisibility()==VisibilityType.followers){
				//
			}
			return true;
		}

	

}
