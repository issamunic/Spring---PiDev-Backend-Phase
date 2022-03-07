package tn.esprit.spring.serviceInterface;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.*;

public interface IChatService{
	public void sendAudio(Long to);
	public Chat SendMessage(Chat chatMessage ,Long to );
	public List<Chat> getMessageByGroup(Long idGroup);
	public List<Chat> getAllMessage();
	public void DeleteMessage(Long idMessage);
	public Chat readVocal(Long idMessage);
	public Map<MessageType, List<Chat>> CountTypeOfMessage(Long idGroup);
	
}
