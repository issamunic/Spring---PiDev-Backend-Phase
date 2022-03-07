package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.EtatStories;
import tn.esprit.spring.entity.Stories;
import tn.esprit.spring.entity.User;


@Repository
public interface StoriesRepository extends JpaRepository<Stories, Long>{
	@Query("SELECT DISTINCT s FROM Stories s ")
	List<Stories> getFollowersStorie();
		/*
		 * SELECT  s FROM Stories s  WHERE "
			+ "( s.userStorie =:user ) and ( "
			+ "(s.visibility = publicPersonExcept and s.ExceptStroie !=:userSession ) OR"
			+ "( s.visibility = followersExcept and s.ExceptStroie !=:userSession ) OR "
			+ "(s.visibility = friendAndTheirFrienExcept and s.ExceptStroie !=:userSession ) OR "
			+ "( s.visibility IN ('followers','friendAndTheirFrien') ) "
			+ ") 
			*/
		
	@Query("select s.ExceptStroie from Stories s where "
			+ "s.idStories=:idStorie ")
	List<User> getExceptByUser(@Param("idStorie") Long idStorie );
	
	@Query("select s from Stories s where "
			+ "s.userStorie.idUser=:idUser and "
			+ "s.etatStorie = 'posted' and "
			+ "s.visibility != 'privateStorie' "
			+ "order By s.dateStories desc")
	List<Stories> getStorieByUser(@Param("idUser") Long idUser );
	
	/*
	 SELECT stories.*,COUNT(stories_views_stroie.views_stroie_id_user) "
			+ "FROM stories_views_stroie INNER JOIN stories ON "
			+ "stories_views_stroie.stories_id_stories = stories.id_stories  "
			+ "WHERE stories.user_storie_id_user=:idSession "
			+ "GROUP BY(stories_views_stroie.stories_id_stories)"
			+ "ORDER by stories.date_stories desc)	 
	 */
	@Query( "SELECT s from Stories s where "
			+ "s.userStorie=:user and s.etatStorie=:etatStories "
			+ "order By s.dateStories desc")
	List<Stories> getMyArchiveStories(@Param("user") User user , @Param("etatStories") EtatStories etatStories);
	
	@Query("SELECT DISTINCT s FROM Stories s WHERE "
			+ "(s.dateStories-SYSDATE())+60<=0 and s.etatStorie=:etat")
	List<Stories> ScheduledSetEtatStories(@Param("etat") EtatStories etat);
	
}