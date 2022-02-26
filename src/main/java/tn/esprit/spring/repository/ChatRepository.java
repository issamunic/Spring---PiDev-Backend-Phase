package tn.esprit.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.*;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{
	@Query("SELECT c from Chat c where c.ChatGroup=:group order By dateMsg desc ")
	List<Chat> getChatByGroup(@Param("group") Groups group);
	
	@Query("select c from chat c "
			+ "WHERE c.expirationdate-NOW()<=0 AND ("
			+ "SELECT COUNT(c.ChatGroup) FROM chat c "
			+ "WHERE groups_id_group = c.chat_group_id_group group BY(groups_id_group)"
			+ ") = ("
			+ "SELECT COUNT(etat) FROM chat_etat , chat c where "
			+ "MessageUser = c.idMessage group BY(MessageUser))")
	List<Chat> DeleteSecureMessage();
}