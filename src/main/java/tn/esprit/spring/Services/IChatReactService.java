package tn.esprit.spring.Services;

import tn.esprit.spring.entity.ChatReact;
import tn.esprit.spring.entity.React;

public interface IChatReactService{
	public void ReactToMessage(ChatReact chatr);
	
	public void updateReact(Long idReact ,React typeReact);
	public void deleteReact(Long idReact);
}
