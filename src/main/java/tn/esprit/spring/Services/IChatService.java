package tn.esprit.spring.Services;

import java.util.*;

import tn.esprit.spring.entity.*;

public interface IChatService{
	// CRUD GROUPS
	public Chat SendMessage(Chat chatMessage ,Long to);
	public List<Chat> getMessageByGroup(Long idGroup);
	public List<Chat> getAllMessage();
	public void DeleteMessage(Long idMessage);
	
}