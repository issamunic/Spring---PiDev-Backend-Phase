package tn.esprit.spring.statistics;

public class UserByDomain {
	
	private String name;
	
	private Long numberOfUser;
	
	public UserByDomain(String name, Long numberOfUser) {
		super();
		this.name = name;
		this.numberOfUser = numberOfUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNumberOfUser() {
		return numberOfUser;
	}

	public void setNumberOfUser(Long numberOfUser) {
		this.numberOfUser = numberOfUser;
	}
	
	

}
