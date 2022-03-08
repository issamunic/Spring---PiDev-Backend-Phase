package tn.esprit.spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.core.Pipeline;
import tn.esprit.spring.dto.CommentsDto;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.mapper.CommentMapper;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.PostRepository;
import tn.esprit.spring.serviceInterface.ICommentService;
import java.util.regex.Pattern;
@Service
@AllArgsConstructor
@Slf4j
public class CommentServiceImpl implements ICommentService {
	
	@Autowired
	CommentRepository comRepo;
	@Autowired
	PostRepository postrepo;
	
	private final CommentMapper commentMapper;
	
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
	public String addSentimentToComment(Comment comment) {
		// TODO Auto-generated method stub
		String text = comment.getText();
		int sentimentInt;
	      String sentimentName = null; 
	      Annotation annotation = stanfordCoreNLP.process(text);
	      for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class))
	      {
	         Tree tree = sentence.get(SentimentAnnotatedTree.class);
	        sentimentInt = RNNCoreAnnotations.getPredictedClass(tree); 
	                sentimentName = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
	        System.out.println(sentimentName + "\t" + sentimentInt + "\t" + sentence);
	      }
	      
	      return sentimentName;
	     }
	
	

	@Override
	public void deleteComment(Long id) {
		// TODO Auto-generated method stub
		comRepo.deleteById(id);
		
	}

	

	@Override
	public List<Comment> retrieveCommentsByPostAndSentiment(Long postId, String sentiment) {
		// TODO Auto-generated method stub
		Post post = postrepo.findById(postId).orElse(null);
		if (post!=null) {
			List<Comment> comments = comRepo.findAll();
			List<Comment> commentsToReturn = new ArrayList<Comment>();
			for (Comment comment : comments) {
				if (comment.getPost().equals(post)&& comment.getSentiment().equals(sentiment));
				commentsToReturn.add(comment);
			}return commentsToReturn;
		}
		return null;
	}
	

	

	
	  @Override 
	  public Comment addComment(CommentsDto commentDto) { 
		  // TODOAuto-generated method stub 
		  Post post = postrepo.findById(commentDto.getPostId()).orElse(null); 
		  if (filterText(commentDto.getText()).equals(commentDto.getText()) && post !=null) 
		 { postrepo.save(post); 
	  Comment comment = commentMapper.map(commentDto, post, null);
	  comment.setSentiment(addSentimentToComment(comment));
	  return comRepo.save(comment);
	  } 
		  return null; 
		  }
	 

	@Override
	public List<Comment> retrieveCommentsByPost(Long postId) {
		// TODO Auto-generated method stub
		Post post = postrepo.findById(postId).orElse(null);
		List<Comment> comments = comRepo.findAll();
		List<Comment> commentsToReturn = new ArrayList<Comment>();
		for (Comment comment : comments) {
			if (comment.getPost().equals(post));
			commentsToReturn.add(comment);
		}
		return commentsToReturn;
	}

	@Override
	public List<String> sentimentsOfCommentsByPost(Long postId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Double> PourcentageOfSentimentsLastWeek() {
		// TODO Auto-generated method stub
		List<Double> pourcentages = new ArrayList<Double>();
		Double numPositives =  (double) 0;
		Double numNegatives =  (double) 0 ;
		Double numNeutral =  (double) 0 ;
		List<Comment> comments = comRepo.findAll();
		log.info(comments.toString());
		for(Comment comment : comments) {
			if (comment.getSentiment().contains("Positive")) {
				numPositives=numPositives+1;
			}
			else if(comment.getSentiment().equals("Negative")) {
				numNegatives++;
			}
			else if(comment.getSentiment().equals("Neutral")) {
				numNeutral++;
			}
			
		}
		
		 if(comments.size() != 0) {
			 System.out.println("numcomments:"+comments.size());
		numPositives=  ((numPositives/(comments.size()))*100);
		numNegatives= (numNegatives/comments.size())*100;
		numNeutral= (numNeutral/comments.size())*100;}
		 
		pourcentages.add(numPositives);
		pourcentages.add(numNegatives);
		pourcentages.add(numNeutral); 
		System.out.println(pourcentages);
		return pourcentages;
		
	}

}
