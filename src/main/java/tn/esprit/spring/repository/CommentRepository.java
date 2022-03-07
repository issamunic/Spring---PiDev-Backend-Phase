package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Query("Select c from Comment c where c.createdDate  >= CURRENT_TIMESTAMP -30")
	List<Comment> findLastWeekComments();

}
