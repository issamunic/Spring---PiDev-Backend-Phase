package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.CodeInvitationCompany;
import tn.esprit.spring.entities.HistorySubcriptionCompany;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.StatusInvitation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.mail.mailsender.ISimpleEmailExampleController;
import tn.esprit.spring.repository.InvitationRepository;
import tn.esprit.spring.twillo.*;
import tn.esprit.spring.serviceInterface.IInvitationService;

@Service
@Slf4j
public class InvitationServiceImpl  implements IInvitationService{

	@Autowired
	InvitationRepository InvitationRepo;
	@Autowired
	ISimpleEmailExampleController SimpleEmailExampleController;
	@Autowired
	SmsSender SmsSender;
	
	
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
			invitation.setDateCreationInvitation(new Date());
			SimpleEmailExampleController.sendSimpleEmail(invitation.getMailEmployee(),
					"invitation to Vinder From "
			+invitation.getUserSender().getNameCompany()
					," \n Your code is : "
			+invitation.getUserSender().getCodeInvitationCompany().getCodeInvitation());
			
			SmsRequest Sms = new SmsRequest(invitation.getNumber(),"invitation to Vinder From "+invitation.getUserSender().getNameCompany()+"Your code is : "+invitation.getUserSender().getCodeInvitationCompany().getCodeInvitation());	
			SmsSender.sendSms(Sms);
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
	
	@Override
	public List<Invitation> getByCompany(User Company) {
		try {
			List<Invitation> Invitationfinal  = new ArrayList();			
			List<Invitation> Invitations = (List<Invitation>) InvitationRepo.findAll();
			for (Invitation Invitationfor : Invitations) {
				if(Company.getIdUser().equals(Invitationfor.getUserSender().getIdUser())) {
					Invitationfinal.add(Invitationfor);
				}
			}
			return Invitationfinal;
			
	}
		catch (Exception e) {
			System.out.println(e);
		return null;
	}
	}
	
	
	@Override
	public Invitation getByEmailAndUser(String email,User comapny) {
		List<Invitation> Invitations = (List<Invitation>) InvitationRepo.findAll();
		for (Invitation invitation : Invitations) {
			if(invitation.getMailEmployee().equals(email) && invitation.getUserSender().getIdUser().equals(comapny.getIdUser())){
				return invitation;
			}
		}
		
		return null;
	}
	

}
