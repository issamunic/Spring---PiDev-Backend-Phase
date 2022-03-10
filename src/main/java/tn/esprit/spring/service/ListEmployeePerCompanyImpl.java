package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.ListEmployeePerCompany;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.*;
import tn.esprit.spring.serviceInterface.IListEmployeePerCompany;
import tn.esprit.spring.serviceInterface.IUserService;

@Service
@Slf4j
public class ListEmployeePerCompanyImpl implements IListEmployeePerCompany{
	
	@Autowired
	ListEmployeePerCompanyRepository ListEmployeePerCompanyRepo;
	
	@Autowired
	IUserService IUserService;
	
	@Override
	public List<ListEmployeePerCompany> getAll() {
		try {
			List<ListEmployeePerCompany> ListEmployeePerCompanys = (List<ListEmployeePerCompany>) ListEmployeePerCompanyRepo.findAll();
			for (ListEmployeePerCompany ListEmployeePerCompany : ListEmployeePerCompanys) {
				log.info(" ListEmployeePerCompany : " + ListEmployeePerCompany);
			}
			return ListEmployeePerCompanys;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ListEmployeePerCompany add(ListEmployeePerCompany ListEmployeePerCompany) {
		try {
			ListEmployeePerCompany.setDateAdd(new Date());
			return ListEmployeePerCompanyRepo.save(ListEmployeePerCompany);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public ListEmployeePerCompany update(ListEmployeePerCompany ListEmployeePerCompany) {
		try {
			ListEmployeePerCompanyRepo.save(ListEmployeePerCompany);
			return ListEmployeePerCompany;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
		
	}

	@Override
	public void delete(Long ListEmployeePerCompanyid) {
		try {
			ListEmployeePerCompanyRepo.deleteById(ListEmployeePerCompanyid);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}

	@Override
	public ListEmployeePerCompany getById(Long ListEmployeePerCompanyid) {
		try {
			ListEmployeePerCompany ListEmployeePerCompany = ListEmployeePerCompanyRepo.findById(ListEmployeePerCompanyid).orElse(null);
			return ListEmployeePerCompany;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

		
		@Override
		public User getComany(Long id) {
			
				List<ListEmployeePerCompany> ListEmployeePerCompany = (List<ListEmployeePerCompany>) ListEmployeePerCompanyRepo.findAll();
				for (ListEmployeePerCompany ListEmployeePerCompanyfor : ListEmployeePerCompany) {
					if(ListEmployeePerCompanyfor.getEmployee().equals(id)){
						return IUserService.retrieveUser(ListEmployeePerCompanyfor.getComany());
					}
				}
				
				return null;

		}
		
		@Override
		public List<User> getEmployees(Long id) {
			try {
				List<User> Users  = new ArrayList();			
				List<ListEmployeePerCompany> ListEmployeePerCompanys = (List<ListEmployeePerCompany>) ListEmployeePerCompanyRepo.findAll();
				for (ListEmployeePerCompany ListEmployeePerCompanyfor : ListEmployeePerCompanys) {
					if(id.equals(ListEmployeePerCompanyfor.getComany())) {
						Users.add(IUserService.retrieveUser(ListEmployeePerCompanyfor.getEmployee()));
					}
				}
				System.out.println(Users.size());
				return Users;
				
		}
			catch (Exception e) {
				System.out.println(e);
			return null;
		}
		}
		
}
