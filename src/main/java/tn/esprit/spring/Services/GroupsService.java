package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.GroupsRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
public class GroupsService implements IGroupsService{

	@Autowired
	GroupsRepository groupRepo;
	@Autowired
	UserRepository userRepo;

	@Override
	@Transactional
	public String CreateGroup(Groups g) {
		groupRepo.save(g);
		String ch="";
		return ch;
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
	public String AddMemberToGroups(Long idGroup,List<Long> IdUser) {
		Groups group = groupRepo.findById(idGroup).get();
		List<Users> users = (List<Users>) userRepo.findAll();
		for(Users user : users ){
			for (Long id : IdUser ){
				if(id.equals(user.getIdUser()))
				user.getUserGroup().add(group);
				userRepo.save(user);
			}
		}
		return group.getGroupUser().toString();
	}

	@Override
	public String RemoveMemberFromGroups(Long idGroup, Long idUser) {
		Groups group = groupRepo.findById(idGroup).get();
		Users user = userRepo.findById(idUser).get();
		// ne9sa logique l quitter
		return "you removed "+user.getNom()+" from the group";
		
	}
	
	@Override
	public String MemberLeaveGroups(Long idGroup,Long idUser){
		Groups group = groupRepo.findById(idGroup).get();
		Users user = userRepo.findById(idUser).get();
		// ne9sa logique l quitter
		return user.getNom()+"leave the group";
	}

	@Override
	public String RenameMember(Long idGroup, Long idUser, String newName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
