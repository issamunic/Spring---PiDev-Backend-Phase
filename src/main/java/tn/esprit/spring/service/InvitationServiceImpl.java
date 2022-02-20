package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.repository.InvitationRepository;

import tn.esprit.spring.serviceInterface.IInvitationService;

@Service
@Slf4j
public class InvitationServiceImpl  implements IInvitationService{

	@Autowired
	InvitationRepository InvitatioRepo;
	
	@Override
	public List<Invitation> getAll() {
		try {
			List<Invitation> invitations = (List<Invitation>) InvitatioRepo.findAll();
			for (Invitation invitation : invitations) {
				log.info(" invitation : " + invitation);
			}
			return invitations;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Invitation add(Invitation invitation) {
		try {
			return InvitatioRepo.save(invitation);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public Invitation update(Invitation invitation) {
		try {
			InvitatioRepo.save(invitation);
			return invitation;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void delete(Long Projectid) {
		try {
			InvitatioRepo.deleteById(Projectid);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}

	@Override
	public Invitation getById(Long Invitationid) {
		try {
			Invitation invitation = InvitatioRepo.findById(Invitationid).orElse(null);
			return invitation;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	

}
