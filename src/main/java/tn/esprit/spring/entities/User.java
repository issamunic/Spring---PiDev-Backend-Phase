package tn.esprit.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String login;
	String password;
	Integer registrationNumber; 
	String FirstName;
	String LastName;
	Date BirthDate;
	String socialStatus;
	String profilePicture;
	String name;
	String logo;
	
	
	@OneToMany
	List<Trip> trips;
}
