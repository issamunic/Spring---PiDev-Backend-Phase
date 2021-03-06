package tn.esprit.spring.dictionnary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Profession;

@RestController
@RequestMapping("/ProfessionSynonym")
public class ProfessionWordController {
	
	@Autowired
	ProfessionWordService professionWordService;
	
	@RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
    public List<Profession> search(@PathVariable("keyword") String keyword) {
        return professionWordService.search(keyword);
    }
	
	@RequestMapping(value = "/synonyms/{word}", method = RequestMethod.GET)
    public List<Profession> synonyms(@PathVariable("word") String word) {
        return professionWordService.synonyms(word);
    }
	
	@RequestMapping(value = "/addSynonymToWord", method = RequestMethod.POST)
    public String addSynonymToWord(@RequestParam String word, @RequestParam String synonym) throws Exception {
        if(professionWordService.addSynonymForWord(word, synonym)!=null) {
        	return "this synonym :"+synonym+" is added to "+word;
        }
        return "synonym already exist";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam String word) throws Exception {
    	Profession professionAdd = professionWordService.addWord2(word);
        if(professionAdd!=null) {
        	return word+" : added";
        }
        return "already exist !!";
    }
}
