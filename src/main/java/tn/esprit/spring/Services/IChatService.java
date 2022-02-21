package tn.esprit.spring.Services;

import java.util.*;

import tn.esprit.spring.entity.*;

public interface IChatService{
	// CRUD GROUPS
	public Chat SendMessage(Chat chatMessage ,Long to);
	public List<Chat> getMessage(Long idGroup,Long idUser);
	public void DeleteMessage(Long idMessage);
	
}
