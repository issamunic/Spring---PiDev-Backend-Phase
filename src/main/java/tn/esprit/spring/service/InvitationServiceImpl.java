package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.StatusInvitation;
import tn.esprit.spring.repository.InvitationRepository;

import tn.esprit.spring.serviceInterface.IInvitationService;

@Service
@Slf4j
public class InvitationServiceImpl  implements IInvitationService{

	@Autowired
	InvitationRepository InvitationRepo;
	
	@Override
	public List<Invitation> getAll() {
		try {
			List<Invitation> invitations = (List<Invitation>) InvitationRepo.findAll();
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
			invitation.setStatusInvitation(StatusInvitation.pending);
			invitation.setDateAcceptInvitation(new Date());
			return InvitationRepo.save(invitation);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public Invitation update(Invitation invitation) {
		try {
			InvitationRepo.save(invitation);
			return invitation;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void delete(Long Projectid) {
		try {
			InvitationRepo.deleteById(Projectid);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}

	@Override
	public Invitation getById(Long Invitationid) {
		try {
			Invitation invitation = InvitationRepo.findById(Invitationid).orElse(null);
			return invitation;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	@Override
	public Invitation SetinvitationAccepted(Long Invitationid) {
		try {
			getById(Invitationid).setStatusInvitation(StatusInvitation.accpeted);
			getById(Invitationid).setDateAcceptInvitation(new Date());
			InvitationRepo.save(getById(Invitationid));
			return getById(Invitationid) ;
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}
	

}
