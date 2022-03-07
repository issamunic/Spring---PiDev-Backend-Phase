package tn.esprit.spring.Services;

import java.util.*;

import tn.esprit.spring.entity.*;

public interface IStorieService{

	public Stories CreateStorie(Stories storie);//
	public List<Stories> getStorieByUser(Long idUser); //
	public Map<User,List<Stories>> getFollowersStorie();
	public void BanUserToShowStorie(Long idStorie , Long idUser);
	public void unbannedUserToShowStorie(Long idStorie , Long idUser);
	public void deleteSoty(Long idStorie);
	public List<Stories> getMyArchiveStories();//
	public List<User> BannerUsersList(Long idStorie);
	public void repeatToStorie( Long idGroup,Chat chat);
	
}
