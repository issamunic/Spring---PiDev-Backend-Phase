package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.EtatStories;
import tn.esprit.spring.entity.Stories;
import tn.esprit.spring.entity.Users;


@Repository
public interface StoriesRepository extends JpaRepository<Stories, Long>{
	
	@Query("SELECT DISTINCT s FROM Stories s ,Users u WHERE  "
			+ "( s.visibility = followersExcept ) or (s.visibility = friendAndTheirFrienExcept )or "
			+ "((s.visibility=followers) or (s.visibility=friendAndTheirFrien))"
			+ "and (s.ExceptStroie !=:userSession )")
	List<Stories> getFollowersStorie(@Param("userSession") Users userSession);
	
	@Query("SELECT DISTINCT s FROM Stories s ,Users u  WHERE "
			+ "( s.userStorie =:user ) and ( "
			+ "(s.visibility = publicPersonExcept and s.ExceptStroie !=:userSession ) OR"
			+ "( s.visibility = followersExcept and s.ExceptStroie !=:userSession ) OR "
			+ "(s.visibility = friendAndTheirFrienExcept and s.ExceptStroie !=:userSession ) OR "
			+ "( s.visibility IN ('followers','friendAndTheirFrien') ) "
			+ ") ")
	List<Stories> getStorieByUser(@Param("user") Users user ,  @Param("userSession") Users userSession);
	
	@Query("SELECT DISTINCT s FROM Stories s WHERE "
			+ "s.userStorie.idUser =:idSession")
	List<Stories> getMyArchiveStories(@Param("idSession") Long idSession);
	
	@Query("SELECT DISTINCT s FROM Stories s WHERE "
			+ "(s.dateStories-SYSDATE())+60<=0 and s.etatStorie=:etat")
	List<Stories> ScheduledDeleteStories(@Param("etat") EtatStories etat);
		
}