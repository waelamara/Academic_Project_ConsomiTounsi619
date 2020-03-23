package tn.esprit.spring.DAO.Forum;

import java.util.List;

import tn.esprit.spring.Model.Forum.*;

public interface ISujetService {

	public int ajouterSujet (Sujet c);
	public List<Sujet> getAllSujets();
	public void deleteSujetById(long sujetId);
	public List<Sujet> findSujetbyName(String name);
	public Sujet findOne(Long id);
	public void modifierDescription(String desc, long sujetId);
	public void affecterSujetACategS(long sujId, long categId);
	public List<String> getAllSujetNamesByCategorie(long categId);
}
