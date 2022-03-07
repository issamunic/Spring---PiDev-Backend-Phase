package tn.esprit.spring.springbatch.csv;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.esprit.spring.serviceInterface.IInvitationService;
import tn.esprit.spring.entities.csv;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.fileupload.*;

@Component
public class DBWriter implements ItemWriter<csv>{

	@Autowired
	private csvRepository csvRepository;
	
	@Autowired
	IInvitationService IInvitationService;
	
	@Autowired 
	FilesStorageService FilesStorageService;
	
	
    @Autowired
    public DBWriter (csvRepository csvRepository) {
        this.csvRepository = csvRepository;
    }
	
    User user;
    
    
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public void write(List<? extends csv> items) throws Exception {
		System.out.println("Data Saved for items" + items);
		for (csv item : items) {
			item.setIdsender("sender");
			Invitation invitation = new Invitation();
			invitation.setMailEmployee(item.getEmail());
			invitation.setUserSender(getUser());
			IInvitationService.add(invitation);
			
		}		
	FilesStorageService.deleteFile("csv.csv");
	}
	
	

}
