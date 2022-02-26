package tn.esprit.spring.Services;

import java.util.*;

import tn.esprit.spring.entity.*;

public interface IStorieService{

	public Stories CreateStorie(Stories storie);
	public List<Stories> getStorieByUser(Long idUser);
	public List<Stories> getFollowersStorie();
	public void BanUserToShowStorie(Stories storie , Long idUser);
	public void unbannedUserToShowStorie(Stories storie , Long idUser);
	public void updateEtatStorie(Stories storie , Long idUser);
	public void deleteSoty(Stories storie);
	public List<Stories> getMyArchiveStories();
	public void repeatToStorie(Stories storie, String message);
	public void viewStorie(Stories storie);
	
}
