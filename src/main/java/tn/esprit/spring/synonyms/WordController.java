package tn.esprit.spring.synonyms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/synonym")
public class WordController {
	
    @Autowired
    private WordService wordService;

    @RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
    public List<Word> search(@PathVariable("keyword") String keyword) {
        return wordService.search(keyword);
    }

    @RequestMapping(value = "/synonyms/{word}", method = RequestMethod.GET)
    public List<Word> synonyms(@PathVariable("word") String word) {
        return wordService.synonyms(word);
    }

    @RequestMapping(value = "/addSynonymToWord", method = RequestMethod.POST)
    public Word addSynonymToWord(@RequestParam String word, @RequestParam String synonym) throws Exception {
        return wordService.addSynonymForWord(word, synonym);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam String word) throws Exception {
    	Word wordAdd = wordService.addWord2(word);
        if(wordAdd!=null) {
        	return word+" : added";
        }
        return "already exist !!";
    }
}
