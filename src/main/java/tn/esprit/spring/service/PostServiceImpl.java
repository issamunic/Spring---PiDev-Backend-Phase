package tn.esprit.spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.core.Pipeline;
import tn.esprit.spring.entities.Community;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.exceptions.ForumException;
import tn.esprit.spring.repository.CommunityRepository;
import tn.esprit.spring.repository.PostRepository;
import tn.esprit.spring.serviceInterface.IPostService;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {
	
	@Autowired
	PostRepository postrepo;
	@Autowired
	CommunityRepository communityRepo;
	
	StanfordCoreNLP stanfordCoreNLP = Pipeline.getInstance();
	
	
	static Map<String, String[]> words = new HashMap<>();
    
    static int largestWordLength = 0;
    
    public static void loadBadWords() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv").openConnection().getInputStream()));
            String line = "";
            int counter = 0;
            while((line = reader.readLine()) != null) {
                counter++;
                String[] content = null;
                try {
                    content = line.split(",");
                    if(content.length == 0) {
                        continue;
                    }
                    String word = content[0];
                    String[] ignore_in_combination_with_words = new String[]{};
                    if(content.length > 1) {
                        ignore_in_combination_with_words = content[1].split("_");
                    }

                    if(word.length() > largestWordLength) {
                        largestWordLength = word.length();
                    }
                    words.put(word.replaceAll(" ", ""), ignore_in_combination_with_words);

                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Loaded " + counter + " words to filter out");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Iterates over a String input and checks whether a cuss word was found in a list, then checks if the word should be ignored (e.g. bass contains the word *ss).
     * @param input
     * @return
     */
     
    public static ArrayList<String> badWordsFound(String input) {
    	 loadBadWords();
        if(input == null) {
            return new ArrayList<>();
        }

        // don't forget to remove leetspeak, probably want to move this to its own function and use regex if you want to use this 
        
        input = input.replaceAll("1","i");
        input = input.replaceAll("!","i");
        input = input.replaceAll("3","e");
        input = input.replaceAll("4","a");
        input = input.replaceAll("@","a");
        input = input.replaceAll("5","s");
        input = input.replaceAll("7","t");
        input = input.replaceAll("0","o");
        input = input.replaceAll("9","g");
        

        ArrayList<String> badWords = new ArrayList<>();
        input = input.toLowerCase().replaceAll("[^a-zA-Z]", "");

        // iterate over each letter in the word
        for(int start = 0; start < input.length(); start++) {
            // from each letter, keep going to find bad words until either the end of the sentence is reached, or the max word length is reached. 
            for(int offset = 1; offset < (input.length()+1 - start) && offset < largestWordLength; offset++)  {
                String wordToCheck = input.substring(start, start + offset);
                if(words.containsKey(wordToCheck)) {
                    // for example, if you want to say the word bass, that should be possible.
                    String[] ignoreCheck = words.get(wordToCheck);
                    boolean ignore = false;
                    for(int s = 0; s < ignoreCheck.length; s++ ) {
                        if(input.contains(ignoreCheck[s])) {
                            ignore = true;
                            break;
                        }
                    }
                    if(!ignore) {
                        badWords.add(wordToCheck);
                    }
                }
            }
        }


        for(String s: badWords) {
            System.out.println(s + " qualified as a bad word!");
        }
        return badWords;

    }

    public static String filterText(String input) {
        ArrayList<String> badWords = badWordsFound(input);
        if(badWords.size() > 0) {
            return "This message was blocked because a bad word was found. If you believe this word should not be blocked, please message support.";
        }
        return input;
    }
    
	
	@Override
	public Post addPost(Post p) {
		// TODO Auto-generated method stub
		String description = p.getDescription();
		if (filterText(description).equals(description))		
			{
			return postrepo.save(p);}
		else  return null;
		
	}

	@Override
	public void deletePost(Long id) {
		// TODO Auto-generated method stub
		postrepo.deleteById(id);
		
	}

	@Override
	public Post updatePost(Post p) {
		// TODO Auto-generated method stub
		String description = p.getDescription();
		if (filterText(description).equals(description))
		{	Date date = new Date(System.currentTimeMillis());
			p.setCreatedOn(date);
		return postrepo.save(p);}
		else throw (new ForumException("this contains bad words!"));
	}

	@Override
	public Post retrievePost(Long id) {
		return postrepo.findById(id).orElse(null);
	}

	@Override
	public List<Post> retrievePostsByCountry(String country) {
		// TODO Auto-generated method stub
		
		List<Post>postsToReturn = new ArrayList<Post>();
		List<Post> lastmonthPosts = postrepo.findLastMonthPosts();
		
		for (Post post : lastmonthPosts) {
			String description =post.getDescription();
			description = description.replaceAll(".", "");
			description = description.replaceAll(",", "");
//			description = description.replaceAll("?", "");
//			description = description.replaceAll("!", "");
//			description = description.replaceAll("*", "");
			log.info(lastmonthPosts.toString());
			CoreDocument coreDocument = new CoreDocument(description);
			stanfordCoreNLP.annotate(coreDocument);
			List<CoreLabel> coreLabels = coreDocument.tokens();
			log.info(coreLabels.toString());
			for(CoreLabel coreLabel: coreLabels) {
				String ner = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
				log.info(coreLabel.originalText() +" " +ner);
				if ((ner.equalsIgnoreCase("country"))  &&(coreLabel.originalText().equalsIgnoreCase(country)))
				{
					postsToReturn.add(post);
				}
			}		
			}
		  return  postsToReturn;
	}

	@Override
	public List<Post> retrievePostsByLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> retrievePostsByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> retreivePostsByCommunity(Long idCommunity) {
		// TODO Auto-generated method stub
		Community community = communityRepo.findById(idCommunity).orElse(null);
		return postrepo.findAllPostsByCommunity(community);
	}
	

}
