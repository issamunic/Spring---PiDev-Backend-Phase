package tn.esprit.spring.serviceInterface;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Image;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;

public interface IUserService {
	
	public abstract List<User> retrieveAllUsers();
	
	public abstract User addUser(User user);
	
	public abstract void deleteUser(Long id);

	public abstract User updateUser(User user);
	
	public abstract User modifyUser(User user);

	public abstract User retrieveUser(Long id);
	
	public abstract void assignUserToDomain(Long idUser,Long idDomain);
	
	public abstract void assignUserToProfession(Long idUser,Long idProfession);
	
	public List<User> findUsersByRole(Role role);
	
	public List<User> getCompanysByName(String name);
	
	public List<User> findEmployesByName(String nameEmp);
	
	public List<User> findEmployesWithProfession(Long idProfession);
	
	public List<User> findCompanysWithDomain(Long idDomain);
	
	public List<User> findEmployesWithBirthDate(Date dateInf,Date dateDeb);
	
	void assignImageToUser(Long idImage,Long idUser);
	
	String assignRolesToUser(Long idRoles,Long idUser);
	
	Optional<Image> retrieveImageUser(Long idUser);
	
	void register(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;
	
	boolean verify(String verificationCode);
	
	boolean testActiveAccount(String login);
	
	boolean checkEmailUsed(String login);
}
