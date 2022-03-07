package tn.esprit.spring.entity;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table( name = "Stories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stories implements Serializable{
	@Id
	@Column(name="idStories")
	@GeneratedValue (strategy = GenerationType.IDENTITY)

	private Long idStories ;
	
	private String Media;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStories;
	
	@Enumerated(EnumType.STRING)
	private EtatStories etatStorie;
	
	@Enumerated(EnumType.STRING)
	private VisibilityType visibility;
	
	@ManyToOne
	Users userStorie; 
	
	@ManyToMany
	private Set<Users> ViewsStroie; 
	
	@ManyToMany	
	
	private Set<Users> ExceptStroie;

	
}
