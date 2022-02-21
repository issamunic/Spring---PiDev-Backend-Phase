package tn.esprit.spring.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Data;

@Entity
@Table( name = "Chat")
@Data
public class Chat {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idMessage")
	
	private Long idMessage ;
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMsg;
	
	@ManyToOne
	Groups ChatGroup;
	
	@ManyToOne
	Users MessageUser; 
	
	@ManyToMany(mappedBy="etatMessage", cascade = CascadeType.ALL)
	private Set<Users> etat;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="ChatReact")
	private Set<ChatReact> react;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationdate;
	
}
