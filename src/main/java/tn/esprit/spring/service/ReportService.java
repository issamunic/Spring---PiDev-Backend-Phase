package tn.esprit.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.RepportRepository;
import tn.esprit.spring.repository.UserRepo;
import tn.esprit.spring.serviceInterface.IReportService;

@Service
@Slf4j
public class ReportService implements IReportService {
	
	@Autowired
	 RepportRepository repoRecalam;
	
	@Autowired
	 UserRepo userRepo;

	
	
	
	@Override
	public Report addReclamation(Report r) {
		r.setUtilisateur(userRepo.findById((long) 2).orElse(null));
		repoRecalam.save(r);
		return r;
		
	}

	@Override
	public void deleteReclamation(int id) {
		repoRecalam.deleteById(id);

	}

	@Override
	public void updateReclamation(Report r) {
		
		repoRecalam.save(r);
		
	}

	@Override
	public List<Report> RecalamationList() {
		List<Report> listReclam = (List<Report>) repoRecalam.findAll();
		return  listReclam;
	}

	@Override
	public List<Report> findReclamByUser(long id) {
		User user = userRepo.findById(id).orElse(null);
		return user.getReclamations();
	}
	
	
	

}
