package tn.esprit.spring.dictionnary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Profession;

@Service
public class ProfessionWordService {
	
	@Autowired
	ProfessionDao professionDao;
	
	public Profession addWord(String word) throws Exception {
        return professionDao.addWord(word);
    }
    
    public Profession addWord2(String word) throws Exception {
        return professionDao.addWord2(word);
    }
    
    public Profession addSynonymForWord(Profession word, String synonym) throws Exception {
    	return professionDao.addSynonymForWord(word, synonym);
    }
    
    public Profession addSynonymForWord(String word, String synonym) throws Exception {
        return professionDao.addSynonymForWord(word, synonym);
    }
    
    //--
    public Profession findWord(String word) {
    	return professionDao.findWord(word);
    }
    
    public List<Profession> search(String keyword) {
        return professionDao.search(keyword);
    }
    
    public List<Profession> synonyms(Profession word) {
    	return professionDao.synonyms(word);
    }
    
    public List<Profession> synonyms(String word) {
        return professionDao.synonyms(word);
    }
}
