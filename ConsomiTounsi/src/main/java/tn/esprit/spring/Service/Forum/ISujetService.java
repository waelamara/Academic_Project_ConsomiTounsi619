package tn.esprit.spring.Service.Forum;

import java.util.List;

import javax.mail.MessagingException;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.*;
import tn.esprit.spring.Model.Produit.Produit;

public interface ISujetService {

	public int ajouterSujet (Sujet c,Long categId,Long userId);
	public void accpeterSujet (Long sujetId);
	public void RefuserSujet (Long sujetId);
	public List<Sujet> getAllSujets();
	public List<Sujet> getAllSujetEtatWaiting();
	public int deleteSujetById(Long sujetId);
	public List<Sujet> findSujetbyName(String name);
	public Sujet findOne(Long id);
	public int modifierDescription(String desc, Long sujetId,Long userId);
	public void affecterSujetACategS(Long sujId, Long categId);
	public List<Sujet> getAllSujetNamesByCategorie(Long categId);
	public List<Sujet> findSujetbyUser(Long userid);
	public String findNamebySujet (Long sujetid);
	public User client_gangnant(int nbpoint);
	public Produit produit_gangnant(int nbpoint) throws MessagingException;
	public void sendmail();
	
}
