package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Roles;

public interface IRolesService {

	public abstract List<Roles> retrieveAllRoles();
	
	public abstract Roles addRoles(Roles roles);
	
	public abstract void deleteRoles(Long id);

	public abstract Roles updateRoles(Roles roles);

	public abstract Roles retrieveRoles(Long id);
}
