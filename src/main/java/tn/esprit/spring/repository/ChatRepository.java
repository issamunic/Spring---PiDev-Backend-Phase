package tn.esprit.spring.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.*;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{
	@Query("SELECT c from Chat c where "
			+ "c.ChatGroup=:group "
			+ "order By dateMsg desc ")
	List<Chat> getChatByGroup(@Param("group") Groups group);
	
	@Query("SELECT c.etat FROM Chat c WHERE "
			+ "c.idMessage =:idMessage")
	List<User> getChatView( @Param("idMessage") Long idMessage );
	
	@Query("SELECT c FROM Chat c WHERE "
			+ "c.expirationdate-SYSDATE()<=0")
	List<Chat> chatEexpirationDate();
	
@Query(value ="SELECT COUNT(message_type) ,message_type FROM chat c WHERE "
			+ "message_type!='text' and "
			+ "chat_group_id_group=2 "
			+ "GROUP BY message_type", nativeQuery = true)
Object CountTypeOfMessages();
	
}