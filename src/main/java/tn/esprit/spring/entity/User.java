package tn.esprit.spring.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idUser")
	private Long idUser; 
	private String  nom;
	private String  prenom;
	private String  mailAdress;
	private String  password;
	private String pdp;
	


	
	
	
	
}
