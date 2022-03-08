package tn.esprit.spring.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.spring.entities.ReactType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactDto {
	private ReactType reactType;
	private Long postId;

}
