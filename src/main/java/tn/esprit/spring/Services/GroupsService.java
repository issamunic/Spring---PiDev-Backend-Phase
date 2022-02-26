package tn.esprit.spring.Services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.GroupsRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
@Slf4j
public class GroupsService implements IGroupsService{
	

	@Autowired
	GroupsRepository groupRepo;
	@Autowired
	UserRepository userRepo;

	@Override
	@Transactional
	public void CreateGroup(Groups groups, List<Long> idUser) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateCreation= new Date();
		groups.setDateGroupe(dateCreation);
		groupRepo.save(groups);
		
	}
		
	
	
	@Override
	public void deleteGroup(Long idGroup) {
		groupRepo.deleteById(idGroup);
	}


	@Override
	public void UpadateEtatGroup(Long idGroup, GroupeType etatGroup) {
		Groups group = groupRepo.findById(idGroup).get();
		group.setGroupeSecuritytype(etatGroup);
		groupRepo.save(group);	
	}
	
	@Override
	public void ChangeThemeGroup(Long idGroup, themes theme) {
		Groups group = groupRepo.findById(idGroup).get();
		group.setTheme(theme);
		groupRepo.save(group);	
	}

	@Override
	public String RenameGroups(Long idGroup, String Newname) {
		Groups group = groupRepo.findById(idGroup).get();
		group.setGroupeName(Newname);
		groupRepo.save(group);		
		return group.getGroupeName();
	}

	@Override
	public String ChangeImageGroups(Long idGroup, String image) {
		Groups group = groupRepo.findById(idGroup).get();
		group.setImageGroup(image);
		groupRepo.save(group);		
		return "l'image du groupe a été modifier";
	}
	
	@Override
	public Groups getByIdGroup(Long idGroup) {
		return groupRepo.findById(idGroup).orElse(null);
		
	}

	@Override
	public List<Groups> retrieveAllGroup() {
		return (List<Groups>) groupRepo.findAll();
		
	}
	
	
	

	@Override
	public String AddMemberToGroups(Long idGroup,List<Long> IdUser) {
		Groups group = groupRepo.findById(idGroup).get();
		List<Users> users = (List<Users>) userRepo.findAll();
		for(Users user : users ){
			for (Long id : IdUser ){
				if(id.equals(user.getIdUser()))
				group.getGroupUser().add(user);
				groupRepo.save(group);
			}
		}
		return group.getGroupUser().toString();
	}

	@Override
	public String RemoveMemberFromGroups(Long idGroup, Long idUser) {
		Groups group = groupRepo.findById(idGroup).orElse(null);
		Users user = userRepo.findById(idUser).orElse(null);
		if(group.getGroupUser() != null){
			
		int u =  group.getGroupUser().size();
		String us =  user.toString();
		group.getGroupUser().remove(user);
		groupRepo.save(group);
			log.info("nmbr: "+u);
			log.info("nmbr: "+us);
		}
		return user.getNom()+" "+user.getPrenom()+" was removed from the group from "+group.getGroupeName()+".";
		
	}
	

	@Override
	public String RenameMember(Long idGroup, Long idUser, String newName) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
