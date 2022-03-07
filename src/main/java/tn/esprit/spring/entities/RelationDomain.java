package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RelationDomain implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRelationDomain;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Domain word1;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Domain word2;
	
	public RelationDomain() {}
	
	public RelationDomain(Domain w1, Domain w2) {
		this.word1 = w1;
		this.word2 = w2;
	}
	
	public Domain getWord1() {
		return word1;
	}
	
	public void setWord1(Domain word1) {
		this.word1 = word1;
	}
	
	public Domain getWord2() {
		return word2;
	}
	
	public void setWord2(Domain word2) {
		this.word2 = word2;
	}
}

