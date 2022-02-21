package tn.esprit.spring.entity;

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table( name = "Groups")
@Data
public class Groups {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idGroup")
	
	private Long idGroup;
	private String groupeName;
	
	@Enumerated(EnumType.STRING)
	private themes theme;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateGroupe;
	
	private String imageGroup;
	
	@ManyToMany(mappedBy="UserGroup", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Users> GroupUser;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="ChatGroup")
	
	private Set<Chat> ChatMessage;
	
	@Enumerated(EnumType.STRING)
	private GroupeType groupeSecuritytype;
	
	
	
}
