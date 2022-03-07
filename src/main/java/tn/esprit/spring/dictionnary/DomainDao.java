package tn.esprit.spring.dictionnary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.entities.RelationDomain;
import tn.esprit.spring.repository.DomainRepository;
import tn.esprit.spring.repository.RelationDomainRepository;

@Repository
public class DomainDao {
	
	@Autowired
	DomainRepository domainRepository;
	
	@Autowired
	RelationDomainRepository relationDomainRepository;
	
	private void addRelation(Domain w1, Domain w2) {
		RelationDomain relationDomain=new RelationDomain(w1,w2);
		relationDomainRepository.save(relationDomain);
    }
	
	public Domain findWord(String word) {
		List<Domain> domains=domainRepository.findAll();
    	for(Domain w : domains) {
    		if(w.getName().toLowerCase().equals(word.toLowerCase())) {
    			return w;
    		}
    	}
        return null;
    }
	
	public Domain addWord(String word) throws Exception {
        if(findWord(word) != null) {
            throw new Exception(String.format("Domain <%s> already exist in database", word));
        }
        Domain _word = new Domain(word);
        Domain domainAdded = domainRepository.save(_word);
        return domainAdded;
    }
	
	public Domain addWord2(String word) throws Exception {
        if(findWord(word) != null) {
            return null;
        }
        Domain _word = new Domain(word);
        Domain domainAdded = domainRepository.save(_word);
        return domainAdded;
    }
	
	public Domain addSynonymForWord(String word, String synonym) throws Exception {
		Domain dbWord = findWord(word);
        
        if(dbWord == null) {
            throw new Exception(String.format("Domain <%s> does not exist in database", word));
        }
        
        return addSynonymForWord(dbWord, synonym);
    }
	
	/*public Domain addSynonymForWord(Domain word, String synonym) throws Exception {
		Domain dbSynonym = findWord(synonym);
        
        if(dbSynonym == null) {
            addWord(synonym);
            dbSynonym = findWord(synonym);
        }
        
        addRelation(word, dbSynonym);

        return dbSynonym;
    }*/
	
	public Domain addSynonymForWord(Domain word, String synonym) throws Exception {
		Domain dbSynonym = findWord(synonym);
		
        if(dbSynonym == null) {
            addWord(synonym);
            dbSynonym = findWord(synonym);
        }
        List<RelationDomain> relations=relationDomainRepository.findAll();
        for(RelationDomain relation: relations) {
        	if((relation.getWord1().getName().toLowerCase().equals(word.getName().toLowerCase()) && 
        			relation.getWord2().getName().toLowerCase().equals(dbSynonym.getName().toLowerCase())) ||
        			(relation.getWord1().getName().toLowerCase().equals(dbSynonym.getName().toLowerCase()) && 
                			relation.getWord2().getName().toLowerCase().equals(word.getName().toLowerCase())) ) {
        		return null;
        	}
        }
        addRelation(word, dbSynonym);

        return dbSynonym;
    }
	
	public List<Domain> search(String keyword) {
        List<Domain> results = new LinkedList<>();
        List<Domain> domains=domainRepository.findAll();
        for(Domain d : domains) {
            if(d.like(keyword)) {
                results.add(d);
            }
        }
        return results;
    }
	
	private void fetchSynonyms(Domain word, List<Domain> results,List<RelationDomain> relations) {
    	if(results.contains(word)) {
    		return;
    	}
    	
    	results.add(word);
    	
    	if(word != null) {
    		for(RelationDomain rel : relations) {
                if(rel.getWord1().getName().toLowerCase().equals(word.getName().toLowerCase())) {
                	fetchSynonyms(rel.getWord2(), results,relations);
                } else if(rel.getWord2().getName().toLowerCase().equals(word.getName().toLowerCase())) {
                	fetchSynonyms(rel.getWord1(), results,relations);
                }
            }
    	}
    }
	
	public List<Domain> synonyms(Domain word) {
    	List<Domain> results = new ArrayList<Domain>();
    	List<RelationDomain> relations=relationDomainRepository.findAll();
        fetchSynonyms(word, results,relations);
        
        results.remove(0);
        
        return results;
    }
	
	public List<Domain> synonyms(String word) {
        return synonyms(findWord(word));
    }
}
