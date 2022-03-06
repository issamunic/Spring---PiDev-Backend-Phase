package tn.esprit.spring.synonyms;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class WordDao {
	
	private static List<Word> words;
	
	private static List<Relation> relations;
	
	static {
        words = new LinkedList<>();
        relations = new LinkedList<>();

        // Insert some dummy data
        
        words.add(new Word("Clean"));	//=>0
        words.add(new Word("Wash"));	//=>1
        words.add(new Word("Run"));		//=>2
        words.add(new Word("Build"));	//=>3
        words.add(new Word("Create"));	//=>4
        words.add(new Word("Make"));	//=>5

        // Make Wash as synonym of Clean
        addRelation(words.get(0), words.get(1));
        
        // Make Build as synonym of Make
        addRelation(words.get(5), words.get(3));

        // Make Create as synonym of Make
        addRelation(words.get(5), words.get(4));
    }
	
	private static void addRelation(Word w1, Word w2) {
        // TODO:
        // Before creating relation, first check if relation already exists?

        relations.add(new Relation(w1, w2));
    }
	
	/**
     * Find word in database
     *
     * @param word - word which need to be found
     * @return founded word or null if nothing is found
     */
    public Word findWord(String word) {
    	for(Word w : words) {
    		if(w.equals(word)) {
    			return w;
    		}
    	}
        return null;
    }
    
    /**
     * Insert word into database
     *
     * @param word value of word
     * @return added word
     */
    public Word addWord(String word) throws Exception {
        if(findWord(word) != null) {
            throw new Exception(String.format("Word <%s> already exist in database", word));
        }
        Word _word = new Word(word);
        words.add(_word);
        return _word;
    }
    
    public Word addWord2(String word) throws Exception {
        if(findWord(word) != null) {
            return null;
        }
        Word _word = new Word(word);
        words.add(_word);
        return _word;
    }
    
    /**
     * Attach synonym to word
     *
     * @param word value of word
     * @param synonym value of synonym
     * @return Word object of created synonym
     * @throws Exception If word does not exist in database
     */
    public Word addSynonymForWord(String word, String synonym) throws Exception {
        Word dbWord = findWord(word);
        
        if(dbWord == null) {
            throw new Exception(String.format("Word <%s> does not exist in database", word));
        }
        
        return addSynonymForWord(dbWord, synonym);
    }
    
    /**
     * 
     * @param word word
     * @param synonym synonym that needs to be added
     * @return Word object of created synonym
     * @throws Exception If word does not exist in database
     */
    public Word addSynonymForWord(Word word, String synonym) throws Exception {
    	Word dbSynonym = findWord(synonym);
        
        if(dbSynonym == null) {
            addWord(synonym);
            dbSynonym = findWord(synonym);
        }
        
        addRelation(word, dbSynonym);

        return dbSynonym;
    }
    
    /**
     * Search for words by keyword
     *
     * @param keyword searching pattern
     * @return list of matching words
     */
    public List<Word> search(String keyword) {
        List<Word> results = new LinkedList<>();

        for(Word w : words) {
            if(w.like(keyword)) {
                results.add(w);
            }
        }

        return results;
    }
    
    /**
     * Fetch synonyms for word
     * @param word Word which synonyms needs to be fetched
     * @param results Reference to results list
     */
    private void fetchSynonyms(Word word, List<Word> results) {
    	if(results.contains(word)) {
    		return;
    	}
    	
    	results.add(word);
    	
    	if(word != null) {
    		for(Relation rel : relations) {
                if(rel.getWord1() == word) {
                	fetchSynonyms(rel.getWord2(), results);
                } else if(rel.getWord2() == word) {
                	fetchSynonyms(rel.getWord1(), results);
                }
            }
    	}
    }
    
    /**
     * 
     * @param word - Word of which synonyms will be searched
     * @return List of synonyms of word
     */
    public List<Word> synonyms(Word word) {
    	List<Word> results = new LinkedList<Word>();
    	
        fetchSynonyms(word, results);
        
        results.remove(0);
        
        return results;
    }
    
    /**
     * Searching for synonyms of a word
     *
     * @param word value of word
     * @return List of synonyms for a word
     */
    public List<Word> synonyms(String word) {
        return synonyms(findWord(word));
    }
}
