package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.CodeInvitationCompany;
import tn.esprit.spring.entities.Invitation;

public interface ICodeInvitationCompanyService {
	
	public List<CodeInvitationCompany> getAll();
	
	public CodeInvitationCompany add(CodeInvitationCompany CodeInvitationCompany);
	
	public CodeInvitationCompany update(CodeInvitationCompany CodeInvitationCompany);
	
	public void delete(Long CodeInvitationCompanyid);
	
	public CodeInvitationCompany getById(Long CodeInvitationCompanyid);

	public CodeInvitationCompany addFinal(CodeInvitationCompany CodeInvitationCompanyyyy);

	public CodeInvitationCompany getByCode(String code);


}
