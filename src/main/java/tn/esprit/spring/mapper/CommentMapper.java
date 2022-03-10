package tn.esprit.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.dto.CommentsDto;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.User;



@Mapper(componentModel = "spring")
public interface CommentMapper {
	
	
	@Mapping(target = "idComment", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
	Comment map(CommentsDto commentsDto, Post post, User user);

	 @Mapping(target = "postId", expression = "java(comment.getPost().getIdPost())")
	 @Mapping(target = "userName", expression = "java(comment.getUser().getLogin())")
	 CommentsDto mapToDto(Comment comment);
}
