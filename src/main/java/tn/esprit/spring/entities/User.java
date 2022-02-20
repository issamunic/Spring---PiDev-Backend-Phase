package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idUser;
	
	String login;
	String password;
	Integer registrationNumberEmploye; 
	String FirstNameEmploye;
	String LastNameEmploye;
	
	@Temporal(TemporalType.DATE)
	Date BirthDateEmploye;
	
	String socialStatusEmploye;
	String profilePictureEmploye;
	String nameCompany;
	String logoCompany;
	
	@ManyToOne
	Profession profession;
	
	@ManyToOne
	Domain domain;
	
	int x;
}
