package tn.esprit.spring.statistics;

import tn.esprit.spring.entities.Role;

public class UserByRole {
	
	private Role role;
	private Long numberOfUser;
	
	public UserByRole(Role role, Long numberOfUser) {
		super();
		this.role = role;
		this.numberOfUser = numberOfUser;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getNumberOfUser() {
		return numberOfUser;
	}

	public void setNumberOfUser(Long numberOfUser) {
		this.numberOfUser = numberOfUser;
	}
	
	
}
