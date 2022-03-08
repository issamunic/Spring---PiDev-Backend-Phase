package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Table( name = "Chat")
@Data
public class Chat implements Serializable{
	@Id
	@Column(name="idMessage")
	@GeneratedValue (strategy = GenerationType.IDENTITY)

	private Long idMessage ;
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMsg;
	
	@ManyToOne
	@ToString.Exclude
	@JsonIgnore
	Groups ChatGroup;
	
	@ManyToOne
	User MessageUser; 
	
	@ManyToMany
	private Set<User> etat;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="ChatReact")
	@JsonIgnore
	private Set<ChatReact> react;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationdate;
	
	private Long dureeExpiration;
	
	@Enumerated(EnumType.STRING)
	private MessageType messageType;
	
}
