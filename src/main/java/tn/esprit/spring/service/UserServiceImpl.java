package tn.esprit.spring.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.entities.Image;
import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Roles;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.imageConfig.ImageUtility;
import tn.esprit.spring.repository.DomainRepository;
import tn.esprit.spring.repository.ImageRepository;
import tn.esprit.spring.repository.ProfessionRepository;
import tn.esprit.spring.repository.RolesRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.serviceInterface.IUserService;

@Service
@Slf4j
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DomainRepository domainRepository;
	
	@Autowired
	ProfessionRepository professionRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired 
	ImageRepository imageRepository;
	
	@Autowired
	RolesRepository rolesRepository;
	
	@Autowired
    private JavaMailSender mailSender;
	
	
	public void register(User user, String siteURL) throws UnsupportedEncodingException, MessagingException{
		user.setPassword(getEncodedPassword(user.getPassword()));
	    String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
	    user.setActive(false);
	     
	    userRepository.save(user);
	     
	    sendVerificationEmail(user, siteURL);
    }
     
	private void sendVerificationEmail(User user, String siteURL)
	        throws MessagingException, UnsupportedEncodingException {
	    String toAddress = user.getLogin();
	    String fromAddress = "mohamed.benrabah98@gmail.com";
	    String senderName = user.getNameCompany();
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
	            + "Thank you,<br>"
	            + "Your company name.";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", user.getLogin());
	    String verifyURL = siteURL + "/user/verify?code=" + user.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}
	
	public boolean verify(String verificationCode) {
	    User user = userRepository.findByVerificationCode(verificationCode);
	    if (user == null || user.isActive()) {
	        return false;
	    } else {
	        user.setVerificationCode(null);
	        user.setActive(true);
	        userRepository.save(user);
	        return true;
	    }
	}
	
	@Override
	public List<User> retrieveAllUsers() {
		List<User> users=userRepository.findAll();
		for(User user:users) {
			log.info("user : "+user);
		}
		return users;
	}

	@Override
	public User addUser(User user) {
		try {
			user.setPassword(getEncodedPassword(user.getPassword()));
			user.setActive(false);
			User u=userRepository.save(user);
			log.info("user added : "+u);
			return u;
		}
		catch(Exception e) {
			log.info("erreur user add : "+e.getMessage());
			return null;
		}
	}

	@Override
	public void deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
			log.info("user deleted with id: "+id);
		}
		catch(Exception e) {
			log.info("erreur user delete : "+id);
		}
	}

	@Override
	public User updateUser(User user) {
		try {
			user.setPassword(getEncodedPassword(user.getPassword()));
			User u=userRepository.save(user);
			log.info("user update : "+u);
			return u;
		}
		catch(Exception e) {
			log.info("erreur user update : "+e.getMessage());
			return null;
		}
	}
	
	/*@Override
	public User updateUser(User user,MultipartFile file) {
		try {
			user.setPassword(getEncodedPassword(user.getPassword()));
			if(file!=null) {
				Image image=imageRepository.save(Image.builder()
		                .name(file.getOriginalFilename())
		                .type(file.getContentType())
		                .image(ImageUtility.compressImage(file.getBytes())).build());
				user.setImage(image);
			}
			User u=userRepository.save(user);
			log.info("user update : "+u);
			return u;
		}
		catch(Exception e) {
			log.info("erreur user update : "+e.getMessage());
			return null;
		}
	}*/

	@Override
	public User retrieveUser(Long id) {
		try {
			User u=userRepository.findById(id).orElse(null);
			log.info("user retrieve : "+u);
			return u;
		}
		catch(Exception e) {
			log.info("erreur user retrieve : "+e.getMessage());
			return null;
		}
	}

	@Override
	public void assignUserToDomain(Long idUser, Long idDomain) {
		try {
			User user=userRepository.findById(idUser).orElse(null);
			Domain domain=domainRepository.findById(idDomain).orElse(null);
			if(user!=null && domain!=null) {
				if(user.getRole().equals(Role.company)) {
					user.setDomain(domain);
					User userUpdated=userRepository.save(user);
					log.info("assign user to domain : "+userUpdated);
				}
			}
		}
		catch(Exception e) {
			log.info("erreur to assign user to domain : "+e.getMessage());
		}
	}
	
	@Override
	public void assignUserToProfession(Long idUser,Long idProfession) {
		try {
			User user=userRepository.findById(idUser).orElse(null);
			Profession profession=professionRepository.findById(idProfession).orElse(null);
			if(user!=null && profession!=null) {
				if(user.getRole().equals(Role.employe)) {
					user.setProfession(profession);
					User userUpdated=userRepository.save(user);
					log.info("assign user to profession : "+userUpdated);
				}
			}
		}
		catch(Exception e) {
			log.info("erreur to assign user to profession : "+e.getMessage());
		}
	}

	@Override
	public List<User> findUsersByRole(Role role) {
		try {
			List<User> users = userRepository.findByRole(role);
			for(User user:users) {
				log.info("user by role : "+user);
			}
			return users;
		}
		catch(Exception e) {
			log.info("erreur findUsersByRole : "+e.getMessage());
			return null;
		}
	}

	@Override
	public List<User> getCompanysByName(String name) {
		try {
			List<User> users = userRepository.findCompanysByName(name);
			for(User user:users) {
				log.info("company by name : "+user);
			}
			return users;
		}
		catch(Exception e) {
			log.info("erreur getCompanysByName : "+e.getMessage());
			return null;
		}
	}

	@Override
	public List<User> findEmployesByName(String nameEmp) {
		try {
			List<User> users=userRepository.findEmployesByName(nameEmp);
			for(User user:users) {
				log.info("employe by name : "+user);
			}
			return users;
		}
		catch(Exception e) {
			log.info("erreur findEmployesByName : "+e.getMessage());
			return null;
		}
	}

	@Override
	public List<User> findEmployesWithProfession(Long idProfession) {
		try {
			Profession profession=professionRepository.findById(idProfession).orElse(null);
			if(profession!=null) {
				List<User> listUsers=userRepository.findAll();
				List<User> usersByProfession=new ArrayList<>();
				for(User user:listUsers) {
					if(user.getProfession()!=null) {
						if(user.getProfession().equals(profession)) {
							usersByProfession.add(user);
							log.info("employe by profession : "+user);
						}
					}
				}
				return usersByProfession;
			}
			log.info("profession does not exist");
			return null;
		}
		catch(Exception e) {
			log.info("erreur findEmployesWithProfession : "+e.getMessage());
			return null;
		}
	}

	@Override
	public List<User> findCompanysWithDomain(Long idDomain) {
		try {
			Domain domain=domainRepository.findById(idDomain).orElse(null);
			if(domain!=null) {
				List<User> users=userRepository.findByDomain(domain);
				for(User user:users) {
					log.info("company by domain : "+user);
				}
				return users;
			}
			log.info("domain does not exist");
			return null;
		}
		catch(Exception e) {
			log.info("erreur findCompanysWithDomain : "+e.getMessage());
			return null;
		}
	}

	@Override
	public List<User> findEmployesWithBirthDate(Date dateInf, Date dateDeb) {
		try {
			List<User> users=userRepository.getEmployesByDateRange(dateInf,dateDeb);
			for(User user:users) {
				log.info("company by birthDate : "+user);
			}
			return users;
		}
		catch(Exception e) {
			log.info("erreur findCompanysWithBirthDate : "+e.getMessage());
			return null;
		}
	}
	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

	@Override
	public void assignImageToUser(Long idImage, Long idUser) {
		Image image=imageRepository.findById(idImage).orElse(null);
		User user=userRepository.findById(idUser).orElse(null);
		user.setImage(image);
		userRepository.save(user);
	}
	
	@Override
	public String assignRolesToUser(Long idRoles,Long idUser) {
		Roles roles=rolesRepository.findById(idRoles).orElse(null);
		User user=userRepository.findById(idUser).orElse(null);
		if(user.getRoles().size()>0) {
			for (Roles r : user.getRoles()) {
				if(r.getIdRoles()==roles.getIdRoles()) {
					return "this user is already a(n) "+r.getRoleName();
				}
			}
		}
		user.setRole(Role.valueOf(roles.getRoleName()));
		user.getRoles().clear();
		user.getRoles().add(roles);
		userRepository.save(user);
		return "this user is now : a(n) "+roles.getRoleName();
	}

	@Override
	public Optional<Image> retrieveImageUser(Long idUser) {
		User user=userRepository.findById(idUser).orElse(null);
		if(user!=null && user.getImage()!=null) {
			Image img=user.getImage();
			return imageRepository.findByName(img.getName());
		}
		return null;
	}
}
