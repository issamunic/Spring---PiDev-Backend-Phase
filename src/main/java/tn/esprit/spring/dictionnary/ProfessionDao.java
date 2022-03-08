package tn.esprit.spring.dictionnary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.entities.RelationProfession;
import tn.esprit.spring.repository.ProfessionRepository;
import tn.esprit.spring.repository.RelationProfessionRepository;

@Repository
public class ProfessionDao {
	
	@Autowired
	ProfessionRepository professionRepository;
	
	@Autowired
	RelationProfessionRepository relationProfessionRepository;
	
	private void addRelation(Profession w1, Profession w2) {
		RelationProfession relationProfession=new RelationProfession(w1,w2);
		relationProfessionRepository.save(relationProfession);
    }
	
	public Profession findWord(String word) {
		List<Profession> professions=professionRepository.findAll();
    	for(Profession w : professions) {
    		if(w.getName().toLowerCase().equals(word.toLowerCase())) {
    			return w;
    		}
    	}
        return null;
    }
	
	public Profession addWord(String word) throws Exception {
        if(findWord(word) != null) {
            throw new Exception(String.format("Profession <%s> already exist in database", word));
        }
        Profession _word = new Profession(word);
        Profession professionAdded = professionRepository.save(_word);
        return professionAdded;
    }
	
	public Profession addWord2(String word) throws Exception {
        if(findWord(word) != null) {
            return null;
        }
        Profession _word = new Profession(word);
        Profession professionAdded = professionRepository.save(_word);
        return professionAdded;
    }
	
	public Profession addSynonymForWord(String word, String synonym) throws Exception {
		Profession dbWord = findWord(word);
        
        if(dbWord == null) {
            throw new Exception(String.format("Profession <%s> does not exist in database", word));
        }
        
        return addSynonymForWord(dbWord, synonym);
    }
	
	public Profession addSynonymForWord(Profession word, String synonym) throws Exception {
		Profession dbSynonym = findWord(synonym);
		
        if(dbSynonym == null) {
            addWord(synonym);
            dbSynonym = findWord(synonym);
        }
        List<RelationProfession> relations=relationProfessionRepository.findAll();
        for(RelationProfession relation: relations) {
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
	
	public List<Profession> search(String keyword) {
        List<Profession> results = new LinkedList<>();
        List<Profession> professions=professionRepository.findAll();
        for(Profession d : professions) {
            if(d.like(keyword)) {
                results.add(d);
            }
        }
        return results;
    }
	
	private void fetchSynonyms(Profession word, List<Profession> results,List<RelationProfession> relations) {
    	if(results.contains(word)) {
    		return;
    	}
    	
    	results.add(word);
    	
    	if(word != null) {
    		for(RelationProfession rel : relations) {
                if(rel.getWord1().getName().toLowerCase().equals(word.getName().toLowerCase())) {
                	fetchSynonyms(rel.getWord2(), results,relations);
                } else if(rel.getWord2().getName().toLowerCase().equals(word.getName().toLowerCase())) {
                	fetchSynonyms(rel.getWord1(), results,relations);
                }
            }
    	}
    }
	
	public List<Profession> synonyms(Profession word) {
    	List<Profession> results = new ArrayList<Profession>();
    	List<RelationProfession> relations=relationProfessionRepository.findAll();
        fetchSynonyms(word, results,relations);
        
        results.remove(0);
        
        return results;
    }
	
	public List<Profession> synonyms(String word) {
        return synonyms(findWord(word));
    }
}
