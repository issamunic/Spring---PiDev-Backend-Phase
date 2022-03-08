package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RelationProfession implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRelationProfession;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Profession word1;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Profession word2;
	
	public RelationProfession() {}
	
	public RelationProfession(Profession w1, Profession w2) {
		this.word1 = w1;
		this.word2 = w2;
	}
	
	public Profession getWord1() {
		return word1;
	}
	
	public void setWord1(Profession word1) {
		this.word1 = word1;
	}
	
	public Profession getWord2() {
		return word2;
	}
	
	public void setWord2(Profession word2) {
		this.word2 = word2;
	}
	
}
