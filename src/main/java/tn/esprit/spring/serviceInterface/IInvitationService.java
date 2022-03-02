package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.entities.User;


public interface IInvitationService {
	
	public List<Invitation> getAll();
	
	public Invitation add(Invitation invitation);
	
	public Invitation update(Invitation invitation);
	
	public void delete(Long Projectid);
	
	public Invitation getById(Long Invitationid);

	Invitation SetinvitationAccepted(Long Invitationid);

	List<Invitation> getByCompany(User Company);

}
