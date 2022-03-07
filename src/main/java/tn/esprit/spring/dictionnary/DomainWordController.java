package tn.esprit.spring.dictionnary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Domain;

@RestController
@RequestMapping("/DomainSynonym")
public class DomainWordController {
	
	@Autowired
	DomainWordService domainWordService;
	
	@RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
    public List<Domain> search(@PathVariable("keyword") String keyword) {
        return domainWordService.search(keyword);
    }
	
	@RequestMapping(value = "/synonyms/{word}", method = RequestMethod.GET)
    public List<Domain> synonyms(@PathVariable("word") String word) {
        return domainWordService.synonyms(word);
    }
	
	@RequestMapping(value = "/addSynonymToWord", method = RequestMethod.POST)
    public String addSynonymToWord(@RequestParam String word, @RequestParam String synonym) throws Exception {
        if(domainWordService.addSynonymForWord(word, synonym)!=null) {
        	return "this synonym :"+synonym+" is added to "+word;
        }
        return "synonym already exist";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam String word) throws Exception {
    	Domain domainAdd = domainWordService.addWord2(word);
        if(domainAdd!=null) {
        	return word+" : added";
        }
        return "already exist !!";
    }
}
