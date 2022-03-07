package tn.esprit.spring.dictionnary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Domain;


@Service
public class DomainWordService {
	
	@Autowired
	DomainDao domainDao;
	
	public Domain addWord(String word) throws Exception {
        return domainDao.addWord(word);
    }
    
    public Domain addWord2(String word) throws Exception {
        return domainDao.addWord2(word);
    }
    
    public Domain addSynonymForWord(Domain word, String synonym) throws Exception {
    	return domainDao.addSynonymForWord(word, synonym);
    }
    
    public Domain addSynonymForWord(String word, String synonym) throws Exception {
        return domainDao.addSynonymForWord(word, synonym);
    }
    
    public Domain findWord(String word) {
    	return domainDao.findWord(word);
    }
    
    public List<Domain> search(String keyword) {
        return domainDao.search(keyword);
    }
    
    public List<Domain> synonyms(Domain word) {
    	return domainDao.synonyms(word);
    }
    
    public List<Domain> synonyms(String word) {
        return domainDao.synonyms(word);
    }
  
}
