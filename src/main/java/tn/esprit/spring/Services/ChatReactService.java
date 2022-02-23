package tn.esprit.spring.Services;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.ChatReactRepository;
import tn.esprit.spring.repository.ChatRepository;
import tn.esprit.spring.repository.UserRepository;

@Slf4j
@Service
public class ChatReactService implements IChatReactService{

	 @Autowired
	 ChatReactRepository crr;
	 @Autowired
	 ChatRepository cr;
	 @Autowired
	 UserRepository ur;
	@Override
	public void ReactToMessage(ChatReact chatr) {
		log.info(chatr.getChatReact().toString());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateReact = new Date();
		chatr.setDateOfReact(dateReact);
		crr.save(chatr);
		
	}

	@Override
	public void updateReact(Long idReact , React typeReact) {
		ChatReact chatr= crr.findById(idReact).orElse(null);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateReact = new Date();
		
		
		chatr.setReact(typeReact);
		chatr.setDateOfReact(dateReact);

		crr.save(chatr);;
		
	}

	@Override
	public void deleteReact(Long idReact) {
		crr.deleteById(idReact);
		
	}

	


}
