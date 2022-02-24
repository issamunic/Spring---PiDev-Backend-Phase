package tn.esprit.spring.entities;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Post implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idPost;
	String description;
	@ElementCollection
	List<String> country;
	@ElementCollection
	List<String> city;
	@ElementCollection
	List<String> stateOrProvince;
	@Temporal(TemporalType.DATE)
	Date createdOn;
	String url;
	Integer ReactCount;
	@JsonIgnore
	@ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "idUser")
    private User user;
	@JsonIgnore
	@ManyToOne(fetch = LAZY)
    @JoinColumn(name = "communityId", referencedColumnName = "idCommunity")
	Community community;


}
