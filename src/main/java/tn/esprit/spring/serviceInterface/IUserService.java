package tn.esprit.spring.serviceInterface;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;

public interface IUserService {
	
	public abstract List<User> retrieveAllUsers();
	
	public abstract User addUser(User user);
	
	public abstract void deleteUser(Long id);

	public abstract User updateUser(User user);

	public abstract User retrieveUser(Long id);
	
	public abstract void assignUserToDomain(Long idUser,Long idDomain);
	
	public abstract void assignUserToProfession(Long idUser,Long idProfession);
	
	public List<User> findUsersByRole(Role role);
	
	public List<User> getCompanysByName(String name);
	
	public List<User> findEmployesByName(String nameEmp);
	
	public List<User> findEmployesWithProfession(Long idProfession);
	
	public List<User> findCompanysWithDomain(Long idDomain);
	
	public List<User> findCompanysWithBirthDate(Date dateInf,Date dateDeb);
}
