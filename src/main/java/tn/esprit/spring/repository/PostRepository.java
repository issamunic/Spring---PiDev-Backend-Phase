package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Community;
import tn.esprit.spring.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("Select p from Post p where p.community = ?1")
	List<Post> findAllPostsByCommunity(Community community);
	@Query("Select p from Post p where p.createdOn  >=  DATEADD(day, -30, getdate())")
	List<Post> findLastMonthPosts();

}
