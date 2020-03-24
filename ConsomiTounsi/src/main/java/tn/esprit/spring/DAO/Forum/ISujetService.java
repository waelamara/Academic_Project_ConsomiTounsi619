package tn.esprit.spring.DAO.Forum;

import java.util.List;

import tn.esprit.spring.Model.Forum.*;

public interface ISujetService {

	public int ajouterSujet (Sujet c,Long categId,Long userId);
	public List<Sujet> getAllSujets();
	public int deleteSujetById(Long sujetId,Long userId);
	public List<Sujet> findSujetbyName(String name);
	public Sujet findOne(Long id);
	public void modifierDescription(String desc, Long sujetId);
	public void affecterSujetACategS(Long sujId, Long categId);
	public List<String> getAllSujetNamesByCategorie(Long categId);
	public List<Sujet> findSujetbyUser(Long userid);
}
