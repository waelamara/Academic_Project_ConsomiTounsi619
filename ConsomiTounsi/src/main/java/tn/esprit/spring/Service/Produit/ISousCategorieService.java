package tn.esprit.spring.Service.Produit;

import java.util.List;

import tn.esprit.spring.Model.Produit.SCategorie;

public interface ISousCategorieService {
	public SCategorie save(SCategorie sc,Long idCategorie);
	public List<SCategorie> findAll();
	public SCategorie findOne(Long id);
	public void Delete(Long id);
	public SCategorie Update(SCategorie sc,Long idScategorie,Long idCategorie);
	public String GetNameById(Long id);
	public List<SCategorie> findSCategorieByCategorie(Long id);
}
