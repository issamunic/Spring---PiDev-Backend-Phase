package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table( name = "ChatReact")
@Data
public class ChatReact implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idReact")
	
	private Long idReact ;
	
	
	@ManyToOne
	User userReact;
	
	@ManyToOne
	Chat ChatReact;
	
	@Enumerated(EnumType.STRING)
	private React react;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfReact;
	
}
