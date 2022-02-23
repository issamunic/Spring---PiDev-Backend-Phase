package tn.esprit.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.*;


@Repository
public interface ChatRepository extends CrudRepository<Chat, Long>{
	
	@Query("SELECT c from Chat c where c.ChatGroup=:group")
	List<Chat> getChatByGroup(@Param("group") Groups group);
}