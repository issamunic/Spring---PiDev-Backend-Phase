package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.ChatReact;
import tn.esprit.spring.entities.React;

public interface IChatReactService{
	public void ReactToMessage(ChatReact chatr);
	
	public void updateReact(Long idReact ,React typeReact);
	public void deleteReact(Long idReact);
}
