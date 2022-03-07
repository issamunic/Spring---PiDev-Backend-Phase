package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class User implements  Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idUser;
	
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
	



	
	
	
	
}
