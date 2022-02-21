package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.CodeInvitationCompany;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.repository.CodeInvitationCompanyRepository;
import tn.esprit.spring.repository.InvitationRepository;
import tn.esprit.spring.serviceInterface.ICodeInvitationCompanyService;
import tn.esprit.spring.serviceInterface.IInvitationService;

@Service
@Slf4j
public class CodeInvitationCompanyServiceImpl implements ICodeInvitationCompanyService{

	@Autowired
	CodeInvitationCompanyRepository CodeInvitationCompanyRepo;
	
	@Override
	public List<CodeInvitationCompany> getAll() {
		try {
			List<CodeInvitationCompany> CodeInvitationCompanys = (List<CodeInvitationCompany>) CodeInvitationCompanyRepo.findAll();
			for (CodeInvitationCompany CodeInvitationCompany : CodeInvitationCompanys) {
				log.info(" CodeInvitationCompany : " + CodeInvitationCompany);
			}
			return CodeInvitationCompanys;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public CodeInvitationCompany add(CodeInvitationCompany CodeInvitationCompany) {
		try {
			CodeInvitationCompany.setDateCreationCodeInvitationCompany(new Date());
			return CodeInvitationCompanyRepo.save(CodeInvitationCompany);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public CodeInvitationCompany update(CodeInvitationCompany CodeInvitationCompanytest) {
		List<CodeInvitationCompany> CodeInvitationCompanys = (List<CodeInvitationCompany>) CodeInvitationCompanyRepo.findAll();
		for (CodeInvitationCompany CodeInvitationCompanyfor : CodeInvitationCompanys) {
			if(CodeInvitationCompanytest.getCodeInvitation().equals(CodeInvitationCompanyfor.getCodeInvitation()))
			{
			System.out.println("code used");
			return null;
			}
		}
		CodeInvitationCompanyRepo.save(CodeInvitationCompanytest);
		return CodeInvitationCompanytest;
	}

	@Override
	public void delete(Long CodeInvitationCompanyid) {
		try {
			CodeInvitationCompanyRepo.deleteById(CodeInvitationCompanyid);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}

	@Override
	public CodeInvitationCompany getById(Long CodeInvitationCompanyid) {
		try {
			CodeInvitationCompany CodeInvitationCompany = CodeInvitationCompanyRepo.findById(CodeInvitationCompanyid).orElse(null);
			return CodeInvitationCompany;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

		@Override
		public CodeInvitationCompany addFinal(CodeInvitationCompany CodeInvitationCompanytest) {
			
				List<CodeInvitationCompany> CodeInvitationCompanys = (List<CodeInvitationCompany>) CodeInvitationCompanyRepo.findAll();
				for (CodeInvitationCompany CodeInvitationCompanyfor : CodeInvitationCompanys) {
					if(CodeInvitationCompanytest.getCodeInvitation().equals(CodeInvitationCompanyfor.getCodeInvitation()))
					{
					System.out.println("code used");
					return null;
					}
				}
				CodeInvitationCompanyRepo.save(CodeInvitationCompanytest);
				return CodeInvitationCompanytest;

		}




}
