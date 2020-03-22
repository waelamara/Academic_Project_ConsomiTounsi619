package tn.esprit.spring.DAO.Forum;

import java.util.List;

import tn.esprit.spring.Model.Forum.CategorieSujet;



public interface ICategorieSujetService {

	public int ajouterCategorieSujet (CategorieSujet c);
	public List<CategorieSujet> getAllCategorieSujets();
	public void deleteCategorieSujetById(long CategId);
	public List<CategorieSujet> findCategbyName(String name);
}
