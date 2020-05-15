package tn.esprit.spring.Service.Forum;

import java.util.List;

import javax.mail.MessagingException;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.*;
import tn.esprit.spring.Model.Produit.Produit;

public interface ISujetService {

	public int ajouterSujet (Sujet c,Long categId,Long userId);
	public List<Sujet> getAllSujets();
	public int deleteSujetById(Long sujetId,Long userId);
	public List<Sujet> findSujetbyName(String name);
	public Sujet findOne(Long id);
	public int modifierDescription(String desc, Long sujetId,Long userId);
	public void affecterSujetACategS(Long sujId, Long categId);
	public List<Sujet> getAllSujetNamesByCategorie(Long categId);
	public List<Sujet> findSujetbyUser(Long userid);
	public String findNamebySujet (Long sujetid);
	public User client_gangnant();
	public Produit produit_gangnant() throws MessagingException;
	public void sendmail();
	public List<NbSujetbyCat> countSujetbycatId();
}
