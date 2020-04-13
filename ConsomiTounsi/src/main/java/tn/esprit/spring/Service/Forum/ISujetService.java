package tn.esprit.spring.Service.Forum;

import java.util.List;

import tn.esprit.spring.Model.Forum.*;

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
}
