package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Domain implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idDomain;
	
	String name;
	
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "domain")
	List<User> users;
	
	public Domain(String name) {
		this.name=name;
	}
	
	public boolean equals(String wordVal) {
        return name.equalsIgnoreCase(wordVal);
    }
	
	public boolean like(String wordVal) {
        return name.toLowerCase().contains(wordVal.toLowerCase());
    }
}
