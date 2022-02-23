package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.entity.*;

public interface IGroupsService{
	// CRUD GROUPS
	public void CreateGroup(Groups groups);
	public Groups getByIdGroup(Long idGroup);
	public List<Groups> retrieveAllGroup();
	public void deleteGroup(Long idGroup);
	public void UpadateEtatGroup(Long idGroup,GroupeType etatGroup);
	public void ChangeThemeGroup(Long idGroup,themes theme);
	public String RenameGroups(Long idGroup,String Newname);
	public String ChangeImageGroups(Long idGroup,String image);
	
	public String AddMemberToGroups(Long idGroup,List<Long> IdUser);
	public String RemoveMemberFromGroups(Long idGroup,Long idUser);
	public String RenameMember(Long idGroup,Long idUser,String newName);

}
