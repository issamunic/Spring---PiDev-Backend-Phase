package tn.esprit.spring.entities;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Comment implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idComment;
	String text;
	Instant createdDate;
	String sentiment;
	@JsonIgnore
	 @ManyToOne(fetch = LAZY)
	    @JoinColumn(name = "postId", referencedColumnName = "idPost")
	    private Post post;
	@JsonIgnore
	@ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "idUser")
    private User user;

}
