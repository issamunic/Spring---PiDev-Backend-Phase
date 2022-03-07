package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class User implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long idUser;
	
	private String login;
	private String password;
	private Integer registrationNumberEmploye; 
	private String FirstNameEmploye;
	private String LastNameEmploye;
	
	@Temporal(TemporalType.DATE)
	private Date BirthDateEmploye;
	
	private String socialStatusEmploye;
	private String profilePictureEmploye;
	private String nameCompany;
	private String logoCompany;
	

	
	@OneToMany
	private List<Trip> trips;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="utilisateur")
	private List<Report> reclamations;
}
